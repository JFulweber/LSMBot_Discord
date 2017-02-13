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
        Game oldGame = gameChange.getPreviousGame();
        if(gameCounts.containsKey(guild)){
            HashMap<Game,Integer> inner = gameCounts.get(guild);
            if(inner.containsKey(game)){
                inner.replace(game,inner.get(game).intValue()+1);
            }
            else{
                inner.put(game,1);
            }

            if(inner.containsKey(oldGame)){
                inner.replace(oldGame,inner.get(oldGame).intValue()-1);
            }
            gameCounts.replace(guild,inner);
        }
        else{
            HashMap<Game, Integer> inner = new HashMap<>();
            inner.put(game,1);
            gameCounts.put(guild, inner);
        }

        HashMap<Game,Integer> inner = gameCounts.get(guild);
        System.out.println(inner);
        inner.forEach((k,v)->{
            System.out.println("k: "+k.getName()+
                    "\nv: "+v.intValue());
        });
       /* inner.forEach((k,v)->{

            List<VoiceChannel> channels = guild.getVoiceChannels();
            boolean foundVoice = false;
            for(VoiceChannel channel:channels){
                if(channel.toString().equals(Configuration.getGeneratedChannelPrefix()+k.getName())){
                    foundVoice = true;
                }
            }
            if(v.intValue()>=2 && !foundVoice){
                //guild.getController().createVoiceChannel(Configuration.getCustomRolePrefix()+k.getName());
                System.out.println("Creating channel for " + k.getName());
            }
        });
        */
    }
}
