package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import utility.Command;
import utility.CommandContainer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fulwejam000 on 2/15/2017.
 */
public class MusicCommand implements Command{
    @Override
    public boolean called(CommandContainer info) {
        return !(info.getArgs().length==0);
    }

    @Override
    public void action(CommandContainer info) {
        String[] args = null;
        String subCommand = info.getArgs()[0];
        args = (String[]) Arrays.asList(info.getArgs()).subList(1,info.getArgs().length).toArray();
        ArrayList<String> subCommands = new ArrayList<>();
        subCommands.add("p"); subCommands.add("s"); subCommands.add("c");

    }

    @Override
    public String help() {
        return "```" +
                "\n     MUSIC HELP" +
                "   Play: p, then a link or title of video" +
                "   Currently Playing: c" +
                "   Skip: s" +
                "   ```";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
