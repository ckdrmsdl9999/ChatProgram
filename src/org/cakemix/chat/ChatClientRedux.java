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

public final class ChatClientRedux {

    Client client;
    String host, port;
    String name;

    public ChatClientRedux(){}

    public ChatClientRedux(String[] connectionDetails, ChatFrameRedux chatFrame) {
        client = new Client();

        host = connectionDetails[0];
        port = connectionDetails[1];
        name = connectionDetails[2];

        //setup the client settings
        setupClient(client, chatFrame);

        connect();

    }

    protected void dispose() {
        // close and stop the current client, if its started
        if (client != null){
        client.close();
        client.stop();}
    }

    protected void requestNameChange(String name) {
        RegisterName registerName = new RegisterName();
        registerName.name = name;
        client.sendTCP(registerName);
    }

    protected Client getClient() {
        return client;
    }

    protected void connect() {
        // connect to the server using details above
        try {
            client.connect(5000, host, Integer.parseInt(port));
            // Server communication after connection can go here, or in Listener#connected().
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /*
     *
     * Setup a kryo client
     */
    protected void setupClient(final Client client, final ChatFrameRedux chatFrame) {
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
