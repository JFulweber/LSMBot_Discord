package utility;

import commands.CustomRole.ColorChangeCommand;
import commands.CustomRole.NameChangeCommand;
import commands.JailCommand;
import commands.MusicCommand;
import commands.PingCommand;
import commands.RainbowCommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;

/**
 * Created by Adair on 02/07/17.
 */
public class CommandHandler {

    public final static CommandParser parser = new CommandParser();
    public static HashMap<String, Command> commands = new HashMap<String, Command>();


    public static void handleCommand(CommandContainer info){
        /////////////////////////////
        // ADDING COMMANDS SECTION //
        commands.put("ping", new PingCommand());
        commands.put("color", new ColorChangeCommand());
        commands.put("name", new NameChangeCommand());
        commands.put("rainbow", new RainbowCommand());
        commands.put("m", new MusicCommand());
        commands.put("p", new MusicCommand());
        commands.put("j", new JailCommand());
        commands.put("jail", new JailCommand());

        if(commands.containsKey(info.getInvoke()))
            if(commands.get(info.getInvoke()).called(info))
                commands.get(info.getInvoke()).action(info);
            else{
                ((GuildMessageReceivedEvent) info.getEvent()).getMessage().getTextChannel().sendMessage(commands.get(info.getInvoke()).help()).queue();
            }
        else{
            ((GuildMessageReceivedEvent) info.getEvent()).getMessage().getTextChannel().sendMessage("Unrecognized command!").queue();
        }
    }
}
