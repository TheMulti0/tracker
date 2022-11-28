package themulti0.tracker.client.location;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import themulti0.tracker.client.models.LocationUpdate;

public class LocationService extends Service {

    private Disposable subscription;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate() {
        try {
            LocationClient client = LocationClient.Create("http://10.0.2.2:8080").get();

            // TODO check if gps is enabled and check permissions

            FusedLocationProviderClient fused = LocationServices.getFusedLocationProviderClient(this);
            Observable<LocationUpdate> locationStream = LocationStream.listen(fused);

            subscription = locationStream.subscribe(client::sendLocationUpdate);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        if (subscription.isDisposed()) {
            return;
        }

        subscription.dispose();
    }

}
