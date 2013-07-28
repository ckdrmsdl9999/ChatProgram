/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.*;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

/**
 *
 * @author cakemix
 */
public class Functions {

    public static SequentialGroup sequentialPair( GroupLayout layout,
            JComponent label,
            Component editable ) {

        return layout.createSequentialGroup()
                .addComponent(label)
                .addComponent(editable);
    }

    public static SequentialGroup sequentialPair( GroupLayout layout,
            Component label,
            Component editable ) {

        return layout.createSequentialGroup()
                .addComponent(label)
                .addComponent(editable);
    }

    public static SequentialGroup sequentialPair( GroupLayout layout,
            Component label,
            Group arrayGroup ) {
        return layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(arrayGroup);
    }

    public static SequentialGroup sequentialPair( GroupLayout layout,
            Group label,
            Group arrayGroup ) {
        return layout.createSequentialGroup()
                .addGroup(label)
                .addGroup(arrayGroup);
    }


    public static ParallelGroup parallelPair( GroupLayout layout,
            JComponent label,
            Component editable ) {
        return layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addComponent(editable);
    }

    public static ParallelGroup parallelPair( GroupLayout layout,
            Component label,
            Component editable ) {
        return layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addComponent(editable);
    }

    public static ParallelGroup parallelPair( GroupLayout layout,
            Component label,
            Group arrayGroup ) {
        return layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addGroup(arrayGroup);
    }

    public static ParallelGroup parallelPair( GroupLayout layout,
            Group label,
            Group arrayGroup ) {
        return layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(label)
                .addGroup(arrayGroup);
    }

    public static SequentialGroup sequentialRadioArray( GroupLayout layout,
            Component[] array ) {
        SequentialGroup rtn = layout.createSequentialGroup();
        for ( int i = 0; i < array.length; i++ ) {
            rtn.addComponent(array[i]);
        }
        return rtn;
    }

    public static ParallelGroup parallelRadioArray( GroupLayout layout,
            Component[] array ) {
        ParallelGroup rtn = layout.createParallelGroup();
        for ( int i = 0; i < array.length; i++ ) {
            rtn.addComponent(array[i]);
        }
        return rtn;
    }

    public static String parseAeName( ActionEvent ae ) {
        return ((Component) ae.getSource()).getName();
    }
 public static String parseAeName( ItemEvent ae ) {
        return ((Component) ae.getSource()).getName();
    }

    public static int updateStatDots( boolean state, String name ) {
        if ( state ) {
            return Integer.parseInt(name.split(" ")[name.split(" ").length - 1]) + 1;
        } else {
            return Integer.parseInt(name.split(" ")[name.split(" ").length - 1]);
        }
    }
    
    public static int updateMeritDots( boolean state, String name ) {
        if ( state ) {
            return Integer.parseInt(name.split(" ")[name.split(" ").length - 1]) + 1;
        } else {
            return Integer.parseInt(name.split(" ")[name.split(" ").length - 1]);
        }
    }
}
