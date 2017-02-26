package misc;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Owner on 2/24/2017.
 */
public class MemberHashMap {
    public static HashMap<User,Member> getMemberHashMap(Guild guild){
        HashMap<User, Member> hashMembers = new HashMap<>();
        List<Member> members = guild.getMembers();
        for(Member member:members){
            hashMembers.put(member.getUser(),member);
        }
        return hashMembers;
    }
}
