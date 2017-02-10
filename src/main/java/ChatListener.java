import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import utility.CommandHandler;
import utility.CommandParser;
import utility.Configuration;

/**
 * Created by Adair on 02/06/17.
 */
public class ChatListener implements EventListener{
    public void onEvent(Event event) {
        if(event instanceof GuildMessageReceivedEvent){
            if (((GuildMessageReceivedEvent) event).getMessage().getContent().substring(0,Configuration.getPrefix().length()).equals(Configuration.getPrefix())){
                CommandHandler.handleCommand(CommandParser.parse(event));
            }
        }
        if(event instanceof PrivateMessageReceivedEvent){
            if(((PrivateMessageReceivedEvent) event).getMessage().getContent().toLowerCase().contains("join")){
                System.out.println("private messag");
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("You can invite me with this link!", "https://discordapp.com/oauth2/authorize?client_id=221857436594733056&scope=bot&permissions=0x00000008");
                builder.setDescription("Have an admin do this!");
                ((PrivateMessageReceivedEvent) event).getChannel().sendMessage(builder.build()).queue();
            }
        }
    }
}
