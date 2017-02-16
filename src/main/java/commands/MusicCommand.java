package commands;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.managers.impl.AudioManagerImpl;
import utility.Command;
import utility.CommandContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fulwejam000 on 2/15/2017.
 */
public class MusicCommand implements Command{

    static HashMap<Guild, AudioManager> audioManagerHashMap = new HashMap<>();

    @Override
    public boolean called(CommandContainer info) {
        return !(info.getArgs().length==0);
    }

    @Override
    public void action(CommandContainer info) {

        String[] args = new String[info.getArgs().length-1];
        String subCommand = info.getArgs()[0];
        for(int i = 1; i<args.length;i++){
            args[i] = info.getArgs()[i];
        }
        ArrayList<String> subCommands = new ArrayList<>();
        subCommands.add("p"); subCommands.add("s"); subCommands.add("c"); subCommands.add("l");
        Guild guild = ((GuildMessageReceivedEvent) info.getEvent()).getGuild();
        Member member = ((GuildMessageReceivedEvent) info.getEvent()).getMember();

        AudioManager manager;
        if(!audioManagerHashMap.containsKey(guild)){
            audioManagerHashMap.put(guild, new AudioManagerImpl(guild));
        }
        manager = audioManagerHashMap.get(guild);

        if(subCommands.contains(subCommand)){
            if(subCommand.equals("p")){
                System.out.println("trying to join");
                if(findVoiceChannel(member)!=null){
                    manager.openAudioConnection(findVoiceChannel(member));
                }
                else{
                    ((GuildMessageReceivedEvent) info.getEvent()).getChannel().sendMessage("You need to be in a voice channel for me to join!");
                }
            }
            else if(subCommand.equals("l")){
                System.out.println("leaving");
                manager.closeAudioConnection();
            }
        }
        else{
            ((GuildMessageReceivedEvent) info.getEvent()).getMessage().getChannel().sendMessage(this.help());
        }

    }

    @Override
    public String help() {
        return "```" +
                "\n     MUSIC HELP" +
                "   Play: p, then a link or title of video" +
                "   Currently Playing: c" +
                "   Skip: s" +
                "   ```";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    public VoiceChannel findVoiceChannel(Member member){
        List<VoiceChannel> channels = member.getGuild().getVoiceChannels();
        for(VoiceChannel channel: channels){
            if(channel.getMembers().contains(member)) return channel;
        }
        return null;
    }

}
