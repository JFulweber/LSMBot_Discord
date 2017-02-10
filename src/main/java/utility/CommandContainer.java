package utility;

import net.dv8tion.jda.core.events.Event;

/**
 * Created by Adair on 02/07/17.
 */
public class CommandContainer{
    /*
    raw = original string
    invoke = command name
    args = arguement commands after name
    event = event
     */
    private String raw;
    private String invoke;
    private String[] args;
    private Event event;

    public CommandContainer(String raw,String invoke, String[] args, Event event){
        this.raw = raw;
        this.invoke = invoke;
        this.args = args;
        this.event = event;
    }

    public String getRaw() {
        return raw;
    }

    public String getInvoke() {
        return invoke;
    }

    public String[] getArgs() {
        return args;
    }

    public Event getEvent() {
        return event;
    }
}