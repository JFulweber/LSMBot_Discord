import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import utility.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adair on 02/12/17.
 */
public class VoiceChannelManager {

    static HashMap<Guild,HashMap<Game, Integer>> gameCounts = new HashMap<>();

    public static void manageChangeEvent(Event event){
        UserGameUpdateEvent gameChange = (UserGameUpdateEvent) event;
        Guild guild = gameChange.getGuild();
        Game game = guild.getMember(gameChange.getUser()).getGame();
        // maybe just loop for it on each update? it would probably make things easier and wouldn't affect performance on a small scale greatly
        if(game==null){
            game = gameChange.getPreviousGame();
            HashMap<Game, Integer> inner = gameCounts.get(guild);
            if(inner.containsKey(game))
                inner.replace(game,inner.get(game).intValue()-1);
            else{
                inner.put(game,1);
            }
            return;
        }
        if(gameCounts.containsKey(guild)){
            HashMap<Game,Integer> inner = gameCounts.get(guild);
            if(inner.containsKey(game)){
                inner.replace(game,inner.get(game).intValue()+1);
            }
        }
        else{
            HashMap<Game, Integer> inner = new HashMap<>();
            inner.put(game, 1);
            gameCounts.put(guild, inner);
        }
        if (game!=null){
            if(gameCounts.get(guild).get(game)>=2){
                System.out.println("There are 2 or more people playing a game, planning on creating a channel.");
                //guild.getController().createVoiceChannel(Configuration.getGeneratedChannelPrefix()+game.getName());
            }
        }
        System.out.println(gameCounts.get(guild));
    }
}
