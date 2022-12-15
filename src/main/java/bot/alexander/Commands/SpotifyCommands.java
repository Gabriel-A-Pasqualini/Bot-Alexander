package bot.alexander.Commands;

import java.io.IOException;
import org.json.JSONObject;
import bot.alexander.alexander;
import bot.alexander.apis.TokenSpotify.TokenSpotify;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SpotifyCommands extends ListenerAdapter{
    
    String prefix = alexander.prefix;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().equalsIgnoreCase(prefix + "Spotify")) {
            
            try {

                final String SEARCH_URL = "https://accounts.spotify.com/api/token";
                
                String tokenResponse = TokenSpotify.sendGetRequest(SEARCH_URL);

                String obj = (new JSONObject(tokenResponse)).getString("access_token");
                
                System.out.println(obj);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("========== Spotify command erro 1: "+e);
            
            } catch (IOException e) {
                
                e.printStackTrace();
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
        }
    }
}
