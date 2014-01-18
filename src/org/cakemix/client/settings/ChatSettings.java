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
     * Attributes for the different message types Array of message styles with
     * enough to hold the number of different chat
     *
     * message types
     */
    private MessageAttributes[] msgAttr =
            new MessageAttributes[ChatMessage.NUM_TYPE];
    // background colour for the text pane
    private Color backgroundColor = Color.white;

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
     * Look at setting up a settings specific class keep everything neat
     */
    public void setStyle(ConfigIO configIO) {

        // Set the attributes to defaults
        msgAttr = MessageAttributes.setToDefalts();
        // first try and load from file

        backgroundColor = configIO.getBackgroundColor();
        String[] chat = configIO.getChatSettings();
        // loop through the chat array and load settings
        for (int i = 0; i < ChatMessage.NUM_TYPE; i++) {
            if (chat[i] != null) {
                msgAttr[i].fromString(chat[i]);
            }
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

    void setBackgroundColor(Color c) {
        backgroundColor = c;
    }

    void updateAttributes(MessageAttributes[] msgAttr) {
        this.msgAttr = msgAttr;
    }
}
