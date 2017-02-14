import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import utility.CommandContainer;
import utility.Configuration;

import java.util.HashMap;

/**
 * Created by Adair on 02/12/17.
 */
public class VoiceChannelManager {

    static HashMap<Guild,HashMap<Game, Integer>> gameCounts = new HashMap<>();


    public static void manageChangeEvent(Event event) {
        UserGameUpdateEvent gameChange = (UserGameUpdateEvent) event;
        Game noGame = Game.of("nogame");
        Guild guild = gameChange.getGuild();
        HashMap<Game, Integer> inner = gameCounts.get(guild);
        if(gameCounts.containsKey(guild)){
            inner = gameCounts.get(guild);
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
            if(gameCounts.get(guild).get(game).intValue()>=2){
                guild.getController().createVoiceChannel(Configuration.getGeneratedChannelPrefix()+game.getName()).queue();
            }

        }

    }
}
