package themulti0.tracker.client.sensor;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.disposables.Disposable;
import themulti0.tracker.client.models.SensorUpdate;

public class SensorService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private SensorClient sensorClient;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate() {
        sensorClient = createSensorClient();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
            SensorManager.SENSOR_DELAY_NORMAL);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private SensorClient createSensorClient() {
        try {
            return SensorClient.Create("http://10.0.2.2:8080").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onDestroy() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorClient.sendSensorUpdate(new SensorUpdate(event.sensor.getName(), event.values));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
