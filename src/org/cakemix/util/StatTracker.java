/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.util;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;
import static org.cakemix.util.Functions.*;

/**
 *
 * Keeps track of the players vitals
 *
 * @author cakemix
 */
public class StatTracker extends JPanel implements ActionListener {

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
    // vitals
    Vitals vitals = new Vitals();

    public StatTracker() {
        super();
        initialiseComponants(6, 1, 1, 10);
        update();
    }

    public StatTracker( int health, int will,
            int power, int afinity ) {
        super();
        initialiseComponants(health, will, afinity, power);
        update();

    }

    private void initialiseComponants( int health, int will,
            int afinity, int power ) {

        for ( int i = 0; i < 15; i++ ) {
            if ( i < 10 ) {
                rdoAfinity[i] = new JRadioButton();
                rdoAfinity[i].setName("ar" + i);
                rdoAfinity[i].addActionListener(this);
                if ( i < afinity ) {
                    rdoAfinity[i].setSelected(true);
                }

                rdoWillpower[i] = new JRadioButton();
                rdoWillpower[i].setName("wr" + i);
                rdoWillpower[i].addActionListener(this);
                txtWillpower[i] = new JCheckBox();
                txtWillpower[i].addActionListener(this);
                txtWillpower[i].setName("wc" + i);
                if ( i < will ) {
                    rdoWillpower[i].setSelected(true);
                }
                chkPower[0][i] = new JCheckBox();
                chkPower[0][i].setName("p0" + i);
                chkPower[0][i].addActionListener(this);
                chkPower[1][i] = new JCheckBox();
                chkPower[1][i].setName("p1" + i);
                chkPower[1][i].addActionListener(this);
                if ( i < power / 2 ) {
                    chkPower[0][i].setSelected(true);
                }
                if ( i >= power ) {
                    chkPower[0][i].setEnabled(false);
                } else {
                    if ( i + 10 >= power ) {
                        chkPower[1][i].setEnabled(false);
                    }
                }
            }
            rdoHealth[i] = new JRadioButton();
            rdoHealth[i].setName("hr" + i);
            rdoHealth[i].addActionListener(this);
            txtHealth[i] = new JTextField(1);
            txtHealth[i].setName("ht" + i);
            txtHealth[i].addActionListener(this);
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




        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10, 75, Short.MAX_VALUE)
                .addGroup(
                layout.createParallelGroup()
                .addGroup(
                sequentialPair(layout, lblHealth,
                parallelPair(layout,
                sequentialRadioArray(layout, rdoHealth),
                sequentialRadioArray(layout, txtHealth))))
                .addGroup(
                sequentialPair(layout, lblWillpower,
                parallelPair(layout,
                sequentialRadioArray(layout, rdoWillpower),
                sequentialRadioArray(layout, txtWillpower))))
                .addGroup(
                sequentialPair(layout, lblPower,
                parallelPair(layout,
                sequentialRadioArray(layout, chkPower[0]),
                sequentialRadioArray(layout, chkPower[1]))))
                .addGroup(
                sequentialPair(layout,
                lblAfinity,
                sequentialRadioArray(layout, rdoAfinity))))
                .addGap(10, 75, Short.MAX_VALUE));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(
                parallelPair(layout,
                lblHealth,
                sequentialPair(layout,
                parallelRadioArray(layout, rdoHealth),
                parallelRadioArray(layout, txtHealth))))
                .addGap(5, 10, 15)
                .addGroup(
                parallelPair(layout,
                lblWillpower,
                sequentialPair(layout,
                parallelRadioArray(layout, rdoWillpower),
                parallelRadioArray(layout, txtWillpower))))
                .addGap(5, 10, 15)
                .addGroup(
                parallelPair(layout,
                lblPower,
                sequentialPair(layout,
                parallelRadioArray(layout, chkPower[0]),
                parallelRadioArray(layout, chkPower[1]))))
                .addGap(5, 10, 15)
                .addGroup(
                parallelPair(layout,
                lblAfinity,
                parallelRadioArray(layout, rdoAfinity))));

        ArrayList cmp = new ArrayList<Component>();
        for ( int i = 0; i < this.getComponents().length; i++ ) {
            if ( this.getComponents()[i] instanceof JRadioButton
                    || this.getComponent(i) instanceof JTextField
                    || this.getComponent(i) instanceof JCheckBox ) {
                cmp.add(this.getComponents()[i]);
            }
        }
        Component[] cmpPass = new Component[cmp.size()];
        for ( int i = 0; i < cmp.size(); i++ ) {
            cmpPass[i] = (Component) cmp.get(i);
        }
        layout.linkSize(cmpPass);
        layout.linkSize(SwingConstants.HORIZONTAL, lblHealth, lblPower,
                lblWillpower, lblAfinity);
    }

    @Override
    public void actionPerformed( ActionEvent ae ) {
        // get the actual item that called this
        String name = parseAeName(ae);
        switch ( name.charAt(0) ) {
            case 'a':

                vitals.setStats("af",
                        Integer.parseInt(name.substring(2)) + 1);
                switch ( Integer.parseInt(name.substring(2)) + 1 ) {
                    case 1:
                        vitals.setStats("po", 10);
                        break;
                    case 2:
                        vitals.setStats("po", 11);
                        break;
                    case 3:
                        vitals.setStats("po", 12);
                        break;
                    case 4:
                        vitals.setStats("po", 13);
                        break;
                    case 5:
                        vitals.setStats("po", 14);
                        break;
                    case 6:
                        vitals.setStats("po", 15);
                        break;
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        vitals.setStats("po", 20);
                        break;
                }
                break;
            case 'h':
                if ( name.charAt(1) == 'r' ) {
                    vitals.setStats("hp",
                            Integer.parseInt(name.substring(2)) + 1);
                }
                if ( name.charAt(1) == 't' ) {
                    vitals.setStats(ae.getActionCommand(),
                            Integer.parseInt(name.substring(2)));
                }
                break;
            case 'p':
                if ( name.charAt(1) == '0' ) {
                    vitals.setStats("pl",
                            Integer.parseInt(name.substring(2)) + 1);
                }
                if ( name.charAt(1) == '1' ) {
                    vitals.setStats("pl",
                            Integer.parseInt(name.substring(2)) + 10);
                }
                break;
            case 'w':
                if ( name.charAt(1) == 'r' ) {
                    vitals.setStats("wp",
                            Integer.parseInt(name.substring(2)) + 1);
                }
                if ( name.charAt(1) == 'c' ) {
                    vitals.setStats("wl",
                            Integer.parseInt(name.substring(2)) + 1);
                }
                break;
        }
        update();


    }

    private void update() {
        for ( int i = 0; i < this.getComponentCount(); i++ ) {
            String name = getComponent(i).getName();
            if ( name != null ) {
                String s = name.substring(0, 2);
                switch ( s ) {

                    case "hr":
                        if ( Integer.parseInt(name.substring(2)) > vitals.health - 1 ) {
                            ((JRadioButton) getComponent(i)).setSelected(false);
                        } else {
                            ((JRadioButton) getComponent(i)).setSelected(true);
                        }
                        break;
                    case "ht":
                        ((JTextField) getComponent(i)).setText(
                                String.valueOf(vitals.damage.charAt(
                                Integer.parseInt(name.substring(2)))));
                        break;
                    case "wr":
                        if ( Integer.parseInt(name.substring(2)) > vitals.willpower - 1 ) {
                            ((JRadioButton) getComponent(i)).setSelected(false);
                        } else {
                            ((JRadioButton) getComponent(i)).setSelected(true);
                        }
                        break;
                    case "wc":
                        if ( Integer.parseInt(name.substring(2)) > vitals.usedWill - 1 ) {
                            ((JCheckBox) getComponent(i)).setSelected(false);
                        } else {
                            ((JCheckBox) getComponent(i)).setSelected(true);
                        }
                        break;
                    case "p0":
                        if ( Integer.parseInt(name.substring(2)) > vitals.powerLeft - 1 ) {
                            ((JCheckBox) getComponent(i)).setSelected(false);
                        } else {
                            ((JCheckBox) getComponent(i)).setSelected(true);
                        }
                        if (Integer.parseInt(name.substring(2)) > vitals.power -1){
                            ((JCheckBox) getComponent(i)).setEnabled(false);
                        }
                        else {
                            ((JCheckBox) getComponent(i)).setEnabled(true);
                        }
                        break;
                    case "p1":
                        if ( Integer.parseInt(name.substring(2)) > vitals.powerLeft - 11 ) {
                            ((JCheckBox) getComponent(i)).setSelected(false);
                        } else {
                            ((JCheckBox) getComponent(i)).setSelected(true);
                        }
                        if (Integer.parseInt(name.substring(2)) > vitals.power -11){
                            ((JCheckBox) getComponent(i)).setEnabled(false);
                        }
                        else {
                            ((JCheckBox) getComponent(i)).setEnabled(true);
                        }
                        break;
                    case "ar":
                        if ( Integer.parseInt(name.substring(2)) > vitals.afinity- 1 ) {
                            ((JRadioButton) getComponent(i)).setSelected(false);
                        } else {
                            ((JRadioButton) getComponent(i)).setSelected(true);
                        }
                        break;

                }
            }
        }
    }

}
