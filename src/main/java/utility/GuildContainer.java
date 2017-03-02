package utility;

import music.GuildMusicManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;

import java.util.List;

/**
 * Created by Owner on 3/2/2017.
 */

/** Contains useful information about a Guild. It's voicechannels, manager, etc.
 *  @param 
 */


public class GuildContainer {
    private GuildMusicManager manager;
    private Guild guild;
    private List<VoiceChannel> channels;


    //TODO: Give greater usage to this class. I.E. contain UserHashMap or other Guild based methods in here; they don't need their own class.
    public GuildContainer(Guild guild, GuildMusicManager manager){
        this.guild = guild;
        this.manager = manager;
        this.channels = guild.getVoiceChannels();
    }

    public VoiceChannel findClosestChannel(String name){
        /*
        simple sorting algorithim based off of fitness to dermine closest channel based on how the name
        given fits into a substring of the original channel.
        TODO: Perhaps put in means of comparing all substrings of the name to the channelname to give a greater range of fitness?
        */
        int greatestFitness = 0;
        VoiceChannel greatestChannel = null;
        for(VoiceChannel channel: channels){
            String channelName = channel.getName();
            int combos = channelName.length()-name.length();
            int fitness = 0;
            for (int i = 0; i < combos ; i++) {
                String channelSub = channelName.substring(i,name.length());
                if(channelSub.equals(name)) fitness+=name.length();
                if(fitness>greatestFitness) {
                    greatestFitness = fitness;
                    greatestChannel = channel;
                }
            }
        }
        return greatestChannel;
    }
}
