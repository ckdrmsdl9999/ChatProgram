/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.client.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.cakemix.Network.ChatMessage;

/**
 *
 * @author cakemix
 */
public class StylePickerFrame extends JFrame implements ActionListener,
        ItemListener {

    public StylePickerFrame( MessageStyle[] styles ) throws BadLocationException {
        // set the name and create the frame
        super("Style Picker");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // alot of this is copy pasta from ClientFrame

        // Create a new group layout
        GroupLayout layout = new GroupLayout(getContentPane());
        // Set auto-create gaps, makes it look neater
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // tell the panel to use the layout
        getContentPane().setLayout(layout);

        JButton[] colour = new JButton[ChatMessage.NUM_TYPE];
        JCheckBox[] bold = new JCheckBox[ChatMessage.NUM_TYPE];
        JCheckBox[] italic = new JCheckBox[ChatMessage.NUM_TYPE];
        JTextPane[] example = new JTextPane[ChatMessage.NUM_TYPE];

        // create all the componants and set them to their current values
        for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {
            colour[i] = new JButton(styles[i].getColorString());
            colour[i].setForeground(styles[i].getColor());
            bold[i] = new JCheckBox();
            bold[i].setSelected(styles[i].getBold());
            italic[i] = new JCheckBox();
            italic[i].setSelected(styles[i].getItalic());
            example[i] = new JTextPane();
            // find out which style it is, and set the text for the example
            switch ( i ) {
                case ChatMessage.TYPE_ALIAS:
                    example[i].getStyledDocument().insertString(0,
                            "This is an \"Alias\"", styles[i].attributes);
                    break;
                case ChatMessage.TYPE_ALL:
                    example[i].getStyledDocument().insertString(0,
                            "This is a \"Standard Comment\"",
                            styles[i].attributes);
                    break;
                case ChatMessage.TYPE_ANNOUNCE:
                    example[i].getStyledDocument().insertString(0,
                            "This is an \"Announcement\"", styles[i].attributes);
                    break;
                case ChatMessage.TYPE_DESCRIPTION:
                    example[i].getStyledDocument().insertString(0,
                            "This is a \"Descripion Block\"",
                            styles[i].attributes);
                    break;
                case ChatMessage.TYPE_EMOTE:
                    example[i].getStyledDocument().insertString(0,
                            "This is an \"Emote\"", styles[i].attributes);
                    break;
                case ChatMessage.TYPE_OFF_TOPIC:
                    example[i].getStyledDocument().insertString(0,
                            "This is an \"Off Topic Comment\"",
                            styles[i].attributes);
                    break;
                case ChatMessage.TYPE_SENDER:
                    example[i].getStyledDocument().insertString(0,
                            "This is a \"Server Response\"",
                            styles[i].attributes);
                    break;
                case ChatMessage.TYPE_WHISPER:
                    example[i].getStyledDocument().insertString(0,
                            "This is a \"Player Whisper\"", styles[i].attributes);
                    break;

            }
            example[i].setEditable(false);
        }

        // create lables for bold and italic
        JLabel boldLbl = new JLabel("Bold"), italicLbl = new JLabel("Italic");

        // Create the horizontal group
        // this gets a bit funky cus were looping through the items
        // first, create the SequentialGroup, so when we loop
        // they come out one after the other
        ParallelGroup horizGroup = layout.createParallelGroup(
                GroupLayout.Alignment.TRAILING);
        SequentialGroup sg = layout.createSequentialGroup();
        horizGroup.addGroup(sg);
        sg.addComponent(boldLbl);
        sg.addComponent(italicLbl);
        for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {
// Then create the sequential group
            sg = layout.createSequentialGroup();


            // add the parallel to the main group
            horizGroup.addGroup(sg);
            sg.addComponent(colour[i], 64, 128, 256);
            sg.addComponent(bold[i], 32, 32, 32);
            sg.addComponent(italic[i], 32, 32, 32);
            sg.addComponent(example[i], 192, 192, 1024);

        }

        // link the button sizes and the example text sizes

        layout.linkSize(SwingConstants.HORIZONTAL,colour);
        layout.linkSize(SwingConstants.HORIZONTAL, example);

        // then set the horizontal group
        layout.setHorizontalGroup(horizGroup);


        // do the same for the vertical group
        SequentialGroup vertGroup = layout.createSequentialGroup();


        ParallelGroup pg = layout.createParallelGroup();
        vertGroup.addGroup(pg);
        pg.addComponent(boldLbl);
        pg.addComponent(italicLbl);
        for ( int i = 0; i < ChatMessage.NUM_TYPE; i++ ) {
// Then create the parallel group
            pg = layout.createParallelGroup();

            // add the parallel to the main group
            vertGroup.addGroup(pg);
            pg.addComponent(colour[i]);
            pg.addComponent(bold[i]);
            pg.addComponent(italic[i]);
            pg.addComponent(example[i]);
        }

        // then set the vertical group
        layout.setVerticalGroup(vertGroup);

        pack();

        setVisible(true);

    }

    @Override
    public void actionPerformed( ActionEvent ae ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void itemStateChanged( ItemEvent ie ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
