package org.cakemix.chat;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import org.cakemix.chat.Network.ChatMessage;
import org.cakemix.chat.Network.RegisterName;
import org.cakemix.chat.Network.UpdateNames;

public final class ChatClient {

    ChatFrame chatFrame;
    Client client;
    String host, port;
    String name;

    public ChatClient() {
        client = new Client();

        //setup the client settings
        setupClient(client);

        //popup get connection details
        // sets host and name
        String[] temp = Network.getConnectionDetails();

        host = temp[0];
        port = temp[1];
        name = temp[2];

        // All the ugly Swing stuff is hidden in ChatFrame so it doesn't clutter the KryoNet example code.
        chatFrame = new ChatFrame(host);

        setChatListeners();
        chatFrame.setVisible(true);

        connect();

    }

    protected void requestNameChange(String name) {
        RegisterName registerName = new RegisterName();
        registerName.name = name;
        client.sendTCP(registerName);
    }

    protected void setChatListeners() {
        //this is called when new connection button is clicked
        chatFrame.createNewConnection(new Runnable() {
            public void run() {
                // close and stop the current
                // client
                client.close();
                client.stop();
                //create a new client
                client = new Client();
                //set up the new client
                setupClient(client);
                //get the connection details
                //and connect
                getConnectionDetails();
                connect();
            }
        });
        // This listener is called when the send button is clicked.
        chatFrame.setSendListener(new Runnable() {
            public void run() {
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.text = chatFrame.getSendText();
                client.sendTCP(chatMessage);
            }
        });
        // This listener is called when the chat window is closed.
        chatFrame.setCloseListener(new Runnable() {
            public void run() {
                client.stop();
            }
        });
    }

    protected void connect() {
        // We'll do the connect on a new thread so the ChatFrame can show a progress bar.
        // Connecting to localhost is usually so fast you won't see the progress bar.
        new Thread("Connect") {
            public void run() {
                try {
                    client.connect(5000, host, Network.port);
                    // Server communication after connection can go here, or in Listener#connected().
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }

    protected void getConnectionDetails() {
        // Request the host from the user.
        String input = (String) JOptionPane.showInputDialog(null, "Server Address:",
                "Connect to chat server", JOptionPane.QUESTION_MESSAGE,
                null, null,
                "localhost");
        if (input == null || input.trim().length() == 0) {
            System.exit(1);
        }
        host = input.trim();

        input = (String) JOptionPane.showInputDialog(null, "Server Port:",
                "Connect to chat Server", JOptionPane.QUESTION_MESSAGE, null,
                null, "5000");
        if (input == null || input.trim().length() == 0) {
            System.exit(1);
        }
        port = input.trim();

        // Request the user's name.
        input = (String) JOptionPane.showInputDialog(null, "Display Name:",
                "Connect to chat server", JOptionPane.QUESTION_MESSAGE, null,
                null, "Test");
        if (input == null || input.trim().length() == 0) {
            System.exit(1);
        }
        name = input.trim();
    }

    /*
     *
     * Setup a kryo client
     */
    protected void setupClient(final Client client) {
        client.start();

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(client);

        client.addListener(new Listener() {
            public void connected(Connection connection) {
                RegisterName registerName = new RegisterName();
                registerName.name = name;
                client.sendTCP(registerName);
            }

            public void received(Connection connection, Object object) {
                if (object instanceof UpdateNames) {
                    UpdateNames updateNames = (UpdateNames) object;
                    chatFrame.setNames(updateNames.names);
                    return;
                }

                if (object instanceof ChatMessage) {
                    ChatMessage chatMessage = (ChatMessage) object;
                    chatFrame.addMessage(chatMessage.text);
                    return;
                }
            }

            public void disconnected(Connection connection) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        // Closing the frame calls the close listener which will stop the client's update thread.
                        client.close();
                    }
                });
            }
        });


    }
}
