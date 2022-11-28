
package themulti0.tracker.client.sensor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.microsoft.signalr.HubConnection;

import java.util.concurrent.CompletableFuture;

import themulti0.tracker.client.models.LocationUpdate;
import themulti0.tracker.client.models.SensorUpdate;
import themulti0.tracker.client.signalr.HubFactory;

public class SensorClient {
    private static final String path = "/Sensor";

    private final HubConnection _connection;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static CompletableFuture<SensorClient> Create(String baseUrl) {
        return HubFactory
                .Create(baseUrl + path)
                .thenApply(SensorClient::new);
    }

    SensorClient(HubConnection connection) {
        _connection = connection;
    }

    public void sendSensorUpdate(SensorUpdate sensor) {
        _connection.send("OnSensorUpdate", sensor);
    }
}