package bot.alexander;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.security.auth.login.LoginException;
import bot.alexander.Commands.HelpCommands;
import bot.alexander.Commands.MovieCommands;
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
        
        Path caminho = Paths.get("C:/Users/Pasqualini/OneDrive - UNIP/Documents/Dev/BotJava/bot/token.txt");
        byte[] texto = Files.readAllBytes(caminho);
        String leitura = new String(texto); 
        
        JDABuilder jda = JDABuilder.createDefault(leitura);
        
        //jda.setActivity(Activity.watching());
        jda.setStatus(OnlineStatus.ONLINE);
        jda.addEventListeners(new ArtistCommands());
        jda.addEventListeners(new HelpCommands());
        jda.addEventListeners(new AdviceCommands());
        jda.addEventListeners(new MovieCommands());
        jda.addEventListeners(new AnimeCommands());
        jda.addEventListeners(new CommandConfig());
        jda.build();
        System.out.println("I'm online!");
        
    }
}