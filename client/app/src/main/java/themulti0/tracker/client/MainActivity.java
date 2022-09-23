package themulti0.tracker.client;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import themulti0.tracker.client.http.LocationUpdate;
import themulti0.tracker.client.http.RequestFactory;

public class MainActivity extends AppCompatActivity {

    private RequestFactory requestFactory = new RequestFactory();
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLocationUpdates();

        queue = Volley.newRequestQueue(this);
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }

            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location lastLocation = locationResult.getLastLocation();

                queue.add(requestFactory.post(
                    "location",
                    new LocationUpdate(lastLocation.getLatitude(), lastLocation.getLongitude(), lastLocation.getAltitude()),
                    e -> {},
                    e -> {}
                ));
            }
        };

        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }
}