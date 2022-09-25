package themulti0.tracker.client.http;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RequestFactory {
    private final Gson gson;
    private final String url;

    public RequestFactory() {
        gson = new Gson();
        url = "http://10.0.2.2:8080";
    }

    public <TResponse> Request<TResponse> get(
            String path,
            Response.Listener<TResponse> listener,
            Response.ErrorListener errorListener) {
        return new GsonGetRequest<TResponse>(
                String.format("%s/%s", url, path),
                new TypeToken<TResponse>() {
                }.getType(),
                gson,
                listener,
                errorListener);
    }

    public <TRequest, TResponse> Request<TResponse> post(
            String path,
            TRequest request,
            Response.Listener<TResponse> listener,
            Response.ErrorListener errorListener) {
        return new GsonPostRequest<TResponse>(
                String.format("%s/%s", url, path),
                gson.toJson(request),
                new TypeToken<TResponse>() {
                }.getType(),
                gson,
                listener,
                errorListener);
    }
}
