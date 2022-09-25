package themulti0.tracker.client.location;

import android.location.Location;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.Priority;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class LocationStream {
    private FusedLocationProviderClient client;
    private BehaviorSubject<LocationUpdate> subject;
    private LocationRequest request;
    private LocationCallback callback;

    public static Observable<LocationUpdate> listen(FusedLocationProviderClient client) {
        return new LocationStream(client).createStream();
    }

    private LocationStream(FusedLocationProviderClient client) {
        this.client = client;
        this.subject = BehaviorSubject.create();
        this.request = LocationRequest.create()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setInterval(0)
                .setFastestInterval(0);
        this.callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult result) {
                super.onLocationResult(result);

                Location lastLocation = result.getLastLocation();
                LocationUpdate update = new LocationUpdate(
                        lastLocation.getLatitude(),
                        lastLocation.getLongitude(),
                        lastLocation.getAltitude());

                subject.onNext(update);
            }
        };
    }

    private Observable<LocationUpdate> createStream() {
        return subject
                .doOnSubscribe(d -> client.requestLocationUpdates(request, callback, null))
                .doOnDispose(() -> client.removeLocationUpdates(callback));
    }
}
