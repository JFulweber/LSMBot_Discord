package commands.CustomRole;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import utility.Command;
import utility.CommandContainer;
import utility.Configuration;

import java.util.Arrays;

import static commands.CustomRole.CustomRole.findOrCreateRole;

/**
 * Created by Adair on 02/09/17.
 */
public class NameChangeCommand implements Command{
    @Override
    public boolean called(CommandContainer container) {
        return false;
    }

    @Override
    public void action(CommandContainer info) {
        Guild guild = ((GuildMessageReceivedEvent)info.getEvent()).getGuild();
        Member member = ((GuildMessageReceivedEvent) info.getEvent()).getMember();
        Role role = findOrCreateRole(info);
        String line = "";
        for(String arg:info.getArgs()){
            line+=" "+arg;
        }
        role.getManager().setName(Configuration.getCustomRolePrefix()+line.trim()).queue();
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
