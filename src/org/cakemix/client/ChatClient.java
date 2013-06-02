/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.cakemix.Network;
import org.cakemix.Network.ChatMessage;
import org.cakemix.Network.RegisterName;
import org.cakemix.Network.UpdateNames;

/**
 *
 * @author cakemix
 */
public class ChatClient {

    // the Kryonet Client
    Client client;
    // the connection details
    String host, name;
    int port;
    //the Client frame that this client is attached too
    ClientFrame frame;

    public ChatClient() {
    }

    public ChatClient( String[] connectionDetails, ClientFrame frame ) {
        // create a new network client
        client = new Client();

        // Populate the connection details
        host = connectionDetails[0];
        name = connectionDetails[1];
        port = Network.port;

        // attach the frame
        this.frame = frame;

        //setup the client settings
        setupClient(client);

        // connect to the server
        connect();

    }

    /**
     * Connect to a server
     */
    protected void connect() {
        // connect to the server using details above
        try {
            client.connect(5000, host, port);
            // Server communication after connection can go here, or in Listener#connected().
        } catch ( IOException ex ) {
            //ex.printStackTrace();
            //System.exit(1);
            JOptionPane.showMessageDialog(frame,
                    "Error: Connection could not be made." + '\n'
                    + "Check the details and try again.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Remove the current client
     */
    protected void disconnect() {
        // close and stop the current client, if its started
        if ( client != null ) {
            client.close();
            client.stop();
        }
    }

    /**
     * Request a change of Display Name (gui form)
     */
    protected void requestNameChange( String name ) {
        RegisterName registerName = new RegisterName();
        registerName.name = this.name;
        registerName.displayName = name;
        client.sendTCP(registerName);
    }

    /**
     * expose the underlying network client
     */
    protected Client getClient() {
        return client;
    }

    /**
     *
     * Setup a kryo client
     */
    private void setupClient( final Client client) {
        client.start();

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(client);

        //add network listeners
        client.addListener(new Listener() {
            // for connecting to a server
            @Override
            public void connected( Connection connection ) {
                // create a register name packet
                RegisterName registerName = new RegisterName();
                // set the names
                registerName.name = name;
                registerName.displayName = name;
                // send the packet
                client.sendTCP(registerName);

            }

            // for packets received from the server
            @Override
            public void received( Connection connection, Object object ) {

                if ( object instanceof UpdateNames ) {
                    UpdateNames updateNames = (UpdateNames) object;
                    frame.setNames(updateNames);
                    return;
                }

                if ( object instanceof ChatMessage ) {
                    ChatMessage chatMessage = (ChatMessage) object;
                    frame.addMessage(chatMessage);
                    return;
                }
                if (object instanceof RegisterName){
                    // create a register name packet
                RegisterName registerName = new RegisterName();
                // set the names
                String name = JOptionPane.showInputDialog(frame, "Name not available!" + '\n' +
                        "Please enter new name.", "", JOptionPane.INFORMATION_MESSAGE);
                registerName.name = name;
                registerName.displayName = name;
                // send the packet
                client.sendTCP(registerName);
                }
            }

            @Override
            public void disconnected( Connection connection ) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Closing the frame calls the close listener which will stop the client's update thread.
                        client.close();
                    }
                });
            }
        });


    }
}