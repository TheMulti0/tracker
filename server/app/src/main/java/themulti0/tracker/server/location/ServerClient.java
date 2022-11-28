package themulti0.tracker.server.location;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.microsoft.signalr.HubConnection;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import themulti0.tracker.server.models.LocationUpdate;
import themulti0.tracker.server.models.SensorUpdate;
import themulti0.tracker.server.signalr.HubFactory;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ServerClient {
    private static final String Path = "/Server";
    private static Optional<CompletableFuture<ServerClient>> Instance = Optional.empty();

    private final HubConnection _connection;
    private final BehaviorSubject<LocationUpdate> _location;
    private final BehaviorSubject<SensorUpdate> _sensor;


    public static CompletableFuture<ServerClient> Create(String baseUrl) {
        if (!Instance.isPresent()) {
            Instance = Optional.of(
                HubFactory
                    .Create(baseUrl + Path)
                    .thenApply(ServerClient::new));
        }

        return Instance.get();
    }

    private ServerClient(HubConnection connection) {
        _connection = connection;
        _location = BehaviorSubject.create();
        _sensor = BehaviorSubject.create();

        _connection.on(
            "OnLocationUpdate",
            _location::onNext,
            LocationUpdate.class);

        _connection.on(
            "OnSensorUpdate",
            _sensor::onNext,
            SensorUpdate.class);
    }

    public Observable<LocationUpdate> getLocation() {
        return _location
            .doOnSubscribe(d -> updateCurrentLocation());
    }

    public Observable<SensorUpdate> getSensor() {
        return _sensor
            .doOnSubscribe(d -> updateCurrentSensor());
    }

    private void updateCurrentLocation() {
        LocationUpdate currentLocation = _connection
                .invoke(LocationUpdate.class, "GetCurrentLocation")
                .blockingGet();

        _location.onNext(currentLocation);
    }

    private void updateCurrentSensor() {
        SensorUpdate currentSensor = _connection
                .invoke(SensorUpdate.class, "GetCurrentLocation")
                .blockingGet();

        _sensor.onNext(currentSensor);
    }
}
