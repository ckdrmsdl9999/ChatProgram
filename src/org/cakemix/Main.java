package org.cakemix;

import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.cakemix.characterSheets.Changeling;
import org.cakemix.server.ChatServer;
import org.cakemix.client.ClientFrame;
import org.cakemix.util.StatTracker;

/**
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
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        Log.set(Log.LEVEL_DEBUG);
        if (args.length > 0) {
            if (args[0].toLowerCase().equals("Server")) {
                try {
                    new ChatServer();
                    new ClientFrame();
                } catch (IOException e) {
                }
            } else if (args[0].toLowerCase().equals("serveronly")) {
                try {
                    new ChatServer();
                } catch (IOException e) {
                }
            } else if (args[0].toLowerCase().equals("sheet")){
                new Changeling();
            }else if (args[0].toLowerCase().equals("tracker")){
                JFrame frame = new JFrame();
                frame.add(new StatTracker(5,3,1,1));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
            else{
                new ClientFrame();
            }
        } else {
            new ClientFrame();
        }
    }
}
