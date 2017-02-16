package music;

import net.dv8tion.jda.core.audio.hooks.ConnectionListener;
import net.dv8tion.jda.core.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.core.entities.User;

/**
 * Created by Adair on 02/16/17.
 */
public class ConnectionListenerImpl implements ConnectionListener {
    @Override
    public void onPing(long l) {

    }

    @Override
    public void onStatusChange(ConnectionStatus connectionStatus) {

    }

    @Override
    public void onUserSpeaking(User user, boolean b) {

    }
}
