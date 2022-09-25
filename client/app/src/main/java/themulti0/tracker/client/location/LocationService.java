package themulti0.tracker.client.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class LocationService extends Service {

    private Disposable subscription;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        LocationClient client = new LocationClient(Volley.newRequestQueue(this));

        FusedLocationProviderClient fused = LocationServices.getFusedLocationProviderClient(this);

        // TODO check if gps is enabled and check permissions

        Observable<LocationUpdate> locationStream = LocationStream.listen(fused);

        subscription = locationStream.subscribe(client::sendLocationUpdate);
    }

    @Override
    public void onDestroy() {
        if (subscription.isDisposed()) {
            return;
        }

        subscription.dispose();
    }

}
