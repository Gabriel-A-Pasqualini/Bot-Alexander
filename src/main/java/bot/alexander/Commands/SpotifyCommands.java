package bot.alexander.Commands;
import java.io.IOException;
import java.lang.reflect.Array;

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
        if (message.startsWith(prefix + "Spotify")) {
            
            String artist_name = message.split("Spotify")[1];
            
            try {
                EmbedBuilder info = new EmbedBuilder();

                final String SEARCH_URL_TOKEN = "https://accounts.spotify.com/api/token";

                String tokenResponse = TokenSpotify.sendGetRequest(SEARCH_URL_TOKEN);

                String token = (new JSONObject(tokenResponse)).getString("access_token");

                String id = ApiSpotify.searchForId(token,artist_name);

                String artist_inf = ApiSpotify.searchArtist(token, id);

                JSONArray topTracks = ApiSpotify.searchTopSongArtist(token, id);


                Integer i = 0;
                for (Object albuns : topTracks) {

                    JSONObject track = topTracks.getJSONObject(i);

                    JSONObject album = track.getJSONObject("album");
                    JSONArray imagem = album.getJSONArray("images");
                    JSONObject imagem_media = imagem.getJSONObject(1);
                    String url_img_media = imagem_media.getString("url");
                    String album_name = track.getString("name");
                    String preview_url = track.getString("preview_url");
 
                    i++;
                }

                

                //info.setTitle("Artist: Gojira ");// + artists.getString("desc"));
                //info.addField("album: ", String.join("/", album_name), false);
                //info.setThumbnail(url_img_media); // setImage
                //info.setColor(0xab0a1d);
                //event.getChannel().sendTyping().queue();
                //event.getChannel().sendMessageEmbeds(info.build()).queue();


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
