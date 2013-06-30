/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.client.settings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import org.cakemix.Network.ChatMessage;
import org.cakemix.client.ClientFrame;

/**
 *
 * @author cakemix
 */
public class StylePickerFrame extends JFrame implements ActionListener,
        ItemListener {

    // Store the styles that currently exist
    ChatSettings settings;
    // Create the form elements array
    // there needs to be the same ammount as there is
    // number of message types, one of each element
    // per message type
    JButton[] colour = new JButton[ChatMessage.NUM_TYPE];
    JCheckBox[] bold = new JCheckBox[ChatMessage.NUM_TYPE];
    JCheckBox[] italic = new JCheckBox[ChatMessage.NUM_TYPE];
    JTextPane[] example = new JTextPane[ChatMessage.NUM_TYPE];
    Color background;
    ClientFrame owner;

    public StylePickerFrame( ChatSettings settings, ClientFrame owner ) throws
            BadLocationException {
        // set the name and create the frame
        super("Style Picker");
        // Dispose the JFrame when it closes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // set the styles array
        this.settings = settings;

        // set the owner
        this.owner = owner;

        // Populate the form
        createGroupLayout();

        // Pack the form and make it visible
        pack();
        setVisible(true);

    }

    /**
     * Origonal Code (group layout)
     */
    private void createGroupLayout() throws BadLocationException {
        // alot of this is copy pasta from ClientFrame

        // Create a new group layout
        GroupLayout layout = new GroupLayout(getContentPane());
        // Set auto-create gaps, makes it look neater
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // tell the panel to use the layout
        getContentPane().setLayout(layout);



        // create all the componants and set them to their current values
        for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {

            // Create all the check boxes
            // Setting them all to match the current styles
            bold[i] = new JCheckBox("Bold");
            bold[i].setSelected(settings.getMessageAttributes()[i].getBold());
            bold[i].addItemListener(this);
            // Use this to identify which checkbox calls the item changed event
            bold[i].setName('b' + String.valueOf(i));
            italic[i] = new JCheckBox("Itallic");
            italic[i].setSelected(settings.getMessageAttributes()[i].getItalic());
            italic[i].addItemListener(this);
            // same as bold name
            italic[i].setName('i' + String.valueOf(i));
            // Create the example message pane
            example[i] = new JTextPane();
            example[i].setBackground(settings.getBackgroundColor());

            // Create an empty string for the message pane
            String name = "";

            // find out which style it is, and create the identifier
            switch ( i ) {
                case ChatMessage.TYPE_ALIAS:
                    name = "Alias";
                    break;
                case ChatMessage.TYPE_ALL:
                    name = "Standard";
                    break;
                case ChatMessage.TYPE_ANNOUNCE:
                    name = "Announcement";
                    break;
                case ChatMessage.TYPE_DESCRIPTION:
                    name = "Descripion Block";
                    break;
                case ChatMessage.TYPE_EMOTE:
                    name = "Emote";
                    break;
                case ChatMessage.TYPE_OFF_TOPIC:
                    name = "Off Topic";
                    break;
                case ChatMessage.TYPE_SENDER:
                    name = "Server Response";
                    break;
                case ChatMessage.TYPE_WHISPER:
                    name = "Player Whisper";
                    break;
            }

            // Get the current styled doc for the message box
            example[i].getStyledDocument().insertString(0,
                    "An example of " + name, settings.getMessageAttributes()[i].attributes);
            // Make sure the user cannot change it
            example[i].setEditable(false);
            // Create the colour button
            colour[i] = new JButton(name);
            // add teh action listener to the button
            colour[i].addActionListener(this);
        }

        // create a button to reset colours to programmed default
        JButton defaults = new JButton("Defaults");
        defaults.addActionListener(this);

        // create button for Console Style
        JButton alt = new JButton("Console");
        alt.addActionListener(this);

        // create the Background Colour button
        JButton bgColor = new JButton("Background Colour");
        bgColor.addActionListener(this);


        // Create the horizontal group
        // this gets a bit funky cus were looping through the items
        // create root groups
        ParallelGroup horizGroup = layout.createParallelGroup(
                GroupLayout.Alignment.TRAILING);
        SequentialGroup vertGroup = layout.createSequentialGroup();

        for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {
            // Then create the sub groups
            // first the horis
            SequentialGroup sg = layout.createSequentialGroup();

            // add the parallel to the main group
            horizGroup.addGroup(sg);
            sg.addComponent(colour[i]);
            sg.addComponent(bold[i]);
            sg.addComponent(italic[i]);
            sg.addComponent(example[i]);

            // then the  vert
            // Then create the parallel group
            ParallelGroup pg = layout.createParallelGroup();

            // add the parallel to the main group
            vertGroup.addGroup(pg);
            pg.addComponent(colour[i]);
            pg.addComponent(bold[i]);
            pg.addComponent(italic[i]);
            pg.addComponent(example[i]);

        }

        // Add the background & default
        horizGroup.addGroup(layout.createSequentialGroup()
                .addComponent(alt)
                .addComponent(defaults)
                .addComponent(bgColor));
        vertGroup.addGroup(layout.createParallelGroup()
                .addComponent(alt)
                .addComponent(defaults)
                .addComponent(bgColor));


        // link the button sizes and the example text sizes
        layout.linkSize(SwingConstants.HORIZONTAL, colour);
        layout.linkSize(SwingConstants.HORIZONTAL, example);

        // then assign the groups
        layout.setHorizontalGroup(horizGroup);
        layout.setVerticalGroup(vertGroup);
    }

    private void applyStyles() {
        // When the styles are changed
        // make sure it is replicated in the example text
        for ( int i = 0; i < settings.getMessageAttributes().length; i++ ) {
            // select all the text in the box
            example[i].selectAll();
            // update the message attributes of that box
            example[i].setCharacterAttributes(settings.getMessageAttributes()[i].attributes,
                    true);
            // deselect all text
            example[i].select(0, 0);
            //set the background colour
            example[i].setBackground(settings.getBackgroundColor());
        }

        // set styles in the owner as well
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                owner.setSettings(settings);
            }
        });
    }

    @Override
    public void actionPerformed( ActionEvent ae ) {
        //check if its the defaults button first off
        if ( ae.getActionCommand().equals("Defaults") ) {
            // if it does,set all message attributes to hard coded defautlts
            settings = ChatSettings.setToDefalts();
            //loop through components and change them to the new settings
            for ( int i = 0; i < settings.getMessageAttributes().length; i++ ) {
                bold[i].setSelected(settings.getMessageAttributes()[i].getBold());
                italic[i].setSelected(settings.getMessageAttributes()[i].getItalic());
            }
            // apply the new settings
            applyStyles();
            // after this is all done, return rather than go through the loop
            return;
        }

        // oterwise check if its the alternate
        if ( ae.getActionCommand().equals("Console") ) {
            // if it does,set all message attributes to hard coded defautlts
            settings = ChatSettings.setToConsole();
            //loop through components and change them to the new settings
            for ( int i = 0; i < settings.getMessageAttributes().length; i++ ) {
                bold[i].setSelected(settings.getMessageAttributes()[i].getBold());
                italic[i].setSelected(settings.getMessageAttributes()[i].getItalic());
            }
            // apply the new settings
            applyStyles();
            // after this is all done, return rather than go through the loop
            return;
        }

        //if not, then check if its the background colour button
        if ( ae.getActionCommand().equals("Background Colour") ) {
            // call the color chooser in a dialoge
            // and temporarily store the colour in a var
            Color c = JColorChooser.showDialog(
                    new JDialog(), "Choose your Colour",
                    settings.getBackgroundColor());
            // if the user chose a colour (ie didnt hit cancel)
            if ( c != null ) {
                // update the correct message attributes
                settings.setBackgroundColor(c);
            }
        }

        // called when a button in pressed
        // loop through the buttons
        for ( int i = 0; i < settings.getMessageAttributes().length; i++ ) {
            // if the action command is the same as the button text
            // set up the colour chooser
            if ( ae.getActionCommand().equals(colour[i].getText()) ) {
                // call the color chooser in a dialoge
                // and temporarily store the colour in a var
                Color c = JColorChooser.showDialog(
                        new JDialog(), "Choose your Colour",
                        settings.getMessageAttributes()[i].getColor());
                // if the user chose a colour (ie didnt hit cancel)
                if ( c != null ) {
                    // update the correct message attributes
                    settings.getMessageAttributes()[i].setForeground(c);
                }
                // Break from the loop, so we dont make unneeded checks
                break;
            }
        }
        // after the loop has completed (or been broken from)
        // update the styles (do this no mater what, just to be safe ;))
        applyStyles();
    }

    @Override
    public void itemStateChanged( ItemEvent ie ) {
        // when a checkbox state is changed (ticked or unticked)
        // check that the caller IS a checkbox, no ugly errors this way
        if ( ie.getSource() instanceof JCheckBox ) {
            // store said checkbox in a var, make it easyer to get at
            JCheckBox src = (JCheckBox) ie.getSource();
            //get a copy of the current settings
            MessageAttributes[] msgAttr = settings.getMessageAttributes();
            // loop through all possible message types
            for ( int i = 0; i < msgAttr.length; i++ ) {
                // check if the second character of the name is the same as
                // the loop number
                if ( src.getName().substring(1).equalsIgnoreCase(String.valueOf(
                        i)) ) {
                    // if it is, check if its bold or italic
                    // denoted by a b or an i as the first character
                    if ( src.getName().charAt(0) == 'i' ) {
                        // if its itallic box, set the itallic to match the value
                        msgAttr[i].setItalic(src.isSelected());
                        break;
                    } else if ( src.getName().charAt(0) == 'b' ) {
                        // same as above but for bold
                        msgAttr[i].setBold(src.isSelected());
                        break;
                    }
                }
            }
            // Update the stored settings
            settings.updateAttributes(msgAttr);
            // once again, apply the updated settings
            applyStyles();
        }
    }
}
