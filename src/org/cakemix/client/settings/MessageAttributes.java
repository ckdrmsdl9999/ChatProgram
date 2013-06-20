/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.client.settings;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.cakemix.Network.ChatMessage;

/**
 *
 * @author cakemix
 */
public class MessageAttributes {

    public SimpleAttributeSet attributes;

    public MessageAttributes() {
        attributes = new SimpleAttributeSet();
    }

    public void setFont() {
    }

    public void setForeground( Color color ) {
        StyleConstants.setForeground(attributes, color);
    }

    public void setBold( boolean flag ) {
        StyleConstants.setBold(attributes, flag);
    }

    public void setItalic( boolean flag ) {
        StyleConstants.setItalic(attributes, flag);
    }

    /*
     * Load the attributes from string
     */
    public MessageAttributes fromString( String input ) {

        // if something goes wrong, load defaults
        try {

            //Split the input up to make it usable
            String[] config = input.split(";");

            //first, set the foreground
            setForeground(Color.decode(config[0]));
            // then bold
            if ( config[1].equalsIgnoreCase("true")
                    || config[1].equalsIgnoreCase("false") ) {
                setBold(Boolean.valueOf(config[1]));
            } else {
                throw new Exception();
            }
            // finally itallic
            if ( config[2].equalsIgnoreCase("true")
                    || config[2].equalsIgnoreCase("false") ) {
                setItalic(Boolean.valueOf(config[2]));
            } else {
                throw new Exception();
            }
            return this;
        } catch ( Exception e ) {
            // Print message to error
            System.err.println(
                    "Error loading string formatting, failing back to defaults");
            setForeground(Color.black);
            setBold(false);
            setItalic(false);
            return this;
        }
    }

    /*
     * Override how to output the object as a string
     * Used for writing to a file
     */
    @Override
    public String toString() {
        // create the string to hold the output
        String output = null;
        /*
         * add the foreground colour to the string
         * ignore the first hex pair (alpha channel) as its not needed
         * add the # to the beginning (so it will decode), and the ; to the end
         * so it will parse when loaded
         */
        output = "#" + Integer.toHexString(
                StyleConstants.getForeground(attributes)
                .getRGB()).substring(2) + ";"
                // then add bold, and finally itallic
                + StyleConstants.isBold(attributes) + ";"
                + StyleConstants.isItalic(
                attributes);

        return output;
    }

    /**
     * Get the Colour
     */
    public Color getColor() {
        return StyleConstants.getForeground(attributes);
    }

    /**
     * Get the Colours string value
     */
    public String getColorString() {
        /**
         * to get the colour as a string,
         * We use Integer to format it correctly
         * StyleConstants to get the color in question
         * then call .getRGB to get the color red green blue values
         * then chop off the first 2 characters, because we don't need alpha
         * all beginning with a hash because it needs one to parse
         */
        return "#" + Integer.toHexString(
                StyleConstants.getForeground(attributes)
                .getRGB()).substring(2);
    }

    /**
     * Get Bold
     */
    public boolean getBold() {
        return StyleConstants.isBold(attributes);
    }

    /**
     * Get Itallic
     */
    public boolean getItalic() {
        return StyleConstants.isItalic(attributes);
    }

    public static MessageAttributes[] setToDefalts() {
        MessageAttributes[] msgAttr = new MessageAttributes[ChatMessage.NUM_TYPE];
        msgAttr[ChatMessage.TYPE_ALIAS] = new MessageAttributes().fromString(
                "#000000;false;true");
        msgAttr[ChatMessage.TYPE_ALL] = new MessageAttributes().fromString(
                "#000000;false;false");
        msgAttr[ChatMessage.TYPE_ANNOUNCE] = new MessageAttributes().fromString(
                "#8080ff;true;false");
        msgAttr[ChatMessage.TYPE_DESCRIPTION] = new MessageAttributes().fromString(
                "#000000;true;true");
        msgAttr[ChatMessage.TYPE_EMOTE] = new MessageAttributes().fromString(
                "#ff8040;false;false");
        msgAttr[ChatMessage.TYPE_OFF_TOPIC] = new MessageAttributes().fromString(
                "#ff4040;false;true");
        msgAttr[ChatMessage.TYPE_SENDER] = new MessageAttributes().fromString(
                "#000000;true;false");
        msgAttr[ChatMessage.TYPE_WHISPER] = new MessageAttributes().fromString(
                "#bb00bb;false;false");
        return msgAttr;
    }

    public static MessageAttributes[] setToConsole() {
        MessageAttributes[] msgAttr = new MessageAttributes[ChatMessage.NUM_TYPE];
        msgAttr[ChatMessage.TYPE_ALIAS] = new MessageAttributes().fromString(
                "#00ff00;false;true");
        msgAttr[ChatMessage.TYPE_ALL] = new MessageAttributes().fromString(
                "#00ff00;false;false");
        msgAttr[ChatMessage.TYPE_ANNOUNCE] = new MessageAttributes().fromString(
                "#8080ff;true;false");
        msgAttr[ChatMessage.TYPE_DESCRIPTION] = new MessageAttributes().fromString(
                "#00ff00;true;true");
        msgAttr[ChatMessage.TYPE_EMOTE] = new MessageAttributes().fromString(
                "#ff8040;false;false");
        msgAttr[ChatMessage.TYPE_OFF_TOPIC] = new MessageAttributes().fromString(
                "#ff4040;false;true");
        msgAttr[ChatMessage.TYPE_SENDER] = new MessageAttributes().fromString(
                "#00ff00;true;false");
        msgAttr[ChatMessage.TYPE_WHISPER] = new MessageAttributes().fromString(
                "#bb00bb;false;false");
        return msgAttr;
    }
}
