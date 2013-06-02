/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.client.settings;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author cakemix
 */
public class MessageStyle {

    public SimpleAttributeSet attributes;

    public MessageStyle() {
        attributes = new SimpleAttributeSet();
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
    public MessageStyle fromString( String input ) {

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
    public String getColorString(){
        return "#" + Integer.toHexString(
                StyleConstants.getForeground(attributes)
                .getRGB()).substring(2);
    }

    /**
     * Get Bold
     */
    public boolean getBold(){
        return StyleConstants.isBold(attributes);
    }

    /**
     * Get Itallic
     */
    public boolean getItalic(){
        return StyleConstants.isItalic(attributes);
    }
    /**
     * form to change chat settings via gui
     */
    public String stylePicker() {

        return null;
    }
}
