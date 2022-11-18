package themulti0.tracker.server;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import themulti0.tracker.server.location.LocationClient;
import themulti0.tracker.server.location.LocationUpdate;

public class MainActivity extends AppCompatActivity {

    LocationUpdate _currentLocation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationClient
            .Create("http://10.0.2.2:8080")
            .thenApply(client -> client
                .getLocation()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLocationUpdate));
    }

    private void onLocationUpdate(LocationUpdate response) {
        this._currentLocation = response;

        TextView latText = findViewById(R.id.lat);
        latText.setText("Lat: " + response.getLat());

        TextView lonText = findViewById(R.id.lon);
        lonText.setText("Lon: " + response.getLon());
    }

    public void onOpenLocationClick(View v) {
        Uri geoLocation = Uri.parse(String.format("geo:%f,%f", this._currentLocation.getLat(), this._currentLocation.getLon()));

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}