package misc;

import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

import java.util.ArrayList;

/**
 * Created by Adair on 02/14/17.
 */



public class Deleter {
    static ArrayList<VoiceChannel> waitToDelete = new ArrayList<>();

    public static void shouldDelete(VoiceChannel channel){
        if(waitToDelete.contains(channel) && channel.getMembers().size() == 0){
            channel.delete().queue();
        }
    }

    public static void addToDeleteList(VoiceChannel channel) {
        waitToDelete.add(channel);
        shouldDelete(channel);
    }
}
