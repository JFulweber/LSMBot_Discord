import misc.Deleter;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import utility.CommandHandler;
import utility.CommandParser;
import utility.Configuration;

import java.util.List;

import static net.dv8tion.jda.core.entities.Game.of;

/**
 * Created by Adair on 02/06/17.
 */
public class MyEventListener implements EventListener {
    public void onEvent(Event event) {
        if (event instanceof GuildMessageReceivedEvent) {
            if (((GuildMessageReceivedEvent) event).getMessage().getContent().substring(0, Configuration.getPrefix().length()).equals(Configuration.getPrefix())) {
                CommandHandler.handleCommand(CommandParser.parse(event));
            }
        }
        if (event instanceof PrivateMessageReceivedEvent) {
            if (((PrivateMessageReceivedEvent) event).getMessage().getContent().toLowerCase().contains("join")) {
                System.out.println("private messag");
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("You can invite me with this link!", "https://discordapp.com/oauth2/authorize?client_id=221857436594733056&scope=bot&permissions=0x00000008");
                builder.setDescription("Have an admin do this!");
                ((PrivateMessageReceivedEvent) event).getChannel().sendMessage(builder.build()).queue();
            } else if (((PrivateMessageReceivedEvent) event).getMessage().getContent().toLowerCase().contains("setgame") && ((PrivateMessageReceivedEvent) event).getAuthor().getId() == "127150373931712512") {
                String fullName = "";
                String original = ((PrivateMessageReceivedEvent) event).getMessage().getContent();
                for (String part : original.split(" ")) {
                    fullName += (!part.toLowerCase().equals("setgame")) ? part + " " : "";
                }
                Main.jda.getPresence().setGame(Game.of(fullName.trim()));
                if (((PrivateMessageReceivedEvent) event).getMessage().getContent().toLowerCase().contains("setgame")) {
                    String msg = ((PrivateMessageReceivedEvent) event).getMessage().getContent();
                    msg = msg.substring(7);
                    Main.jda.getPresence().setGame(of(msg));
                    System.out.println(event.getJDA().getGuilds().get(1).getName());
                    UserGameUpdateEvent createdevent = new UserGameUpdateEvent(Main.jda, (long) 1, Main.jda.getSelfUser(), event.getJDA().getGuilds().get(1), null);
                    VoiceChannelManager.manageChangeEvent(createdevent);
                }
            }
            if (event instanceof UserGameUpdateEvent) {
                VoiceChannelManager.manageChangeEvent(event);
            }
            if (event instanceof GuildVoiceMoveEvent || event instanceof GuildVoiceLeaveEvent) {
                System.out.println("leave event");
                VoiceChannel channel = ((GuildVoiceMoveEvent) event).getChannelLeft();
                Deleter.shouldDelete(channel);
            }
        }
    }
}
