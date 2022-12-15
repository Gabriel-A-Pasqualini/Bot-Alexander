package bot.alexander;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.security.auth.login.LoginException;
import bot.alexander.Commands.HelpCommands;
import bot.alexander.Commands.HippocratesCommands;
import bot.alexander.Commands.MovieCommands;
import bot.alexander.Commands.SpotifyCommands;
import bot.alexander.Commands.AdviceCommands;
import bot.alexander.Commands.AnimeCommands;
import bot.alexander.Commands.ArtistCommands;
import bot.alexander.Commands.CommandConfig;
import net.dv8tion.jda.api.OnlineStatus;
//import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDABuilder;

public class alexander {
    public static String prefix="?";
    
    public static void main(String[] args) throws LoginException, IOException{
        
        //Path caminho = Paths.get("./BotJava/bot/token.txt");
        //byte[] texto = Files.readAllBytes(caminho);
        //String leitura = new String("texto"); 
        
        JDABuilder jda = JDABuilder.createDefault("ODU0MDI3NjE1MzU4Mjg3OTEy.YMd9Mg.sVyf7kmrXd34wRbgtb5Y5Z5c36Y");
        
        //jda.setActivity(Activity.watching());
        jda.setStatus(OnlineStatus.ONLINE);
        jda.addEventListeners(new SpotifyCommands());
        jda.addEventListeners(new ArtistCommands());
        jda.addEventListeners(new HelpCommands());
        jda.addEventListeners(new AdviceCommands());
        jda.addEventListeners(new MovieCommands());
        jda.addEventListeners(new AnimeCommands());
        jda.addEventListeners(new CommandConfig());
        jda.addEventListeners(new HippocratesCommands());
        jda.build();
        System.out.println("I'm online!");
        
    }
}