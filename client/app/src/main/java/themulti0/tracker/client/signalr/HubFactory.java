package themulti0.tracker.client.signalr;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HubFactory {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static CompletableFuture<HubConnection> Create(String url) {

        return CompletableFuture.supplyAsync(() -> {

            HubConnection connection = HubConnectionBuilder.create(url).build();

            connection.start().blockingAwait();

            return connection;

        }, executor);
    }
}
