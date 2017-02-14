package commands.CustomRole;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import utility.Command;
import utility.CommandContainer;
import utility.Configuration;

import java.awt.*;

/**
 * Created by Adair on 02/07/17.
 */
public class ColorChangeCommand implements Command{
    public boolean called(CommandContainer info) {
        return info.getArgs().length==1 && info.getArgs()[0].contains("#") && info.getArgs()[0].length()==7;
    }

    public void action(CommandContainer info) {
        boolean foundrole = false;
        Member member = ((GuildMessageReceivedEvent) info.getEvent()).getMember();
        changeColor(CustomRole.findOrCreateRole(info),info.getArgs()[0]);

    }
    public static void changeColor(Role role, String color){
        role.getManager().setColor(Color.decode(color)).queue();
    }


    public String help() {
        return "USAGE: "+ Configuration.getPrefix()+"color <HEX ID COLOR>";
    }

    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
