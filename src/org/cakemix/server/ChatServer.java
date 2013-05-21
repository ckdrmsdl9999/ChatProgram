package org.cakemix.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import org.cakemix.*;
import org.cakemix.Network.*;

public class ChatServer {

    // The Kryonet Server at the heart
    Server server;
    // The Visual log (move this)
    JList messageList;
    // welcome message send to all users on connect
    // Make this customisable in a settings file or menu or something
    ChatMessage welcome = new ChatMessage("Welcome to the Server,"
            + "type /help to get a list of commands.");
    // Help message
    // same as above
    String help = "/help - Print this help page." + '\n'
            + "/roll [num] - Roll [num] d10 dice" + '\n'
            + "/{me or em} <action> - Sends <action> as emotive text"
            + "/alias <name> - Change Displayed Name to <name>";
    // GM Help
    // this gets ADDED to athe above in the case of a GM asking for help
    String gmHelp = "GM Commands" + '\n'
            + "--- To Be Implemented ---" + '\n'
            + "Standard Commands" + '\n';

    HashMap users;

    /*
     * Most of the code here is just a copy pasta from the example
     * It differes on the slash commands, and a few others at the moment
     * Working on creating my own implementation
     */
    public ChatServer() throws IOException {
        server = new Server() {
            protected Connection newConnection() {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new ChatConnection();
            }
        };

        //create the hashmap to store user details
        users = new HashMap();

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        org.cakemix.Network.register(server);

        server.addListener(new Listener() {
            public void received(Connection c, Object object) {
                // We know all connections for this server are actually ChatConnections.
                ChatConnection connection = (ChatConnection) c;



                if (object instanceof RegisterName) {
                    // get the values from the object
                    String name = ((RegisterName) object).name;
                    String display = ((RegisterName) object).displayName;
                    // trim the values
                    name = name.trim();
                    display = display.trim();

                    // use this to change the name
                    if (connection.name != null) {
                        if (name != null && name.length() != 0
                                && display != null && display.length() != 0) {
                            if (connection.name.equals(name)) {
                                if (!users.containsKey(name)) {
                                    users.put(name, display);
                                } else {
                                    // remove the old value
                                    users.remove(name);
                                    // insert the new value
                                    users.put(name, display);
                                }
                            }
                            return;
                        }
                        return;
                    }
                    if (name == null && name.length() == 0) {
                        return;
                    }
                    // Store the name on the connection.
                    // Check that no one else is using the name
                    if (!users.containsKey(name)) {
                        // Store the connections name
                        connection.name = name;
                        // if this is the first connection, set it to gm
                        connection.rank = ChatConnection.RANK_GM;
                        // check that the display name is valid
                        if (display != null && display.length() != 0) {
                            users.put(name, display);
                        } else {
                            users.put(name, name);
                        }
                    } else {
                        // find a number that isnt in use
                        // and apend that to the name
                        int i = 1;
                        while (users.containsKey(name + i)) {
                            i++;
                        }
                        connection.name = name + i;
                        // check that the display name is valid
                        if (display != null && display.length() != 0) {
                            users.put(name + i, display);
                        } else {
                            users.put(name + i, name);
                        }
                    }

                    // Send a "connected" message to everyone except the new client.
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.text = connection.name + " connected.";
                    chatMessage.sendTo = ChatMessage.ANNOUNCE;
                    // log the message in the server list
                    DefaultListModel model = (DefaultListModel) messageList.getModel();
                    model.addElement(chatMessage.text);
                    messageList.ensureIndexIsVisible(model.size() - 1);
                    server.sendToAllExceptTCP(connection.getID(), chatMessage);
                    server.sendToTCP(connection.getID(), welcome);
                    // Send everyone a new list of connection names.
                    updateNames();
                    return;
                }

                if (object instanceof ChatMessage) {
                    // Ignore the object if a client tries to chat before registering a name.
                    if (connection.name == null) {
                        return;
                    }
                    ChatMessage chatMessage = (ChatMessage) object;
                    // Ignore the object if the chat message is invalid.
                    String message = chatMessage.text;
                    if (message == null) {
                        return;
                    }
                    message = message.trim();
                    if (message.length() == 0) {
                        return;
                    }
                    // Check for command inpt
                    // Then pass the command input to the regex
                    if (message.charAt(0) == '/') {
                        //check if the connection has GM rights
                        if (connection.rank == ChatConnection.RANK_GM) {
                            // Run the gm regex
                            chatMessage = gmRegex(connection, message);
                        } else {
                            // Run the standard user regex
                            chatMessage = regex(message);
                        }
                        switch (chatMessage.sendTo) {
                            case (ChatMessage.ALL):
                                chatMessage.text = connection.name + ": "
                                        + chatMessage.text;
                                //log the message in the server list
                                DefaultListModel model = (DefaultListModel) messageList.getModel();
                                model.addElement(chatMessage.text);
                                messageList.ensureIndexIsVisible(model.size() - 1);
                                // send the message to clients
                                server.sendToAllTCP(chatMessage);
                                return;
                            case (ChatMessage.EMOTE):
                                chatMessage.text = connection.name + ""
                                        + chatMessage.text;
                                //log the message in the server list
                                model = (DefaultListModel) messageList.getModel();
                                model.addElement(chatMessage.text);
                                messageList.ensureIndexIsVisible(model.size() - 1);
                                // send the message to clients
                                server.sendToAllTCP(chatMessage);
                                return;
                            case (ChatMessage.SENDER):
                                // Check if the GM is getting help
                                if (chatMessage.text.equals(help) &&
                                        connection.rank == ChatConnection.RANK_GM){
                                    // if they are, send them gm help instead
                                    chatMessage.text = gmHelp + chatMessage.text;
                                }
                                // log the command output
                                model = (DefaultListModel) messageList.getModel();
                                model.addElement(connection.name + "::" + chatMessage.text);
                                // send output to user
                                server.sendToTCP(connection.getID(), chatMessage);
                                return;
                            case (ChatMessage.ALIAS):
                                // change the display name
                                if (connection.name != null && connection.name.length() != 0
                                        && chatMessage.text != null && chatMessage.text.length() != 0) {

                                    if (!users.containsKey(connection.name)) {
                                        users.put(connection.name, chatMessage.text);
                                    } else {
                                        // remove the old value
                                        users.remove(connection.name);
                                        // insert the new value
                                        users.put(connection.name, chatMessage.text);
                                    }
                                    // Update the name list on all clients
                                    updateNames();
                                    return;
                                }

                        }
                    }
                    // Prepend the connection's name and send to everyone.
                    chatMessage.text = connection.name + ": " + message;
                    //log the message in the server list
                    DefaultListModel model = (DefaultListModel) messageList.getModel();
                    model.addElement(chatMessage.text);
                    messageList.ensureIndexIsVisible(model.size() - 1);
                    // send the message to clients
                    server.sendToAllTCP(chatMessage);
                    return;

                }
            }

            public void disconnected(Connection c) {
                ChatConnection connection = (ChatConnection) c;
                if (connection.name != null) {
                    // Announce to everyone that someone (with a registered name) has left.
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.text = connection.name + " disconnected.";
                    chatMessage.sendTo = ChatMessage.ANNOUNCE;
                    // Log the message in the server list
                    DefaultListModel model = (DefaultListModel) messageList.getModel();
                    model.addElement(chatMessage.text);
                    messageList.ensureIndexIsVisible(model.size() - 1);
                    // Send to all clients
                    server.sendToAllTCP(chatMessage);
                    updateNames();
                }
            }
        });
        server.bind(org.cakemix.Network.port);
        server.start();

        // Open a window to provide an easy way to stop the server.
        JFrame frame = new JFrame("Chat Server");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent evt) {
                server.stop();
            }
        });
        frame.getContentPane().add(new JScrollPane(messageList = new JList()));
        messageList.setModel(new DefaultListModel());
        frame.setSize(320, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void updateNames() {
        // Collect the names for each connection.
        Connection[] connections = server.getConnections();
        ArrayList names = new ArrayList(connections.length);
        ArrayList displays = new ArrayList(connections.length);
        for (int i = connections.length - 1; i >= 0; i--) {
            ChatConnection connection = (ChatConnection) connections[i];
            if (connection.rank == ChatConnection.RANK_GM){
                names.add(connection.name + " (GM)");
            }else{
            names.add(connection.name);}
            displays.add(users.get(connection.name));
        }
        // Send the names to everyone.
        UpdateNames updateNames = new UpdateNames();
        updateNames.names = (String[]) names.toArray(new String[names.size()]);
        updateNames.displays = (String[]) displays.toArray(new String[displays.size()]);
        server.sendToAllTCP(updateNames);
    }

    protected ChatMessage gmRegex(ChatConnection connection, String message){

        // if its not a gm command, fallback to standard regex
        return regex(message);
    }

    protected ChatMessage regex(String message) {
        // create a new chatmessage to return
        ChatMessage chatMessage = new ChatMessage();

        // convert everything to lower case
        // remove the /
        // split the string into its componant parts
        String[] command = message.toLowerCase().substring(1).split(" ");
        if (command[0].equals("roll")) {
            // Since everyone needs to see rolls
            chatMessage.sendTo = ChatMessage.ALL;

            // check if they filled out the command right
            // and check that they are not tring to roll
            // an invalid number of dice
            if (command.length > 1 && Integer.parseInt(command[1]) > 0) {
                // roll the dice and and store the results
                int[] result = roll(Integer.parseInt(command[1]), 10);
                // set the success and rerolls
                int success = 0, reroll = 0;
                // start filling up the chat message
                chatMessage.text = "rolled " + result.length + " d10's" + "{ ";
                for (int i = 0; i < result.length; i++) {
                    chatMessage.text += result[i] + " ";
                    if (result[i] > 6) {
                        success++;

                    } else {
                        if (result[i] == 0) {
                            success++;
                            reroll++;
                        }
                    }
                }

                // output success and rerolls
                chatMessage.text += "}" + '\n' + "With "
                        + success + " Successes and " + reroll + " rerolls";
                return chatMessage;
            }

            // if they fuck up, give them command instructions
            chatMessage.sendTo = ChatMessage.SENDER;
            chatMessage.text = "/roll [num] - Roll [num] d10 dice";
        }
        // emote
        if (command[0].equals("me") || command[0].equals("em")) {
            // Since everyone needs to see emotes
            chatMessage.sendTo = ChatMessage.EMOTE;
            // cut out the command
            // and return the rest of the string
            chatMessage.text = message.substring(4);
            return chatMessage;
        }
        if (command[0].equals("alias")) {
            // Check that there is a name to alias to
            if (message.length() > 7) {
                // Set message type
                chatMessage.sendTo = ChatMessage.ALIAS;
                // set the message (will be used to change name)
                chatMessage.text = message.substring(7);
                return chatMessage;
            }
        }

        // return the user their helpful message
        chatMessage.sendTo = ChatMessage.SENDER;
        chatMessage.text = help;

        return chatMessage;
    }

    /*
     * return the result of a dice roll
     * @param dice number of dice to roll
     * @param type number of sides on the dice
     */
    private int[] roll(int dice, int type) {
        // create the result
        int[] result = new int[dice];

        //create the random number generator
        java.util.Random rand = new java.util.Random();
        //roll the dice
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (type * rand.nextFloat());
        }

        //return the result
        return result;
    }

    // This holds per connection state.
    static class ChatConnection extends Connection {
        // Add a name to the connection

        public String name;
        // Add a rank to the connection
        public int rank = RANK_STANDARD;
        // Set up the static rank identifiers
        public static final int RANK_LISTENER = 0;
        public static final int RANK_STANDARD = 1;
        public static final int RANK_GM = 2;
    }
}
