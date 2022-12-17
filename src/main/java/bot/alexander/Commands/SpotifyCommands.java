package bot.alexander.Commands;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.json.JSONArray;
import org.json.JSONObject;
import bot.alexander.alexander;
import bot.alexander.apis.ApiSpotify;
import bot.alexander.apis.TokenSpotify.TokenSpotify;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SpotifyCommands extends ListenerAdapter{
    
    String prefix = alexander.prefix;

    //@Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();
        if (message.startsWith(prefix + "spotify")) {
            
            String artist_name = message.split("spotify")[1];
            
            try {
                EmbedBuilder info = new EmbedBuilder();

                final String SEARCH_URL_TOKEN = "https://accounts.spotify.com/api/token";

                String tokenResponse = TokenSpotify.sendGetRequest(SEARCH_URL_TOKEN);

                String token = (new JSONObject(tokenResponse)).getString("access_token");

                String id = ApiSpotify.searchForId(token,artist_name);

                JSONObject artist_inf = ApiSpotify.searchArtist(token, id);

                JSONArray topTracks = ApiSpotify.searchTopSongArtist(token, id);



                List top_five_songs = new ArrayList();
                for (Integer i = 0;i<=5;i++) {

                    JSONObject track = topTracks.getJSONObject(i);
                    JSONObject album = track.getJSONObject("album");
                    JSONArray imagem = album.getJSONArray("images");
                    JSONObject imagem_media = imagem.getJSONObject(1);
                    //String url_img_media = imagem_media.getString("url");
                    String album_name = track.getString("name");
                    //String preview_url = track.getString("preview_url");
                    top_five_songs.add(album_name);
                    
                }

                String name = artist_inf.getString("name");
                Integer popularity = artist_inf.getInt("popularity");

                JSONArray imagem = artist_inf.getJSONArray("images");
                JSONObject imagem_media = imagem.getJSONObject(1);
                String url_img_media = imagem_media.getString("url");

                JSONObject followers = artist_inf.getJSONObject("followers");
                Integer total = followers.getInt("total");


                List genre_list = new ArrayList();
                JSONArray genres = artist_inf.getJSONArray("genres");

                Integer x = genres.length();

                for(Integer i = 0;i<=x-1;i++){

                    String genre = genres.getString(i);
                    genre_list.add(genre);
                }
                
                info.setTitle("Artist: "+name);// + artists.getString("desc"));
                info.addField("Followers: ", total.toString(), false);
                info.addField("Popularity: ", popularity.toString(), false);
                info.addField("Genre: ", String.join("\n", genre_list), false);
                info.addField("Song: ", String.join("\n", top_five_songs), false);
                info.addField("Link: ", "https://open.spotify.com/artist/"+id, false);
                info.setThumbnail(url_img_media);
                info.setColor(0xab0a1d);
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessageEmbeds(info.build()).queue();


            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("SpotifyCommand -  ArrayIndexOutOfBoundsException Error: "+e);
            
            } catch (IOException e) {
                System.out.println("SpotifyCommand -  IOException: "+e);      
                e.printStackTrace();

            } catch (InterruptedException e) {
                System.out.println("SpotifyCommand -  InterruptedException: "+e);                
                e.printStackTrace();
            }
        }
    }

    private Object x(int length) {
        return null;
    }
}
