/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.util;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import org.omg.PortableInterceptor.USER_EXCEPTION;
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
                rdoAfinity[i].setName("afinity " + i);
                rdoAfinity[i].addActionListener(this);
                if ( i < afinity ) {
                    rdoAfinity[i].setSelected(true);
                }

                rdoWillpower[i] = new JRadioButton();
                rdoWillpower[i].setName("willpower " + i);
                rdoWillpower[i].addActionListener(this);
                txtWillpower[i] = new JCheckBox();
                txtWillpower[i].addActionListener(this);
                txtWillpower[i].setName("usedwill " + i);
                if ( i < will ) {
                    rdoWillpower[i].setSelected(true);
                }
                chkPower[0][i] = new JCheckBox();
                chkPower[0][i].setName("power " + i);
                chkPower[0][i].addActionListener(this);
                chkPower[1][i] = new JCheckBox();
                chkPower[1][i].setName("power 1" + i);
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
            rdoHealth[i].setName("health " + i);
            rdoHealth[i].addActionListener(this);
            txtHealth[i] = new JTextField(1);
            txtHealth[i].setName("damage " + i);
            txtHealth[i].setEditable(false);
            // txtHealth[i].setEditable(false);
            txtHealth[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked( MouseEvent me ) {
                    dmgOnClick(me);
                }

                @Override
                public void mousePressed( MouseEvent me ) {
                }

                @Override
                public void mouseReleased( MouseEvent me ) {
                }

                @Override
                public void mouseEntered( MouseEvent me ) {
                }

                @Override
                public void mouseExited( MouseEvent me ) {
                }
            });
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
        switch ( name.split(" ")[0] ) {
            case "afinity":

                vitals.afinity = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                switch ( Integer.parseInt(name.split(" ")[1]) + 1 ) {
                    case 1:
                        vitals.power = 10;
                        break;
                    case 2:
                        vitals.power = 11;
                        break;
                    case 3:
                        vitals.power = 12;
                        break;
                    case 4:
                        vitals.power = 13;
                        break;
                    case 5:
                        vitals.power = 14;
                        break;
                    case 6:
                        vitals.power = 15;
                        break;
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        vitals.power = 20;
                        break;
                }
                break;
            case "health":
                vitals.health = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);

                break;
//            case "damage":
//                switch (((JTextField) ae.getSource()).getText().charAt(0) ){
//                    case '/':
//                    case '\\':
//                        vitals.updateDamage('X',Integer.parseInt(name.split(" ")[1]));
//                    case 'x':
//                    case 'X':
//                        vitals.updateDamage('*',Integer.parseInt(name.split(" ")[1]));
//                    case '*':
//                    default:
//                       vitals.updateDamage(' ',Integer.parseInt(name.split(" ")[1]));
//
//                }
//                break;
            case "power":
                vitals.powerLeft = updateStatDots(
                        ((JCheckBox) ae.getSource()).isSelected(), name);
                break;
            case "willpower":
                vitals.willpower = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "usedwill":
                vitals.usedWill = updateStatDots(
                        ((JCheckBox) ae.getSource()).isSelected(), name);
                break;
        }
        update();


    }

    public Vitals getVitals() {
        return vitals;
    }

    public void setVitals( Vitals vitals ) {
        this.vitals = vitals;
        update();
    }

    private void update() {
        for ( Component c : this.getComponents() ) {
            String name = c.getName();
            if ( name != null ) {
                String s = name.split(" ")[0];
                switch ( s ) {

                    case "health":
                        if ( vitals.health <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "damage":
                        ((JTextField) c).setText("" + vitals.damage.charAt(
                                Integer.parseInt(name.split(" ")[1])));
                        break;
                    case "willpower":
                        if ( vitals.willpower <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "usedwill":
                        if ( vitals.usedWill <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JCheckBox) c).setSelected(false);
                            break;
                        } else {
                            ((JCheckBox) c).setSelected(true);
                            break;
                        }
                    case "power":
                        if ( vitals.powerLeft <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JCheckBox) c).setSelected(false);
                        } else {
                            ((JCheckBox) c).setSelected(true);

                        }
                        if ( vitals.power <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JCheckBox) c).setEnabled(false);
                            break;
                        } else {
                            ((JCheckBox) c).setEnabled(true);
                            break;
                        }

                    case "afinity":
                        if ( vitals.afinity <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }

                }
            }
        }
    }

    private void dmgOnClick( MouseEvent me ) {
        JTextField c = ((JTextField) me.getComponent());
        String name = c.getName();
        switch ( c.getText().charAt(0) ) {
            case ' ':
                vitals.updateDamage('/',
                        Integer.parseInt(name.split(" ")[1]));
                c.setText("/");
                break;
            case '/':
            case '\\':
                vitals.updateDamage('X',
                        Integer.parseInt(name.split(" ")[1]));
                c.setText("X");
                break;
            case 'x':
            case 'X':
                vitals.updateDamage('*',
                        Integer.parseInt(name.split(" ")[1]));
                c.setText("*");
                break;
            case '*':
            default:
                vitals.updateDamage(' ',
                        Integer.parseInt(name.split(" ")[1]));
                c.setText(" ");
                break;

        }
    }
}
