package themulti0.tracker.client.location;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import themulti0.tracker.client.http.RequestFactory;

public class LocationClient {
    private final RequestQueue queue;
    private final RequestFactory requestFactory;

    public LocationClient(RequestQueue queue) {
        this.queue = queue;
        this.requestFactory = new RequestFactory();
    }

    public void sendLocationUpdate(LocationUpdate locationUpdate) {
        Request<Object> request = requestFactory.post(
            "location",
            locationUpdate,
            e -> {},
            e -> {}
        );

        this.queue.add(request);
    }
}
