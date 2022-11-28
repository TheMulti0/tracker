package themulti0.tracker.client;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

import themulti0.tracker.client.location.LocationService;
import themulti0.tracker.client.sensor.SensorService;

public class MainActivity extends AppCompatActivity {

    private Intent[] services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        services = new Intent[]{
            new Intent(this, LocationService.class),
            new Intent(this, SensorService.class),
        };

        for (Intent service : services) {
            startService(service);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (Intent service : services) {
            stopService(service);
        }
    }
}