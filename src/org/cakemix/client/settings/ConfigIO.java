/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.client.settings;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cakemix.Network.ChatMessage;

/**
 *
 * @author cakemix
 */
public class ConfigIO {

    // Storage for the settings
    private String[] chat;
    private Color backgroundColor = Color.white;

    public ConfigIO() {
        // load the reader to read the config file
    }

    /**
     * Load settings from A file
     */
    public void loadSettings( FileReader file ) throws IOException {
        // try to open the file
        BufferedReader reader = new BufferedReader(file);

        // create a string to hold the current line
        String cur;
        // create a temporary array list to hold all the settings
        ArrayList<String> tmp = new ArrayList<>(ChatMessage.NUM_TYPE);
        // while there is a line coming in, loop
        while ( (cur = reader.readLine()) != null ) {
            // trim starting and ending white spaces
            // and add it to the array list
            tmp.add(cur.trim().toLowerCase());
        }
        // save the settings to proper array storage
        String[] settings = new String[tmp.size()];
        for ( int i = 0; i < settings.length; i++ ) {
            settings[i] = tmp.get(i);
        }
        // close the file reader
        reader.close();

        populateSettings(settings);
    }

    private void populateSettings( String[] settings ) {
        chat = new String[ChatMessage.NUM_TYPE];

        for ( int i = 0; i < settings.length; i++ ) {
            switch ( settings[i] ) {
                case "[chat]":
                    // move to the next string
                    i++;
                    // continue to loop through the settings
                    while (i < settings.length && settings[i].charAt(0) != '[' ) {
                        switch ( settings[i].split("=")[0] ) {
                            case "alias":
                                chat[ChatMessage.TYPE_ALIAS] =
                                        settings[i].split("=")[1];
                                break;
                            case "all":
                                chat[ChatMessage.TYPE_ALL] =
                                        settings[i].split("=")[1];
                                break;
                            case "announce":
                                chat[ChatMessage.TYPE_ANNOUNCE] =
                                        settings[i].split("=")[1];
                                break;
                            case "description":
                                chat[ChatMessage.TYPE_DESCRIPTION] =
                                        settings[i].split("=")[1];
                                break;
                            case "emote":
                                chat[ChatMessage.TYPE_EMOTE] =
                                        settings[i].split("=")[1];
                                break;
                            case "off":
                                chat[ChatMessage.TYPE_OFF_TOPIC] =
                                        settings[i].split("=")[1];
                                break;
                            case "sender":
                                chat[ChatMessage.TYPE_SENDER] =
                                        settings[i].split("=")[1];
                                break;
                            case "whisper":
                                chat[ChatMessage.TYPE_WHISPER] =
                                        settings[i].split("=")[1];
                                break;
                            // Background is slightly different
                            case "background":
                                backgroundColor =
                                        Color.decode(settings[i].split("=")[1]);
                                break;
                        }
                        i++;
                    }
                    break; // End of [chat] header
            }
            if (i >= settings.length){
                break;
            }
        }
    }

    public String[] getChatSettings() {
        return chat;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    /**
     * Save the settings to a file
     */
    public void saveSettings( ChatSettings settings ) throws IOException {
        saveSettings(settings, new File("chatConfig.ini"));
    }

    /**
     * Save the settings to a Specific file
     *
     * @param file File to save to
     */
    public void saveSettings( ChatSettings settings, File file ) throws
            IOException {

        FileWriter configWriter = new FileWriter(file);

        // First off write the chat settings
        configWriter.write("[chat]" + '\n');
        // Write out the background color
        configWriter.write(
                "background=" + settings.getBackgroundColorString() + '\n');
        for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {
            configWriter.write(
                    ChatMessage.getMessageTypeName(i) + "="
                    + settings.getMessageAttributes()[i].toString() + '\n');
        }

        //flush the buffers and close the file
        configWriter.flush();
        configWriter.close();
    }
}
