/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.characterSheets;

import java.awt.Component;
import java.awt.Container;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import static org.cakemix.util.Functions.*;

/**
 *
 * @author cakemix
 */
public class Changeling extends JFrame {

    public Changeling() {
        super("Changeling - The Lost");
        buildUI(this.getContentPane());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Build the UI
     */
    private void buildUI( Container contentPane ) {

        // Create the top section
        // Create the labels
        JLabel lblName = new JLabel("Name"),
                lblPlayer = new JLabel("Player"),
                lblChronicle = new JLabel("Chronicle"),
                lblVirtue = new JLabel("Virtue"),
                lblVice = new JLabel("Vice"),
                lblConcept = new JLabel("Concept"),
                lblSeeming = new JLabel("Seeming"),
                lblKith = new JLabel("Kith"),
                lblCourt = new JLabel("Court");

        //Create the input boxes
        JTextField txtName = new JTextField(),
                txtPlayer = new JTextField(),
                txtChronicle = new JTextField(),
                txtConcept = new JTextField(),
                txtKith = new JTextField();

        // Create drop downs
        // Create a string[] to hold the options for each one
        // Start off with the virtues
        String[] s = { "Charity", "Faith", "Fortitude", "Hope", "Justice", "Prudence", "Temperance" };
        JComboBox cboVirtue = new JComboBox<String>(s);
        // Fill for vice
        s = new String[]{ "Envy", "Gluttony", "Greed", "Lust", "Pride", "Sloth", "Wrath" };
        JComboBox cboVice = new JComboBox(s);
        // Fill for seeming
        s = new String[]{ "Beast", "Darkling", "Elemental", "Fairest", "Ogre", "Wizend" };
        JComboBox cboSeeming = new JComboBox(s);
        //Fill for court
        s = new String[]{ "", "Spring", "Summer", "Autumn", "Winter", "", "Dawn", "Dusk" };
        JComboBox cboCourt = new JComboBox(s);

        // Set input box names
        txtName.setName("name");
        txtPlayer.setName("player");
        txtChronicle.setName("chronicle");
        cboVirtue.setName("virtue");
        cboVice.setName("vice");
        txtConcept.setName("concept");
        cboSeeming.setName("seeming");
        txtKith.setName("kith");
        cboCourt.setName("court");

        // Create the stat labels
        JLabel lblIntelligence = new JLabel("Intelligence"),
                lblWits = new JLabel("Wits"),
                lblResolve = new JLabel("Resolve"),
                lblStrength = new JLabel("Strength"),
                lblDexterity = new JLabel("Dexterity"),
                lblStamina = new JLabel("Stamina"),
                lblPresence = new JLabel("Presence"),
                lblManipulation = new JLabel("Mannipulation"),
                lblComposure = new JLabel("Composure");

        // phew, right, stat arrays...
        // 5 "dots" (ie, radio buttons) per stat (hope to HELL this works)
        // can always try checkboxes another time (tho not round : /)
        // start by creating all te arrays
        JRadioButton[] rdoIntelligence = new JRadioButton[5],
                rdoWits = new JRadioButton[5],
                rdoResolve = new JRadioButton[5],
                rdoStrength = new JRadioButton[5],
                rdoDexterity = new JRadioButton[5],
                rdoStamina = new JRadioButton[5],
                rdoPresence = new JRadioButton[5],
                rdoManipulation = new JRadioButton[5],
                rdoComposure = new JRadioButton[5];

        // now loop and create ALL of the buttons and name them (WOOPWOOP!)
        // Im gonna wish i didn't type all of this later, aint i?
        for ( int i = 0; i < 5; i++ ) {
            rdoIntelligence[i] = new JRadioButton();
            rdoWits[i] = new JRadioButton();
            rdoResolve[i] = new JRadioButton();
            rdoStrength[i] = new JRadioButton();
            rdoDexterity[i] = new JRadioButton();
            rdoStamina[i] = new JRadioButton();
            rdoPresence[i] = new JRadioButton();
            rdoManipulation[i] = new JRadioButton();
            rdoComposure[i] = new JRadioButton();

            // name them all
            rdoIntelligence[i].setName("intelligence " + i);
            rdoWits[i].setName("wits " + i);
            rdoResolve[i].setName("resolve " + i);
            rdoStrength[i].setName("strength " + i);
            rdoDexterity[i].setName("dexterity " + i);
            rdoStamina[i].setName("stamina " + i);
            rdoPresence[i].setName("presence" + i);
            rdoManipulation[i].setName("manipulation" + i);
            rdoComposure[i].setName("composure " + i);

            // since these are the Stats and you get a free dot
            // if this is the first loop, set all of them to checked
            if ( i == 0 ) {
                rdoIntelligence[i].setSelected(true);
                rdoWits[i].setSelected(true);
                rdoResolve[i].setSelected(true);
                rdoStrength[i].setSelected(true);
                rdoDexterity[i].setSelected(true);
                rdoStamina[i].setSelected(true);
                rdoPresence[i].setSelected(true);
                rdoManipulation[i].setSelected(true);
                rdoComposure[i].setSelected(true);
            }
        } // Done componant setup

        // setup the layout
        // sigh
        // Create the layout for the form
        GroupLayout layout = new GroupLayout(contentPane);
        // Set auto-create gaps, makes it look neater
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // tell the panel to use the layout
        contentPane.setLayout(layout);

        // these are biggies, so ive folded them out the way
        // makes it easyer to read

        //<editor-fold desc="Horizontal Group" defaultstate="collapsed">
        // create the Horizontal Layout

        SequentialGroup topSecctionH = layout.createSequentialGroup();
        layout.createSequentialGroup();
        // MID
        topSecctionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblName, txtName))// end name
                .addGroup(sequentialPair(layout, lblPlayer, txtPlayer))// end Player
                .addGroup(sequentialPair(layout, lblChronicle, txtChronicle))// end Chronicle
                );// end MID

        // LHS
        topSecctionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblVirtue, cboVirtue))// end Virtue
                .addGroup(sequentialPair(layout, lblVice, cboVice))// end Vice
                .addGroup(sequentialPair(layout, lblConcept, txtConcept))// end Concept
                );// end LHS

        // RHS
        topSecctionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblSeeming, cboSeeming))// end seeming
                .addGroup(sequentialPair(layout, lblKith, txtKith))// end Kith
                .addGroup(sequentialPair(layout, lblCourt, cboCourt))// end Court
                );// end RHS

        SequentialGroup midSectionH = layout.createSequentialGroup();

        midSectionH.addGroup(layout.createParallelGroup()
                .addGroup(
                sequentialPair(layout, lblIntelligence, sequentialRadioArray(
                layout, rdoIntelligence)))
                .addGroup(sequentialPair(layout, lblWits, sequentialRadioArray(
                layout, rdoWits)))
                .addGroup(sequentialPair(layout, lblResolve,
                sequentialRadioArray(
                layout, rdoResolve))));

        midSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblStrength, sequentialRadioArray(
                layout, rdoStrength)))
                .addGroup(sequentialPair(layout, lblDexterity, sequentialRadioArray(
                layout, rdoDexterity)))
                .addGroup(sequentialPair(layout, lblStamina, sequentialRadioArray(
                layout, rdoStamina))));

        midSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblPresence, sequentialRadioArray(
                layout, rdoPresence)))
                .addGroup(sequentialPair(layout, lblManipulation,
                sequentialRadioArray(
                layout, rdoManipulation)))
                .addGroup(sequentialPair(layout, lblComposure, sequentialRadioArray(
                layout, rdoComposure))));

        //Create a group to add everything into
        ParallelGroup fullh = layout.createParallelGroup();

        fullh.addGroup(topSecctionH);
        fullh.addGroup(midSectionH);
        layout.setHorizontalGroup(fullh);//end horizontal group
        //</editor-fold>

        //<editor-fold desc="Vertical Group" defaultstate="collapsed">

        ParallelGroup topSectionV = layout.createParallelGroup();
        //left hand side
        topSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblName, txtName))// end name
                .addGroup(parallelPair(layout, lblPlayer, txtPlayer))// end player
                .addGroup(parallelPair(layout, lblChronicle, txtChronicle))// end Chronicle
                );//end left hand side

        //MID
        topSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblVirtue, cboVirtue))// end virtue
                .addGroup(parallelPair(layout, lblVice, cboVice))// end vice
                .addGroup(parallelPair(layout, lblConcept, txtConcept))// end Concept
                );//end MID

        //rhs
        topSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblSeeming, cboSeeming))// end Seeming
                .addGroup(parallelPair(layout, lblKith, txtKith))// end kith
                .addGroup(parallelPair(layout, lblCourt, cboCourt))// end Court
                );//end RHS

        ParallelGroup midSectionV = layout.createParallelGroup();

        midSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblIntelligence,
                parallelRadioArray(
                layout, rdoIntelligence)))
                .addGroup(parallelPair(layout, lblWits, parallelRadioArray(
                layout, rdoWits)))
                .addGroup(parallelPair(layout, lblResolve, parallelRadioArray(
                layout, rdoResolve))));

        midSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblStrength, parallelRadioArray(
                layout, rdoStrength)))
                .addGroup(parallelPair(layout, lblDexterity, parallelRadioArray(
                layout, rdoDexterity)))
                .addGroup(parallelPair(layout, lblStamina, parallelRadioArray(
                layout, rdoStamina))));

        midSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblPresence, parallelRadioArray(
                layout, rdoPresence)))
                .addGroup(parallelPair(layout, lblManipulation, parallelRadioArray(
                layout, rdoManipulation)))
                .addGroup(parallelPair(layout, lblComposure, parallelRadioArray(
                layout, rdoComposure))));

        SequentialGroup fullV = layout.createSequentialGroup();

        fullV.addGroup(topSectionV);
        fullV.addGroup(midSectionV);
        layout.setVerticalGroup(fullV);// midSection);// end verticle group
        //</editor-fold>

        // link the sizes of componants
        // link all labels with each other
        // section at a time
        layout.linkSize(lblName, lblPlayer, lblChronicle,
                lblVirtue, lblVice, lblConcept,
                lblSeeming, lblKith, lblCourt);
        layout.linkSize(
                lblIntelligence, lblWits, lblResolve,
                lblStrength, lblDexterity, lblStamina,
                lblPresence, lblManipulation, lblComposure);

        // link all editable items too

        layout.linkSize(txtName, txtPlayer, txtChronicle,
                cboVirtue, cboVice, txtConcept,
                cboSeeming, txtKith, cboCourt);


    }
}
