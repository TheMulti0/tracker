package themulti0.tracker.server.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * Convert a JsonElement into a list of objects or an object with Google Gson.
 * <p>
 * The JsonElement is the response object for a {@link com.android.volley.Request.Method} GET call.
 *
 * @author https://plus.google.com/+PabloCostaTirado/about
 */
class GsonGetRequest<T> extends Request<T> {
    private final Class<T> clazz;
    private final Gson gson;
    private final Response.Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url           URL of the request to make
     * @param listener      is the listener for the right answer
     * @param errorListener is the listener for the wrong answer
     */
    public GsonGetRequest(
            Class<T> clazz,
            String url,
            Gson gson,
            Response.Listener<T> listener,
            Response.ErrorListener errorListener
    ) {
        super(Method.GET, url, errorListener);

        this.clazz = clazz;
        this.gson = gson;
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success
            (
                gson.fromJson(json, clazz),
                HttpHeaderParser.parseCacheHeaders(response)
            );
        } catch (UnsupportedEncodingException | JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }


}