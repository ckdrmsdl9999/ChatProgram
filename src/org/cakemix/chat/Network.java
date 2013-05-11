package org.cakemix.chat;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import javax.swing.JOptionPane;

// This class is a convenient place to keep things common to both the client and server.
public class Network {
        static public final int port = 54555;

        // This registers objects that are going to be sent over the network.
        static public void register (EndPoint endPoint) {
                Kryo kryo = endPoint.getKryo();
                kryo.register(RegisterName.class);
                kryo.register(String[].class);
                kryo.register(UpdateNames.class);
                kryo.register(ChatMessage.class);
        }
static public String[] getConnectionDetails() {
        // Request the host from the user.
        String[] output = new String[3];

        String input = (String) JOptionPane.showInputDialog(null, "Server Address:",
                "Connect to chat server", JOptionPane.QUESTION_MESSAGE,
                null, null,
                "localhost");
        if (input == null || input.trim().length() == 0) {
            System.exit(1);
        }
        output[0] = input.trim();

        input = (String) JOptionPane.showInputDialog(null, "Server Port:",
                "Connect to chat Server", JOptionPane.QUESTION_MESSAGE, null,
                null, "5000");
        if (input == null || input.trim().length() == 0) {
            System.exit(1);
        }
        output[1] = input.trim();

        // Request the user's name.
        input = (String) JOptionPane.showInputDialog(null, "Display Name:",
                "Connect to chat server", JOptionPane.QUESTION_MESSAGE, null,
                null, "Test");
        if (input == null || input.trim().length() == 0) {
            System.exit(1);
        }
        output[2] = input.trim();

        return output;
    }

        static public class RegisterName {
                public String name;
        }

        static public class UpdateNames {
                public String[] names;
        }

        static public class ChatMessage {
                public static final int ALL = 0;
                public static final int SENDER = 1;
                public static final int EMOTE = 2;
                public String text;
                public int sendTo = ALL;
                public ChatMessage(){}
                public ChatMessage(String text){this.text=text;}
        }
}
