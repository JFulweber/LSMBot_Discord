package utility;

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import music.GuildMusicManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.managers.impl.AudioManagerImpl;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Owner on 3/2/2017.
 */

/** Contains useful information about a Guild. It's voicechannels, manager, etc.
 *  @param 
 */


public class GuildContainer {
    private GuildMusicManager guildMusicManager;
    private AudioManager audioManager;
    private Guild guild;
    private List<VoiceChannel> channels;

    //TODO: Give greater usage to this class. I.E. contain UserHashMap or other Guild based methods in here; they don't need their own class.
    public GuildContainer(Guild guild){
        this.guild = guild;
        this.guildMusicManager = new GuildMusicManager(new DefaultAudioPlayerManager());
        this.audioManager = new AudioManagerImpl(guild);
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

    public HashMap<User,Member> getMemberHashMap(){
        HashMap<User, Member> hashMembers = new HashMap<>();
        List<Member> members = guild.getMembers();
        for(Member member:members){
            hashMembers.put(member.getUser(),member);
        }
        return hashMembers;
    }

    public GuildMusicManager getGuildMusicManager() {
        return guildMusicManager;
    }

    public Guild getGuild() {
        return guild;
    }

    public List<VoiceChannel> getChannels() {
        return channels;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }
}
