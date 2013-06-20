package org.cakemix;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import javax.swing.JOptionPane;

// This class is a convenient place to keep things common to both the client and server.
public class Network {

    public static final int port = 5000;

    // This registers objects that are going to be sent over the network.
    static public void register( EndPoint endPoint ) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(RegisterName.class);
        kryo.register(String[].class);
        kryo.register(UpdateNames.class);
        kryo.register(ChatMessage.class);
        kryo.register(int[].class);
    }

    static public String[] getConnectionDetails() {
        // Request the host from the user.
        String[] output = new String[2];

        String input = (String) JOptionPane.showInputDialog(null,
                "Server Address:",
                "Connect to chat server", JOptionPane.QUESTION_MESSAGE,
                null, null,
                "localhost");
        if ( input == null || input.trim().length() == 0 ) {
            System.exit(1);
        }
        output[0] = input.trim();

        // Request the user's name.
        input = (String) JOptionPane.showInputDialog(null, "Display Name:",
                "Connect to chat server", JOptionPane.QUESTION_MESSAGE, null,
                null, "Test");
        if ( input == null || input.trim().length() == 0 ) {
            System.exit(1);
        }
        output[1] = input.trim();

        return output;
    }

    static public class RegisterName {

        public String name, displayName;
    }

    static public class UpdateNames {

        public String[] names, displays;
        public int[] rank;
    }

    static public class ChatMessage {

        public static final int TYPE_ALL = 0;
        public static final int TYPE_SENDER = 1;
        public static final int TYPE_EMOTE = 2;
        public static final int TYPE_ALIAS = 3;
        public static final int TYPE_ANNOUNCE = 4;
        public static final int TYPE_DESCRIPTION = 5;
        public static final int TYPE_OFF_TOPIC = 6;
        public static final int TYPE_WHISPER = 7;
        public static final int NUM_TYPE = 8;
        public String text, target = null;
        public int messageType = TYPE_ALL;

        public ChatMessage() {
        }

        public ChatMessage( String text ) {
            this.text = text;
        }

        public ChatMessage( String text, int sendTo ) {
            this.text = text;
            this.messageType = sendTo;
        }

        public ChatMessage( String text, int sendTo, String target ) {
            this.text = text;
            this.messageType = sendTo;
            this.target = target;
        }

        public static String getMessageTypeName( int messageType ) {
            // take the input and return its name
            switch ( messageType ) {
                case TYPE_ALL:
                    return "all";
                case TYPE_ALIAS:
                    return "alias";
                case TYPE_ANNOUNCE:
                    return "announce";
                case TYPE_DESCRIPTION:
                    return "description";
                case TYPE_EMOTE:
                    return "emote";
                case TYPE_OFF_TOPIC:
                    return "off";
                case TYPE_SENDER:
                    return "sender";
                case TYPE_WHISPER:
                    return "whisper";
            }
            // return null as an error
            return null;
        }
    }

    // This holds per connection state.
    static public class ChatConnection extends Connection {
        // Add a name to the connection

        public String name;
        // Add a rank to the connection
        public int rank = RANK_PLAYER;
        // Set up the static rank identifiers
        public static final int RANK_LISTENER = 0;
        public static final int RANK_PLAYER = 1;
        public static final int RANK_GM = 2;
    }
}
