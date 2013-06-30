/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.util;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * Keeps track of the players vitals
 *
 * @author cakemix
 */
public class StatTracker extends JPanel {

    // labels for the componants
    JLabel lblName = new JLabel("Name"),
            lblHealth = new JLabel("Health"),
            lblWillpower = new JLabel("Willpower"),
            lblPower = new JLabel("Supernatural Resource"),
            lblAfinity = new JLabel("Supernatural Power");
    // Player/ Character name
    JTextField txtName;
    // Radio buttons to epresent stat dots
    JRadioButton[] rdoHealth = new JRadioButton[15],
            rdoWillpower = new JRadioButton[10],
            rdoAfinity = new JRadioButton[10];
    JCheckBox[] chkPower = new JCheckBox[20];
    // "check boxes" to show stats current states
    JTextField[] txtHealth = new JTextField[15],
            txtWillpower = new JTextField[10];

    public StatTracker() {
        super();
        initialiseComponants("", 1, 1, 1, 1);
    }

    public StatTracker( String name, int health, int will,
            int power, int afinity ) {
        super();
        initialiseComponants(name, health, will, afinity, power);

    }

    private void initialiseComponants( String name, int health, int will,
            int afinity, int power ) {
        txtName = new JTextField(name);

        for ( int i = 0; i < 15; i++ ) {
            if ( i < 10 ) {
                rdoAfinity[i] = new JRadioButton();
                rdoAfinity[i].setBorder(BorderFactory.createEmptyBorder());
                if ( i < afinity ) {
                    rdoAfinity[i].setSelected(true);
                }

                rdoWillpower[i] = new JRadioButton();
                rdoWillpower[i].setBorder(BorderFactory.createEmptyBorder());
                txtWillpower[i] = new JTextField();
                txtWillpower[i].setBorder(BorderFactory.createEmptyBorder());
                if ( i < will ) {
                    rdoWillpower[i].setSelected(true);
                }
                chkPower[i] = new JCheckBox();
                chkPower[i].setBorder(BorderFactory.createEmptyBorder());
                chkPower[i+10] = new JCheckBox();
                chkPower[i+10].setBorder(BorderFactory.createEmptyBorder());
                if ( i < power ) {
                    chkPower[i].setSelected(true);
                }
            }
            rdoHealth[i] = new JRadioButton();
            rdoHealth[i].setBorder(BorderFactory.createEmptyBorder());
            txtHealth[i] = new JTextField(1);

            if ( i < health ) {
                rdoHealth[i].setSelected(true);
            }

        }

        initialiseGrid();

    }

    private void initialiseGrid() {
        GridLayout layout = new GridLayout(0, 2);
        setLayout(layout);

        add(lblName);
        add(txtName);

        add(lblHealth);
        JPanel p = new JPanel();
        GridLayout panelLayout = new GridLayout (2,15);
        p.setLayout(panelLayout);
        for ( int i = 0; i < 15; i++ ) {
            p.add(rdoHealth[i]);}
        for ( int i = 0; i < 15; i++ ) {
            p.add(txtHealth[i]);
        }
        add(p);

        add(lblWillpower);
        p = new JPanel();
        panelLayout = new GridLayout (2,10);
        p.setLayout(panelLayout);
        for ( int i = 0; i < 10; i++ ) {
            p.add(rdoWillpower[i]);}
        for ( int i = 0; i < 10; i++ ) {
            p.add(txtWillpower[i]);
        }
        add(p);

        add(lblPower);p = new JPanel();
        for ( int i = 0; i < 20; i++ ) {
            p.add(chkPower[i]);
        }
        add(p);

        add(lblAfinity);
        p = new JPanel();
        for ( int i = 0; i < 10; i++ ) {
            p.add(rdoAfinity[i]);
        }
        add(p);

    }

    private void initialiseGroup() {

    }
}
