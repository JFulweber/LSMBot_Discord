package commands.CustomRole;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import utility.CommandContainer;
import utility.Configuration;

/**
 * Created by Adair on 02/08/17.
 */
public class CustomRole {
    public static Role createRole(CommandContainer info) {
        Role role = ((GuildMessageReceivedEvent) info.getEvent()).getGuild().getController().createRole().setName(Configuration.getCustomRolePrefix() + ((GuildMessageReceivedEvent) info.getEvent()).getMember().getEffectiveName()).complete();
        ((GuildMessageReceivedEvent) info.getEvent()).getGuild().getController().addRolesToMember(((GuildMessageReceivedEvent) info.getEvent()).getMember(), role).queue();
        System.out.println(role.getName());
        return role;
    }

    public static Role findOrCreateRole(CommandContainer info){
        Role role = null;
        boolean foundrole = false;
        Member member = ((GuildMessageReceivedEvent) info.getEvent()).getMember();
        for(Role roles:member.getRoles()){
            if(roles.getName().indexOf(Configuration.getCustomRolePrefix())==0){
                foundrole = true;
                role = roles;
            }
        }
        if(!foundrole){
            role = createRole(info);
        }
        assert !role.equals(null);
        return role;
    }
}