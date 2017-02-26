package commands.CustomRole;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import utility.CommandContainer;
import utility.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adair on 02/08/17.
 */
public class CustomRole {

    public static Role jailRole(CommandContainer info){
        Role role;
        Guild guild = ((GuildMessageReceivedEvent) info.getEvent()).getGuild();
        List<Role> jailRoles = guild.getRolesByName(Configuration.getCustomRolePrefix()+Configuration.getJailRoleName(), true);
        boolean foundrole = false;
        if(jailRoles.size()==1) role = jailRoles.get(0);
        else{
            role = guild.getController().createRole().setName(Configuration.getCustomRolePrefix()+Configuration.getJailRoleName()).complete();
            List<Permission> permList = new ArrayList<>();
            role.getManager().setPermissions(1049664).complete();
        }
        return role;
    }

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