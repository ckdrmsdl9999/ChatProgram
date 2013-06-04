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
import javax.swing.text.BadLocationException;
import org.cakemix.Network.ChatMessage;

/**
 *
 * @author cakemix
 */
public class StylePickerFrame extends JFrame implements ActionListener,
        ItemListener {

    MessageStyle[] styles;
    JButton[] colour = new JButton[ChatMessage.NUM_TYPE];
    JCheckBox[] bold = new JCheckBox[ChatMessage.NUM_TYPE];
    JCheckBox[] italic = new JCheckBox[ChatMessage.NUM_TYPE];
    JTextPane[] example = new JTextPane[ChatMessage.NUM_TYPE];

    public StylePickerFrame( MessageStyle[] styles ) throws BadLocationException {
        // set the name and create the frame
        super("Style Picker");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.styles = styles;
        createGroupLayout();

        pack();

        setVisible(true);

    }

    /**
     * Newer card layout attempt
     *
     * @param styles
     */
    private void setCardLayout() {
        // create the card layout
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

            bold[i] = new JCheckBox("Bold");
            bold[i].setSelected(styles[i].getBold());
            bold[i].addItemListener(this);
            bold[i].setName('b' + String.valueOf(i));
            italic[i] = new JCheckBox("Itallic");
            italic[i].setSelected(styles[i].getItalic());
            italic[i].addItemListener(this);
            italic[i].setName('i' + String.valueOf(i));
            example[i] = new JTextPane();

            String name = "";

            // find out which style it is, and set the text for the example
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
            example[i].getStyledDocument().insertString(0,
                    "An example of " + name, styles[i].attributes);
            example[i].setEditable(false);
            colour[i] = new JButton(name);
            colour[i].addActionListener(this);
        }


        // Create the horizontal group
        // this gets a bit funky cus were looping through the items
        // first, create the SequentialGroup, so when we loop
        // they come out one after the other
        ParallelGroup horizGroup = layout.createParallelGroup(
                GroupLayout.Alignment.TRAILING);

        for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {
// Then create the sequential group
            SequentialGroup sg = layout.createSequentialGroup();


            // add the parallel to the main group
            horizGroup.addGroup(sg);
            sg.addComponent(colour[i]);//, 64, 128, 256);
            sg.addComponent(bold[i]);//, 32, 32, 32);
            sg.addComponent(italic[i]);//, 32, 32, 32);
            sg.addComponent(example[i], 256, 256, 256);

        }

        // link the button sizes and the example text sizes

        layout.linkSize(SwingConstants.HORIZONTAL, colour);
        layout.linkSize(SwingConstants.HORIZONTAL, example);

        // then set the horizontal group
        layout.setHorizontalGroup(horizGroup);


        // do the same for the vertical group
        SequentialGroup vertGroup = layout.createSequentialGroup();



        for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {
            // Then create the parallel group
            ParallelGroup pg = layout.createParallelGroup();

            // add the parallel to the main group
            vertGroup.addGroup(pg);
            pg.addComponent(colour[i]);
            pg.addComponent(bold[i]);
            pg.addComponent(italic[i]);
            pg.addComponent(example[i]);
        }

        // then set the vertical group
        layout.setVerticalGroup(vertGroup);
    }

    private void applyStyles() {
        for ( int i = 0; i < styles.length; i++ ) {

            example[i].selectAll();
            example[i].setCharacterAttributes(styles[i].attributes, true);
            example[i].select(0, 0);
        }
    }

    @Override
    public void actionPerformed( ActionEvent ae ) {
        for ( int i = 0; i < styles.length; i++ ) {
            if ( ae.getActionCommand().equals(colour[i].getText()) ) {
                Color c = JColorChooser.showDialog(
                        new JDialog(), "Choose your Colour",
                        styles[i].getColor());
                if ( c != null ) {
                    styles[i].setForeground(c);
                }
                break;
            }
        }
        applyStyles();
    }

    @Override
    public void itemStateChanged( ItemEvent ie ) {
        JCheckBox src = (JCheckBox) ie.getSource();
        if ( src.getName().charAt(0) == 'i' ) {
            for ( int i = 0; i < styles.length; i++ ) {
                if ( src.getName().substring(1).equalsIgnoreCase(String.valueOf(
                        i)) ) {
                    styles[i].setItalic(src.isSelected());
                    break;
                }
            }

        } else if ( src.getName().charAt(0) == 'b' ) {
            for ( int i = 0; i < styles.length; i++ ) {
                if ( src.getName().substring(1).equalsIgnoreCase(String.valueOf(
                        i)) ) {
                    styles[i].setBold(src.isSelected());
                    break;
                }
            }
        }
        applyStyles();
    }
}
