package utility;

import net.dv8tion.jda.core.entities.Guild;

import java.util.HashMap;

/**
 * Created by fulwejam000 on 3/2/2017.
 */
public class GuildHashMap {
    static HashMap<Guild, GuildContainer> guilds = new HashMap<>();

    public static GuildContainer get(Guild guild){
        if (guilds.containsKey(guild)){
            return guilds.get(guild);
        }
        else{
            guilds.put(guild, new GuildContainer(guild));
            return guilds.get(guild);
        }
    }
}
