package bot.alexander.Commands;

import bot.alexander.alexander;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpCommands extends ListenerAdapter{
    
    String prefix = alexander.prefix;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().equalsIgnoreCase(prefix + "help")) {
            System.out.println("help java");
            try {
                EmbedBuilder info = new EmbedBuilder();
                info.setTitle("πΉ Commands: ");
                info.addField("π₯ Movie information ", "?movie [movie's name]", false);
                info.addField("π£π Anime information ", "?anime [anime's name]", false);
                info.addField("πΌπΆ Artist information ", "?artist [artist's name]", false);
                info.addField("β Advice for you ", "?advice ", false);
                info.setColor(0xab0a1d);

                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessageEmbeds(info.build()).complete();
                info.clear();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("help java nΓ£o esta funcionando: "+e);
            }
        }

    }
}