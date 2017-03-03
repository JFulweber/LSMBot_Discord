import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import utility.Configuration;
import utility.GuildContainer;
import utility.GuildHashMap;

import java.util.List;

/**
 * Created by Adair on 02/06/17.
 */
public class Main {
    static JDA jda;
    public static void main(String[] args) {
        if(Configuration.setup()) {
            try {
                jda = new JDABuilder(AccountType.BOT).setToken(Configuration.getToken()).buildAsync();
                jda.addEventListener(new MyEventListener());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Set your bot account token in the config where you're running this from.");
        }
        List<Guild> guilds = jda.getGuilds();
        System.out.println(guilds);
    }
}
