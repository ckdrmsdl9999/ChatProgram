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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
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
            lblAfinity = new JLabel("Wyrd"),
            lblClarity = new JLabel("Clarity"),
            lblSpeed = new JLabel("Speed"),
            lblDefense = new JLabel("Defense"),
            lblSize = new JLabel("Size"),
            lblArmor = new JLabel("Armor"),
            lblInitiative = new JLabel("Initiative"),
            lblExperience = new JLabel("Experience");
    // Radio buttons to epresent stat dots
    JRadioButton[] rdoHealth = new JRadioButton[15],
            rdoWillpower = new JRadioButton[10],
            rdoAfinity = new JRadioButton[10],
            rdoClarity = new JRadioButton[10];
    JCheckBox[] txtWillpower = new JCheckBox[10];
    JTextField txtSpeed = new JTextField(),
            txtDefense = new JTextField(),
            txtSize = new JTextField(),
            txtArmor = new JTextField(),
            txtInitiative = new JTextField(),
            txtExperience = new JTextField();
    JCheckBox[][] chkPower = new JCheckBox[2][10];
    // "check boxes" to show stats current states
    JTextField[] txtHealth = new JTextField[15];
    // vitals
    Vitals vitals = new Vitals();

    public StatTracker() {
        super();
        initialiseComponants(6, 1, 1, 10, 7);
        update();
    }

    public StatTracker( int health, int will,
            int power, int afinity, int clarity ) {
        super();
        initialiseComponants(health, will, afinity, power, clarity);
        update();

    }

    private void initialiseComponants( int health, int will,
            int afinity, int power, int clarity ) {

        for ( int i = 0; i < 15; i++ ) {
            if ( i < 10 ) {
                rdoAfinity[i] = new JRadioButton();
                rdoAfinity[i].setName("afinity " + i);
                rdoAfinity[i].addActionListener(this);
                if ( i < afinity ) {
                    rdoAfinity[i].setSelected(true);
                }

                rdoClarity[i] = new JRadioButton();
                rdoClarity[i].setName("clarity " + ((i - 9) * -1));
                rdoClarity[i].addActionListener(this);
                if ( i < clarity ) {
                    rdoClarity[i].setSelected(true);
                }

                rdoWillpower[i] = new JRadioButton();
                rdoWillpower[i].setName("willpower " + i);
                rdoWillpower[i].addActionListener(this);
                rdoWillpower[i].setEnabled(false);
                if ( i < will ) {
                    rdoWillpower[i].setSelected(true);
                }

                txtWillpower[i] = new JCheckBox();
                txtWillpower[i].addActionListener(this);
                txtWillpower[i].setName("usedwill " + i);

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
            rdoHealth[i].setEnabled(false);
            txtHealth[i] = new JTextField(1);
            txtHealth[i].setName("damage " + i);
            txtHealth[i].setEditable(false);
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
            txtHealth[i].setFont(new Font("Arial", 0, 16));

            if ( i < health ) {
                rdoHealth[i].setSelected(true);
            }

        }

        txtArmor.setFont(new Font("Arial", 0, 16));
        txtArmor.setName("armor ");
        txtArmor.addActionListener(this);
        txtArmor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate( DocumentEvent de ) {
                updateArmor(de);
            }

            @Override
            public void removeUpdate( DocumentEvent de ) {

                updateArmor(de);
            }

            @Override
            public void changedUpdate( DocumentEvent de ) {
                updateArmor(de);
            }
        });
        txtDefense.setFont(new Font("Arial", 0, 16));
        txtDefense.setEditable(false);
        txtDefense.setName("defense ");
        txtDefense.addActionListener(this);
        txtExperience.setFont(new Font("Arial", 0, 16));
        txtExperience.setName("experience ");
        txtExperience.addActionListener(this);
        txtExperience.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate( DocumentEvent de ) {
                updateXp(de);
            }

            @Override
            public void removeUpdate( DocumentEvent de ) {
                updateXp(de);
            }

            @Override
            public void changedUpdate( DocumentEvent de ) {
                updateXp(de);
            }
        });
        txtInitiative.setFont(new Font("Arial", 0, 16));
        txtInitiative.setEditable(false);
        txtInitiative.setName("initiative ");
        txtInitiative.addActionListener(this);
        txtSize.setFont(new Font("Arial", 0, 16));
        txtSize.setEditable(false);
        txtSize.setName("size ");
        txtSize.addActionListener(this);
        txtSpeed.setFont(new Font("Arial", 0, 16));
        txtSpeed.setEditable(false);
        txtSpeed.setName("speed ");
        txtSpeed.addActionListener(this);

        initialiseGroup();
    }

    private void initialiseGroup() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                //.addGap(10, 75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblSize, txtSize))
                .addGroup(sequentialPair(layout, lblSpeed, txtSpeed))
                .addGroup(sequentialPair(layout, lblDefense, txtDefense))
                .addGroup(sequentialPair(layout, lblArmor, txtArmor))
                .addGroup(sequentialPair(layout, lblInitiative, txtInitiative))
                .addGroup(sequentialPair(layout, lblExperience, txtExperience)))
                //.addGap(10, 15, 50)
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
                //.addGap(10, 15, 50)
                .addGroup(
                layout.createParallelGroup()
                .addGroup(parallelPair(layout, lblClarity,
                parallelRadioArray(layout, rdoClarity)))) //.addGap(10, 75, Short.MAX_VALUE)
                );

        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                .addGroup(
                layout.createSequentialGroup()
                //.addGap(5, 10, 15)
                .addGroup(parallelPair(layout, lblSize, txtSize))
                //.addGap(5, 10, 15)
                .addGroup(parallelPair(layout, lblSpeed, txtSpeed))
                //.addGap(5, 10, 15)
                .addGroup(parallelPair(layout, lblDefense, txtDefense))
                //.addGap(5, 10, 15)
                .addGroup(parallelPair(layout, lblArmor, txtArmor))
                //.addGap(5, 10, 15)
                .addGroup(parallelPair(layout, lblInitiative, txtInitiative))
                //.addGap(5, 10, 15)
                .addGroup(parallelPair(layout, lblExperience, txtExperience))))
                .addGroup(layout.createSequentialGroup()
                .addGroup(
                parallelPair(layout,
                lblHealth,
                sequentialPair(layout,
                parallelRadioArray(layout, rdoHealth),
                parallelRadioArray(layout, txtHealth))))
                //.addGap(5, 10, 15)
                .addGroup(
                parallelPair(layout,
                lblWillpower,
                sequentialPair(layout,
                parallelRadioArray(layout, rdoWillpower),
                parallelRadioArray(layout, txtWillpower))))
                //.addGap(5, 10, 15)
                .addGroup(
                parallelPair(layout,
                lblPower,
                sequentialPair(layout,
                parallelRadioArray(layout, chkPower[0]),
                parallelRadioArray(layout, chkPower[1]))))
                //.addGap(5, 10, 15)
                .addGroup(
                parallelPair(layout,
                lblAfinity,
                parallelRadioArray(layout, rdoAfinity))))
                .addGroup(
                layout.createSequentialGroup()
                .addGroup(sequentialPair(layout, lblClarity,
                sequentialRadioArray(layout, rdoClarity)))));

        ArrayList cmp = new ArrayList<Component>();
        for ( int i = 0; i < this.getComponents().length; i++ ) {
            if ( this.getComponents()[i] instanceof JRadioButton
                    || this.getComponent(i) instanceof JTextField
                    || this.getComponent(i) instanceof JCheckBox ) {
                if ( !"armor".equals(
                        this.getComponents()[i].getName().split(" ")[0])
                        || !"defense".equals(this.getComponents()[i].getName().split(
                        " ")[0])
                        || !"experience".equals(this.getComponents()[i].getName().split(
                        " ")[0])
                        || !"initiative".equals(this.getComponents()[i].getName().split(
                        " ")[0])
                        || !"speed".equals(this.getComponents()[i].getName().split(
                        " ")[0])
                        || !"size".equals(this.getComponents()[i].getName().split(
                        " ")[0]) ) {
                    cmp.add(this.getComponents()[i]);
                }
            }
        }
        Component[] cmpPass = new Component[cmp.size()];
        for ( int i = 0; i < cmp.size(); i++ ) {
            cmpPass[i] = (Component) cmp.get(i);
        }
        layout.linkSize(cmpPass);
        layout.linkSize(SwingConstants.HORIZONTAL, lblHealth, lblPower,
                lblWillpower, lblAfinity);
        layout.linkSize(SwingConstants.HORIZONTAL, lblSize, lblSpeed, lblDefense,
                lblArmor, lblInitiative, lblExperience);
        layout.linkSize(txtArmor, txtDefense, txtExperience, txtInitiative,
                txtSize, txtSpeed);
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
            case "clarity":
                vitals.clarity = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "armor":
                vitals.armor = ((JTextField) ae.getSource()).getText();
                break;
            case "experience":
                vitals.experience = Integer.parseInt(
                        ((JTextField) ae.getSource()).getText());
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

    public void update() {
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
                    case "clarity":
                        if ( vitals.clarity <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "size":
                        txtSize.setText(String.valueOf(vitals.size));
                        break;
                    case "speed":
                        txtSpeed.setText(String.valueOf(vitals.speed));
                        break;
                    case "defense":
                        txtDefense.setText(String.valueOf(vitals.defense));
                        break;
                    case "armor":
                        txtArmor.setText(vitals.armor);
                        break;
                    case "initiative":
                        txtInitiative.setText(String.valueOf(vitals.initiative));
                        break;
                    case "experience":
                        txtExperience.setText(String.valueOf(vitals.experience));
                        break;
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

    private void updateArmor( DocumentEvent de ) {

        vitals.armor = txtArmor.getText();
    }

    private void updateXp( DocumentEvent de ) {
        if ( !"".equals(txtExperience.getText())
                && txtExperience.getText() != null ) {
            vitals.experience = Integer.parseInt(txtExperience.getText());
        }
        else {
            vitals.experience = 0;
        }

    }
}
