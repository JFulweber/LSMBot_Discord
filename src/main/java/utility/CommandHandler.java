package utility;

import commands.CustomRole.ColorChangeCommand;
import commands.CustomRole.NameChangeCommand;
import commands.PingCommand;
import commands.RainbowCommand;

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

        if(commands.containsKey(info.getInvoke())) commands.get(info.getInvoke()).action(info);
    }
}
