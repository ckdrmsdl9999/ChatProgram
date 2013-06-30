/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.util;

import java.awt.Component;
import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

/**
 *
 * @author cakemix
 */
public class Functions {

    public static SequentialGroup sequentialPair( GroupLayout layout,
            JLabel label,
            Component editable ) {

        return layout.createSequentialGroup()
                .addComponent(label)
                .addComponent(editable);
    }

    public static ParallelGroup parallelPair( GroupLayout layout, JLabel label,
            Component editable ) {
        return layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(label)
                .addComponent(editable);
    }

    public static  JPanel addArrayToPanel( JRadioButton[] source, JPanel target ) {
        for (int i = 0; i < source.length; i++){
        target.add(source[i]);}
        return target;
    }
}
