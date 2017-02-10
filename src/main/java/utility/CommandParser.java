package utility;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;

/**
 * Created by Adair on 02/06/17.
 */
public class CommandParser {

    public static CommandContainer parse(Event event){

        String in = ((GuildMessageReceivedEvent) event).getMessage().getContent();
        ArrayList<String> split = new ArrayList<String>();
        String[] splitBeheaded = in.replaceFirst(Configuration.getPrefix(),"").split(" ");
        for (int i = 0; i < splitBeheaded.length; i++) {
            split.add(splitBeheaded[i].toLowerCase());
        }
        String invoke = split.get(0);
        String[] args = new String[split.size()-1];
        split.subList(1,split.size()).toArray(args);

        return new CommandContainer(in,invoke,args,event);

    }


}
