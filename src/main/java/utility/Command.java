package utility;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by Adair on 02/06/17.
 */
public interface Command {

    boolean called(CommandContainer info);

    void action(CommandContainer info);

    String help();

    void executed(boolean success, MessageReceivedEvent event);

}
