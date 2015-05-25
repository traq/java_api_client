/**
 * Traq Java API Client.
 *
 * @author Jack Polgar <jack@polgar.id.au>
 * @license Apache-2.0
 */

package traq;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * API class.
 */
public class API {
    protected static final String version = "1.0";

    /**
     * Fetch a JSON response from the URL.
     *
     * @param url URL to fetch from
     *
     * @return JsonElement
     *
     * @throws IOException
     */
    public static JsonElement fetch(String url) throws IOException {
        URL remote = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) remote.openConnection();
        httpURLConnection.addRequestProperty("User-Agent", userAgent());
        httpURLConnection.connect();

        JsonParser jsonParser = new JsonParser();

        return jsonParser.parse(new InputStreamReader((InputStream)httpURLConnection.getContent()));
    }

    /**
     * @return user agent to use when fetching data
     */
    protected static String userAgent() {
        return "Mozilla/5.0 TraqJava/" + version;
    }
}
