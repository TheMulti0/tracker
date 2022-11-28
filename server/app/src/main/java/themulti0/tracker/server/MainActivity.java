package themulti0.tracker.server;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import themulti0.tracker.server.models.LocationUpdate;

public class MainActivity extends AppCompatActivity {

    LocationUpdate _currentLocation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void onLocationUpdate(LocationUpdate response) {
        this._currentLocation = response;

        TextView latText = findViewById(R.id.lat);
        latText.setText("Lat: " + response.getLat());

        TextView lonText = findViewById(R.id.lon);
        lonText.setText("Lon: " + response.getLon());
    }

}