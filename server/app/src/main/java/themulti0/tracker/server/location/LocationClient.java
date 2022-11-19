package themulti0.tracker.server.location;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.microsoft.signalr.HubConnection;

import java.util.concurrent.CompletableFuture;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import themulti0.tracker.server.signalr.HubFactory;

public class LocationClient {
    private static final String path = "/Server";

    private final HubConnection _connection;
    private final BehaviorSubject<LocationUpdate> _location;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static CompletableFuture<LocationClient> Create(String baseUrl) {
        return HubFactory
            .Create(baseUrl + path)
            .thenApply(LocationClient::new);
    }

    private LocationClient(HubConnection connection) {
        _connection = connection;
        _location = BehaviorSubject.create();

        _connection.on(
            "OnLocationUpdate",
            _location::onNext,
            LocationUpdate.class);
    }

    public Observable<LocationUpdate> getLocation() {
        return _location
                .doOnSubscribe(d -> updateCurrentLocation());
    }

    private void updateCurrentLocation() {
        LocationUpdate currentLocation = _connection
                .invoke(LocationUpdate.class, "SendCurrentLocation")
                .blockingGet();

        _location.onNext(currentLocation);
    }
}
