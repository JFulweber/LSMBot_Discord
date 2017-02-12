import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import utility.CommandContainer;

import java.util.HashMap;

/**
 * Created by Adair on 02/12/17.
 */
public class VoiceChannelManager {

    static HashMap<Guild,HashMap<Game, Integer>> gameCounts = new HashMap<>();


    public static void manageChangeEvent(Event event){
        UserGameUpdateEvent gameChange = (UserGameUpdateEvent) event;
        Guild guild = gameChange.getGuild();
        Game game = guild.getMember(gameChange.getUser()).getGame();
        if(gameCounts.containsKey(guild)){
            HashMap<Game,Integer> inner = gameCounts.get(guild);
            if(inner.containsKey(game)){
                inner.replace(game,inner.get(game).intValue()+1);
            }
        }
    }
}
