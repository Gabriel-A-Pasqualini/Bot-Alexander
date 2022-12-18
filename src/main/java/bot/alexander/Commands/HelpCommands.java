package bot.alexander.Commands;
import javax.annotation.Nonnull;
import bot.alexander.alexander;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpCommands extends ListenerAdapter{
    
    String prefix = alexander.prefix;
    
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        
        //String user_name = event.getAuthor().getAsMention(); --Mention someone

        if (message.startsWith(prefix + "help")) {

            try {
                EmbedBuilder info = new EmbedBuilder();
                info.setTitle("🕹 Commands: ");
                //info.addField("😀 User ",user_name, false);
                info.addField("🎥 Movie information ", "?movie [movie's name]", false);
                info.addField("🍣🍜 Anime information ", "?anime [anime's name]", false);
                info.addField("🎼🎶 Artist information ", "?spotify [artist's name]", false);
                info.addField("⚖ Advice for you ", "?advice ", false);
                info.setColor(0xab0a1d);

                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessageEmbeds(info.build()).complete();
                info.clear();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("help java não esta funcionando: "+e);
            }
        }

    }
}