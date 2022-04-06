package bot.alexander.Commands;

import bot.alexander.alexander;
import bot.alexander.apis.myanimelistAip;
import org.json.JSONArray;
import org.json.JSONObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AnimeCommands extends ListenerAdapter {
    
    String prefix = alexander.prefix;
    
    public void onMessageReceived(MessageReceivedEvent event) {

        try {
            String message = event.getMessage().getContentRaw();
            String title = message.split("anime")[1];
            if (message.equalsIgnoreCase(prefix + "anime" + title)) {
                try {

                    String jsonResponse = myanimelistAip.searchAnime(title);

                    JSONObject obj = new JSONObject(jsonResponse);

                    JSONArray objaux = obj.getJSONArray("results");

                    JSONObject f = objaux.getJSONObject(0);
                    EmbedBuilder info = new EmbedBuilder();

                    info.setTitle("🍣 🍜 Information about the Anime: " + f.getString("title") + "");
                    float x = f.getFloat("score");
                    String s = String.valueOf(x);
                    info.addField("Score: ", s, true);
                    info.addField("Type: ", f.getString("type"), true);
                    float x2 = f.getFloat("episodes");
                    String s2 = String.valueOf(x2);
                    info.addField("Number of episodes: ", s2, true);
                    info.addField("Start date: ", f.getString("start_date").split("T")[0], true);
                    info.addField("End date: ", f.getString("end_date").split("T")[0], true);
                    info.addField("Synopsis: ", f.getString("synopsis"), false);
                    info.setImage(f.getString("image_url")); // info.setImage or info.setThumbnail
                    info.setColor(0xab0a1d);
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessageEmbeds(info.build()).queue();

                    info.clear();

                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        } catch (Exception e) {

        }
    }
}
