package commands;

import java.lang.InterruptedException;
import commands.CustomRole.ColorChangeCommand;
import commands.CustomRole.CustomRole;

import java.awt.*;
import java.awt.color.*;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utility.Command;
import utility.CommandContainer;

import java.util.Random;

/**
 * Created by Adair on 02/09/17.
 */
public class RainbowCommand implements Command  {
    @Override
    public boolean called(CommandContainer container) {
        return false;
    }

    @Override
    public void action(CommandContainer info) {
        Role role = CustomRole.findOrCreateRole(info);
        for (int i = 0; true ; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();
                    while (sb.length() < 6) {
                        sb.append(Integer.toHexString(random.nextInt()));
                    }
                    sb.insert(0,"#");
                    sb.setLength(7);
                    ColorChangeCommand.changeColor(role,sb.toString());
                    try {
                        this.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.run();
        }
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
