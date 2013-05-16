package org.cakemix;

import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import org.cakemix.server.ChatServer;
import org.cakemix.client.ClientFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cakemix
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Log.set(Log.LEVEL_DEBUG);
        if (args.length > 0) {
            if (args[0].equals("Server")) {
                try {
                    new ChatServer();
                    new ClientFrame();
                } catch (IOException e) {
                }
            } else if (args[0].equals("ServerOnly")) {
                try {
                    new ChatServer();
                } catch (IOException e) {
                }
            } else {
                new ClientFrame();
            }
        } else {
            new ClientFrame();
        }
    }
}
