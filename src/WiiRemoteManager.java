
import java.util.ArrayList;
import wiiremotej.WiiRemote;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mwilkerson
 */
public class WiiRemoteManager {
    ArrayList<WiiRemoteSet> remoteSets = new ArrayList<WiiRemoteSet>();

    public WiiRemote getRemote( int index ) {
        return remoteSets.get(index).getRemote();
    }

    public WiiRemote addRemote( WiiRemote wiiRemote ) {

        for( WiiRemoteSet wiiRemoteSet : remoteSets ) {
            if( wiiRemoteSet.getRemote() == wiiRemote ) {
                break; // TOOD: Right?
            }
        }

        remoteSets.add( new WiiRemoteSet( wiiRemote ) );
        return wiiRemote;
    }

    public NetLogoWiiRemoteListener getListener( int index ) {
        return remoteSets.get(index).getListener();
    }

    public NetLogoWiiRemoteListener getListener( WiiRemote remote ) {
        for( WiiRemoteSet wiiRemoteSet : remoteSets ) {
            if( wiiRemoteSet.getRemote() == remote ) {
                return wiiRemoteSet.getListener();
            }
        }
        return null;
    }
    
    public int getRemoteCount() {
        cleanOut();
        return remoteSets.size();
    }

    public void cleanOut() {
        for( WiiRemoteSet wiiRemoteSet : remoteSets ) {
            if( ! wiiRemoteSet.getRemote().isConnected() ) {
                remoteSets.remove( wiiRemoteSet );
            }
        }
    }
    

    public class WiiRemoteSet {
        WiiRemote remote;
        NetLogoWiiRemoteListener listener;

        WiiRemoteSet( WiiRemote remote ) {
            this.remote = remote;
            this.listener = new NetLogoWiiRemoteListener();
            remote.addWiiRemoteListener( listener );
        }

        public WiiRemote getRemote() { return remote; }
        public NetLogoWiiRemoteListener getListener() { return listener; }
    }
}
