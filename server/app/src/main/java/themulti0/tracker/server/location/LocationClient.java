package themulti0.tracker.server.location;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import themulti0.tracker.server.http.RequestFactory;

public class LocationClient {
    private final RequestQueue queue;
    private final RequestFactory requestFactory;

    public LocationClient(RequestQueue queue) {
        this.queue = queue;
        this.requestFactory = new RequestFactory();
    }

    public void getLocationUpdate(
            Response.Listener<LocationUpdate> listener
    ) {
        Request<LocationUpdate> request = requestFactory.get(
                LocationUpdate.class,
            "location",
                listener,
                e -> {
                    Log.e("REST", "getLocationUpdate Failed", e);
                }
        );

        this.queue.add(request);
    }
}
