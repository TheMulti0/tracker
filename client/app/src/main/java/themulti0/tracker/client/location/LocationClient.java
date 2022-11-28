
package themulti0.tracker.client.location;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.microsoft.signalr.HubConnection;

import java.util.concurrent.CompletableFuture;

import themulti0.tracker.client.models.LocationUpdate;
import themulti0.tracker.client.signalr.HubFactory;

public class LocationClient {
    private static final String path = "/Location";

    private final HubConnection _connection;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static CompletableFuture<LocationClient> Create(String baseUrl) {
        return HubFactory
                .Create(baseUrl + path)
                .thenApply(LocationClient::new);
    }

    LocationClient(HubConnection connection) {
        _connection = connection;
    }

    public void sendLocationUpdate(LocationUpdate location) {
        _connection.send("OnLocationUpdate", location);
    }
}