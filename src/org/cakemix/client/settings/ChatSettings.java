/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.client.settings;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.cakemix.Network.ChatMessage;

/**
 *
 * @author cakemix
 */
public class ChatSettings {

    /**
     * Attributes for the different message types
     * Array of message styles with enough to hold the number of different chat
     *
     * message types
     */
    private MessageAttributes[] msgAttr =
            new MessageAttributes[ChatMessage.NUM_TYPE];
    // background colour for the text pane
    private Color backgroundColor = Color.white;
    // Config file reader
    private ConfigIO configIO = new ConfigIO();

    public MessageAttributes[] getMessageAttributes() {
        return msgAttr;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public String getBackgroundColorString() {
        return "#" + Integer.toHexString(
                backgroundColor.getRGB()).substring(2);
    }

    /**
     * Set the styles used in the chat log
     *
     * Look at setting up a settings specific class
     * keep everything neat
     */
    public void setStyle() {

        // Set the attributes to defaults
        msgAttr = MessageAttributes.setToDefalts();
        // first try and load from file

        try {


            configIO.loadSettings(new FileReader("chatConfig.ini"));

                    backgroundColor = configIO.getBackgroundColor();
            String[] chat = configIO.getChatSettings();
            // loop through the chat array and load settings
            for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {
                if ( chat[i] != null ) {
                    msgAttr[i].fromString(chat[i]);
                }
            }
        } catch ( IOException ex ) {
            // warn that there was no config found and that a new one
            // is being created
            new JOptionPane("Problem reading Config File, creating new one",
                    JOptionPane.OK_OPTION);
            Logger.getLogger(ConfigIO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    public void saveStyle() {
        try {
            configIO.saveSettings(this);
        } catch ( IOException ex ) {
            new JOptionPane("Problem writing config file." + '\n'
                    + "Defaults will be loaded at next run if no file exists.",
                    JOptionPane.OK_OPTION);
            Logger.getLogger(ConfigIO.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    public static ChatSettings setToDefalts() {
        // create new chat settings
        ChatSettings settings = new ChatSettings();
        //setall message attributes to default
        settings.msgAttr = MessageAttributes.setToDefalts();
        //set the background colour
        settings.backgroundColor = Color.white;
        return settings;
    }

    public static ChatSettings setToConsole() {
        // create new chat settings
        ChatSettings settings = new ChatSettings();
        //setall message attributes to default
        settings.msgAttr = MessageAttributes.setToConsole();
        //set the background colour
        settings.backgroundColor = Color.black;
        return settings;
    }

    void setBackgroundColor( Color c ) {
        backgroundColor = c;
    }

    void updateAttributes( MessageAttributes[] msgAttr ) {
        this.msgAttr = msgAttr;
    }
}
