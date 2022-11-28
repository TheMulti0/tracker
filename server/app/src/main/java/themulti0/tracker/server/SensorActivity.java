package themulti0.tracker.server;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import themulti0.tracker.server.location.ServerClient;
import themulti0.tracker.server.models.SensorUpdate;

public class SensorActivity extends AppCompatActivity {

    SensorUpdate _sensor;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        ServerClient
            .Create("http://10.0.2.2:8080")
            .thenApply(client -> client
                .getSensor()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSensorUpdate));
    }

    private void onSensorUpdate(SensorUpdate sensorUpdate) {
        this._sensor = sensorUpdate;
        TextView label = findViewById(R.id.sensorLabel);

        StringBuilder builder = new StringBuilder();

        builder.append("Sensor: ");
        builder.append(sensorUpdate.getSensor());

        for (float value : sensorUpdate.getValues()) {
            builder.append("\n");
            builder.append(value);
        }
        label.setText(builder.toString());
    }
}