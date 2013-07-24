/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author cakemix
 */
public class ScrollAlert extends JFrame implements ActionListener {

    public ScrollAlert( String message ) {
        this(message, "Message");
    }

    public ScrollAlert( String message, String title ) {
        this(message,title,"Disclaimer: Text in the below box may be all on one line,"
                + " just line wrapped for ease of display.");
    }

    public ScrollAlert( String message, String title, String label){
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JLabel lbl = new JLabel(label);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(lbl);
        JTextArea Output = new JTextArea(message);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Output.setLineWrap(true);
        Output.setPreferredSize(new Dimension(400, 200));
        Output.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(Output);
        JButton btn = new JButton("Close");
        btn.addActionListener(this);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(btn);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed( ActionEvent ae ) {
        this.dispose();
    }
}
