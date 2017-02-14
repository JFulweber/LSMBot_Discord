import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import utility.CommandContainer;
import utility.Configuration;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Adair on 02/12/17.
 */
public class VoiceChannelManager {

    static HashMap<Guild,HashMap<Game, Integer>> gameCounts = new HashMap<>();


    public static void manageChangeEvent(Event event) {
        UserGameUpdateEvent gameChange = (UserGameUpdateEvent) event;
        Game noGame = Game.of("nogame");
        Guild guild = gameChange.getGuild();
        HashMap<Game, Integer> inner = new HashMap<>();

        if(gameCounts.containsKey(guild)){
            inner = new HashMap<>();
        }
        else{
            inner = new HashMap<Game, Integer>();
            gameCounts.put(guild, inner);
        }

        System.out.println("about to insert games");

        for (Member member : gameChange.getGuild().getMembers()) {
            Game game = member.getGame();
            if(game != null) {
                System.out.println(game.getName());
                if (inner.containsKey(game)) {
                    inner.replace(game, inner.get(game).intValue() + 1);
                } else {
                    inner.put(game, 1);
                }
                gameCounts.replace(guild, inner);
            }
        }

        System.out.println("inserted games");

        for(Game game : gameCounts.get(guild).keySet()){
            boolean createdNow = false;
            String genName = Configuration.getGeneratedChannelPrefix()+game.getName();
            if(gameCounts.get(guild).get(game).intValue()>=2){
                List<VoiceChannel> channels = guild.getVoiceChannelsByName(genName,true);
                if(channels.size()>=1) {
                    System.out.println("already have channel");
                }else {
                    System.out.println("making channel now");
                    guild.getController().createVoiceChannel(Configuration.getGeneratedChannelPrefix() + game.getName()).queue();
                    createdNow = true;
                }
            }else{
                System.out.println(game.getName()+":"+gameCounts.get(guild).get(game));
            }
            List<VoiceChannel> channels = guild.getVoiceChannelsByName(genName,true);
            if(channels.size()>0) {
                VoiceChannel channel = channels.get(0);
                if (channel.getMembers().size() == 0 && !createdNow && gameCounts.get(guild).get(game).intValue()>=2) {
                    System.out.println("deleting channel");
                    System.out.println("currently " + gameCounts.get(guild).get(game).intValue() + "playing " + game.getName());
                    channel.delete().queue();
                }
            }
        }

    }
}