package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import utility.Command;
import utility.CommandContainer;

import java.util.Arrays;

/**
 * Created by Adair on 02/06/17.
 */
public class PingCommand implements Command {

    CommandContainer info = null;



    public boolean called(CommandContainer container) {
        return false;
    }

    public void action(CommandContainer newinfo) {
        info = newinfo;
        System.out.println("we got this far; to action");
        ((GuildMessageReceivedEvent)info.getEvent()).getMessage().getTextChannel().sendMessage("pong").queue();
        System.out.println(Arrays.toString(newinfo.getArgs()));
    }

    public String help() {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
