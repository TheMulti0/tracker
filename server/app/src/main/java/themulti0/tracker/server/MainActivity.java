package themulti0.tracker.server;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import themulti0.tracker.server.location.LocationClient;
import themulti0.tracker.server.location.LocationUpdate;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationClient client = new LocationClient(Volley.newRequestQueue(this));

        Response.Listener<LocationUpdate> onLocationUpdate = this::onLocationUpdate;

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                client.getLocationUpdate(onLocationUpdate);
                h.postDelayed(this, 1000);
            }
        }, 0);

    }

    private void onLocationUpdate(LocationUpdate response) {
        TextView latText = findViewById(R.id.lat);
        latText.setText(String.format("Lat: %f", response.getLat()));

        TextView lonText = findViewById(R.id.lon);
        lonText.setText(String.format("Lon: %f", response.getLon()));
    }
}