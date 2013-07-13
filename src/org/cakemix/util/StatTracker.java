/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.Style;
import static org.cakemix.util.Functions.*;

/**
 *
 * Keeps track of the players vitals
 *
 * @author cakemix
 */
public class StatTracker extends JPanel {

    // labels for the componants
    JLabel lblHealth = new JLabel("Health"),
            lblWillpower = new JLabel("Willpower"),
            lblPower = new JLabel("Glamour"),
            lblAfinity = new JLabel("Wyrd");
    // Radio buttons to epresent stat dots
    JRadioButton[] rdoHealth = new JRadioButton[15],
            rdoWillpower = new JRadioButton[10],
            rdoAfinity = new JRadioButton[10];
    JCheckBox[] txtWillpower = new JCheckBox[10];
    JCheckBox[][] chkPower = new JCheckBox[2][10];
    // "check boxes" to show stats current states
    JTextField[] txtHealth = new JTextField[15];

    public StatTracker() {
        super();
        initialiseComponants(1, 1, 1, 1);
    }

    public StatTracker( int health, int will,
            int power, int afinity ) {
        super();
        initialiseComponants(health, will, afinity, power);

    }

    private void initialiseComponants( int health, int will,
            int afinity, int power ) {

        for ( int i = 0; i < 15; i++ ) {
            if ( i < 10 ) {
                rdoAfinity[i] = new JRadioButton();
                if ( i < afinity ) {
                    rdoAfinity[i].setSelected(true);
                }

                rdoWillpower[i] = new JRadioButton();
                txtWillpower[i] = new JCheckBox();
                if ( i < will ) {
                    rdoWillpower[i].setSelected(true);
                }
                chkPower[0][i] = new JCheckBox();
                chkPower[1][i] = new JCheckBox();
                if ( i < power ) {
                    chkPower[0][i].setSelected(true);
                }
            }
            rdoHealth[i] = new JRadioButton();
            txtHealth[i] = new JTextField(1);
            txtHealth[i].setFont(new Font("Arial", 0, 14));

            if ( i < health ) {
                rdoHealth[i].setSelected(true);
            }

        }

        initialiseGroup();

    }

    private void initialiseGroup() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(
                sequentialPair(layout,
                lblHealth,
                parallelPair(layout,
                sequentialRadioArray(layout, rdoHealth),
                sequentialRadioArray(layout, txtHealth))))
                .addGroup(
                sequentialPair(layout,
                lblWillpower,
                parallelPair(layout,
                sequentialRadioArray(layout, rdoWillpower),
                sequentialRadioArray(layout, txtWillpower))))
                .addGroup(
                sequentialPair(layout,
                lblPower,
                parallelPair(layout,
                sequentialRadioArray(layout, chkPower[0]),
                sequentialRadioArray(layout, chkPower[1]))))
                .addGroup(
                sequentialPair(layout,
                lblAfinity,
                sequentialRadioArray(layout, rdoAfinity)))
                );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(
                parallelPair(layout,
                lblHealth,
                sequentialPair(layout,
                parallelRadioArray(layout, rdoHealth),
                parallelRadioArray(layout, txtHealth))))
                .addGroup(
                parallelPair(layout,
                lblWillpower,
                sequentialPair(layout,
                parallelRadioArray(layout, rdoWillpower),
                parallelRadioArray(layout, txtWillpower))))
                .addGroup(
                parallelPair(layout,
                lblPower,
                sequentialPair(layout,
                parallelRadioArray(layout, chkPower[0]),
                parallelRadioArray(layout, chkPower[1]))))
                .addGroup(
                parallelPair(layout,
                lblAfinity,
                parallelRadioArray(layout, rdoAfinity))));

        ArrayList cmp = new ArrayList<Component>();
        for ( int i = 0; i < this.getComponents().length; i++ ) {
            if ( this.getComponents()[i] instanceof JRadioButton
                    || this.getComponent(i) instanceof JTextField
                    //|| this.getComponent(i) instanceof JCheckBox
                    ) {
                cmp.add(this.getComponents()[i]);
            }
        }
        Component[] cmpPass = new Component[cmp.size()];
        for ( int i = 0; i < cmp.size(); i++ ) {
            cmpPass[i] = (Component) cmp.get(i);
        }
        layout.linkSize(cmpPass);
        layout.linkSize(lblHealth, lblPower,lblPower,lblAfinity);
    }
}
