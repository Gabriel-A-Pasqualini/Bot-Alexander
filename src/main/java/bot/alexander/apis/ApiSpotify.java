package bot.alexander.apis;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class ApiSpotify {

    static OkHttpClient client = new OkHttpClient();

    public static String searchArtist(String token, String id) throws IOException {

        String requestUrl = "https://api.spotify.com/v1/artists/" + id;

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .url(requestUrl)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static JSONArray searchTopSongArtist(String token, String id) throws IOException {

        String requestUrl = "https://api.spotify.com/v1/artists/"+id+"/top-tracks?market=ES";

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .url(requestUrl)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray tracks = jsonResponse.getJSONArray("tracks");

            return tracks;
        }
    }

    public static String searchForId(String token, String artist_name) throws IOException {

        String URL_SEARCH_ID_ARTIST = "https://api.spotify.com/v1/search?q=" + artist_name + "&type=artist";

        Request request = new Request.Builder()
                .url(URL_SEARCH_ID_ARTIST)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONObject artists = jsonResponse.getJSONObject("artists");
            JSONArray items = artists.getJSONArray("items");
            JSONObject item_obj_Json = items.getJSONObject(0);
            String id = item_obj_Json.getString("id");
            return id;
        }
    }
}
