import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import utility.Configuration;

/**
 * Created by Adair on 02/06/17.
 */
public class Main {
    static JDA jda;
    public static void main(String[] args) {
        if(Configuration.setup()) {
            try {
                jda = new JDABuilder(AccountType.BOT).setToken(Configuration.getToken()).buildAsync();
                jda.addEventListener(new ChatListener());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Set your bot account token in the config where you're running this from.");
        }
    }
}
