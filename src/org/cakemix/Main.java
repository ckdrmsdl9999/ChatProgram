package org.cakemix;

import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.cakemix.characterSheets.Changeling;
import org.cakemix.client.ChatClient;
import org.cakemix.server.ChatServer;
import org.cakemix.client.ClientFrame;
import org.cakemix.util.StatTracker;

/**
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
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
        // set the ui look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //set the log level
        Log.set(Log.LEVEL_DEBUG);

        // parse server args
        // check first if there are any to be parsed
        if (args.length > 0) {
            //set up the bools to hold the results, with logical defaults
            boolean client = true, //default to client
                    server = false, //with no server
                    nogui = false, //default to the server using gui
                    sheetOnly = false; //for debugging character sheet

            for (int i = 0; i < args.length; i++) {
                switch (args[i].toLowerCase()) {
                    case "server":
                    case "s":
                        server = true;
                        break;
                    case ("serveronly"):
                    case ("so"):
                        server = true;
                        client = false;
                        break;
                    case ("nogui"):
                        nogui = true;
                        break;
                    case ("sheet"):
                        sheetOnly = true;
                        break;
                }

            }
            if (sheetOnly) {
                new Changeling();
            } else {
                if (server) {
                    try {
                        new ChatServer(nogui);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (client) {
                    new ClientFrame();
                }
            }
        } else // if no args to be parsed
        {
            new ClientFrame();
        }
    }
}
