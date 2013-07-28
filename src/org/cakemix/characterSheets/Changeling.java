/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.characterSheets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import org.cakemix.util.ScrollAlert;
import org.cakemix.util.StatTracker;
import org.cakemix.util.Vitals;
import static org.cakemix.util.Functions.*;

/**
 *
 * @author cakemix
 */
public class Changeling extends JFrame implements ActionListener,
        ItemListener {

    JTextField txtName = new JTextField(),
            txtPlayer = new JTextField(),
            txtChronicle = new JTextField(),
            txtConcept = new JTextField(),
            txtKith = new JTextField();
    JComboBox cboVirtue, cboVice, cboSeeming, cboCourt;
    // Create the stat labels
    JLabel lblIntelligence = new JLabel("Intelligence"),
            lblWits = new JLabel("Wits"),
            lblResolve = new JLabel("Resolve"),
            lblStrength = new JLabel("Strength"),
            lblDexterity = new JLabel("Dexterity"),
            lblStamina = new JLabel("Stamina"),
            lblPresence = new JLabel("Presence"),
            lblManipulation = new JLabel("Manipulation"),
            lblComposure = new JLabel("Composure"),
            //Skill labels
            //Mental
            lblAcademics = new JLabel("Academics"),
            lblComputer = new JLabel("Computer"),
            lblCrafts = new JLabel("Crafts"),
            lblInvestigation = new JLabel("Investigation"),
            lblMedicine = new JLabel("Medicine"),
            lblOccult = new JLabel("Occult"),
            lblPolitics = new JLabel("Politics"),
            lblScience = new JLabel("Science"),
            //Physical
            lblAthletics = new JLabel("Athletics"),
            lblBrawl = new JLabel("Brawl"),
            lblDrive = new JLabel("Drive"),
            lblFirearms = new JLabel("Firearms"),
            lblLarceny = new JLabel("Larceny"),
            lblStealth = new JLabel("Stealth"),
            lblSurvival = new JLabel("Survival"),
            lblWeaponry = new JLabel("Weaponry"),
            //Social
            lblAnimalKen = new JLabel("Animal Ken"),
            lblEmpathy = new JLabel("Empathy"),
            lblExpression = new JLabel("Expression"),
            lblIntimidation = new JLabel("Intimidation"),
            lblPersuasion = new JLabel("Persuasion"),
            lblSocialize = new JLabel("Socialize"),
            lblStreetwise = new JLabel("Streetwise"),
            lblSubterfuge = new JLabel("Subterfuge"),
            lblBlessing = new JLabel("Seeming Blessing"),
            lblCurse = new JLabel("Seeming Curse"),
            lblSize = new JLabel("Size");
    /**
     * 5 "dots" (ie, radio buttons) per stat (hope to HELL this works)
     * can always try checkboxes another time (tho not round : /)
     * start by creating all te arrays
     */
    JRadioButton[] rdoIntelligence = new JRadioButton[5],
            rdoWits = new JRadioButton[5],
            rdoResolve = new JRadioButton[5],
            rdoStrength = new JRadioButton[5],
            rdoDexterity = new JRadioButton[5],
            rdoStamina = new JRadioButton[5],
            rdoPresence = new JRadioButton[5],
            rdoManipulation = new JRadioButton[5],
            rdoComposure = new JRadioButton[5],
            //stats
            //Mental
            rdoAcademics = new JRadioButton[5],
            rdoComputer = new JRadioButton[5],
            rdoCrafts = new JRadioButton[5],
            rdoInvestigation = new JRadioButton[5],
            rdoMedicine = new JRadioButton[5],
            rdoOccult = new JRadioButton[5],
            rdoPolitics = new JRadioButton[5],
            rdoScience = new JRadioButton[5],
            //Physical
            rdoAthletics = new JRadioButton[5],
            rdoBrawl = new JRadioButton[5],
            rdoDrive = new JRadioButton[5],
            rdoFirearms = new JRadioButton[5],
            rdoLarceny = new JRadioButton[5],
            rdoStealth = new JRadioButton[5],
            rdoSurvival = new JRadioButton[5],
            rdoWeaponry = new JRadioButton[5],
            //Social
            rdoAnimalKen = new JRadioButton[5],
            rdoEmpathy = new JRadioButton[5],
            rdoExpression = new JRadioButton[5],
            rdoIntimidation = new JRadioButton[5],
            rdoPersuasion = new JRadioButton[5],
            rdoSocialize = new JRadioButton[5],
            rdoStreetwise = new JRadioButton[5],
            rdoSubterfuge = new JRadioButton[5];
    // Skill Specialty List
    JComboBox[] cboSpecialty = new JComboBox[10];
    JTextField[] txtSpecialty = new JTextField[10];
    // Merits are slightly different
    // Text feilds to put the merits in
    JComboBox[] cboMerits = new JComboBox[10];
    // 5 dots for each merit, thus 2d array
    JRadioButton[][] rdoMerits = new JRadioButton[10][5];
    // same again for the contracts
    // Text feilds to put the contracts in
    JComboBox[] cboContracts = new JComboBox[10];
    // 5 dots for each merit, thus 2d array
    JRadioButton[][] rdoContracts = new JRadioButton[10][5];
    // add a stat tracker (to be linked into the gms overall one)
    StatTracker vitals = new StatTracker();
    // character sheet stat storage
    ChangelingStats stats = new ChangelingStats();

    public Changeling() {
        super("Changeling - The Lost");
        buildUI(this.getContentPane());
        buildMenu();
        update();
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        pack();
        setVisible(true);
    }

    //<editor-fold desc="Build UI" defaultstate="collapsed">
    /**
     * Build the UI
     */
    private void buildUI( Container contentPane ) {

        //<editor-fold desc="Header Labels" defaultstate="collapsed">
        JLabel lblGame = new JLabel("Changeling - The Lost");

        //</editor-fold>
        //<editor-fold desc="Character Info Vars" defaultstate="collapsed">
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

        // Create drop downs
        // Create a string[] to hold the options for each one
        // Start off with the virtues
        String[] s = { "Charity", "Faith", "Fortitude", "Hope", "Justice", "Prudence", "Temperance" };
        cboVirtue = new JComboBox<String>(s);
        // Fill for vice
        s = new String[]{ "Envy", "Gluttony", "Greed", "Lust", "Pride", "Sloth", "Wrath" };
        cboVice = new JComboBox(s);
        // Fill for seeming
        s = new String[]{ "Beast", "Darkling", "Elemental", "Fairest", "Ogre", "Wizend" };
        cboSeeming = new JComboBox(s);
        //Fill for court
        s = new String[]{ "Courtless", "Spring", "Summer", "Autumn", "Winter" };
        cboCourt = new JComboBox(s);

        // Set input box names
        txtName.setName("name");
        txtName.setToolTipText("Characters name.");
        txtName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate( DocumentEvent de ) {
                try {
                    stats.name = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void removeUpdate( DocumentEvent de ) {
                try {
                    stats.name = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void changedUpdate( DocumentEvent de ) {
                try {
                    stats.name = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }
        });
        txtPlayer.setName("player");
        txtPlayer.setToolTipText("Your (real) name.");
        txtPlayer.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate( DocumentEvent de ) {
                try {
                    stats.player = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void removeUpdate( DocumentEvent de ) {
                try {
                    stats.player = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void changedUpdate( DocumentEvent de ) {
                try {
                    stats.player = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }
        });
        txtChronicle.setName("chronicle");
        txtChronicle.setToolTipText(
                "Chronicle the character is part of, may be left blank at Storytellers Descretion");
        txtChronicle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate( DocumentEvent de ) {
                try {
                    stats.chronicle = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void removeUpdate( DocumentEvent de ) {
                try {
                    stats.chronicle = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void changedUpdate( DocumentEvent de ) {
                try {
                    stats.chronicle = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }
        });

        txtConcept.setName("concept");
        txtConcept.setToolTipText("Short description of the character");
        txtConcept.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate( DocumentEvent de ) {
                try {
                    stats.concept = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void removeUpdate( DocumentEvent de ) {
                try {
                    stats.concept = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void changedUpdate( DocumentEvent de ) {
                try {
                    stats.concept = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }
        });
        txtKith.setName("kith");
        txtKith.setToolTipText("Characters Kith (optional)");
        txtKith.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate( DocumentEvent de ) {
                try {
                    stats.kith = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void removeUpdate( DocumentEvent de ) {
                try {
                    stats.kith = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }

            @Override
            public void changedUpdate( DocumentEvent de ) {
                try {
                    stats.kith = de.getDocument().getText(0,
                            de.getDocument().getLength());
                } catch ( BadLocationException ex ) {
                    Logger.getLogger(StatTracker.class.getName()).log(
                            Level.SEVERE, null,
                            ex);
                }
            }
        });
        cboVirtue.setName("virtue ");
        cboVirtue.setToolTipText("Characters Virtue");
        cboVirtue.addActionListener(this);

        cboVice.setName("vice ");
        cboVice.setToolTipText("Characters Virtue");
        cboVice.addActionListener(this);

        cboSeeming.setName("seeming ");
        cboSeeming.setToolTipText("Characters Seeming");
        cboSeeming.addActionListener(this);

        cboCourt.setName("court ");
        cboCourt.setToolTipText("Characters Court (optional)");
        cboCourt.addActionListener(this);
//</editor-fold>

        JTabbedPane tabber = new JTabbedPane();


        //<editor-fold desc="Stat and skill Radio Creation (BIG)" defaultstate="collapsed">

        // loop and create ALL of the radios and name them (WOOPWOOP!)
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
            rdoIntelligence[i].setToolTipText("Experience Cost: New Dots x5");
            rdoIntelligence[i].addActionListener(this);
            rdoWits[i].setName("wits " + i);
            rdoWits[i].setToolTipText("Experience Cost: New Dots x5");
            rdoWits[i].addActionListener(this);
            rdoResolve[i].setName("resolve " + i);
            rdoResolve[i].addActionListener(this);
            rdoResolve[i].setToolTipText("Experience Cost: New Dots x5");
            rdoStrength[i].setName("strength " + i);
            rdoStrength[i].setToolTipText("Experience Cost: New Dots x5");
            rdoStrength[i].addActionListener(this);
            rdoDexterity[i].setName("dexterity " + i);
            rdoDexterity[i].setToolTipText("Experience Cost: New Dots x5");
            rdoDexterity[i].addActionListener(this);
            rdoStamina[i].setName("stamina " + i);
            rdoStamina[i].setToolTipText("Experience Cost: New Dots x5");
            rdoStamina[i].addActionListener(this);
            rdoPresence[i].setName("presence " + i);
            rdoPresence[i].setToolTipText("Experience Cost: New Dots x5");
            rdoPresence[i].addActionListener(this);
            rdoManipulation[i].setName("manipulation " + i);
            rdoManipulation[i].setToolTipText("Experience Cost: New Dots x5");
            rdoManipulation[i].addActionListener(this);
            rdoComposure[i].setName("composure " + i);
            rdoComposure[i].setToolTipText("Experience Cost: New Dots x5");
            rdoComposure[i].addActionListener(this);

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

            //Mental
            rdoAcademics[i] = new JRadioButton();
            rdoComputer[i] = new JRadioButton();
            rdoCrafts[i] = new JRadioButton();
            rdoInvestigation[i] = new JRadioButton();
            rdoMedicine[i] = new JRadioButton();
            rdoOccult[i] = new JRadioButton();
            rdoPolitics[i] = new JRadioButton();
            rdoScience[i] = new JRadioButton();
            //Physical
            rdoAthletics[i] = new JRadioButton();
            rdoBrawl[i] = new JRadioButton();
            rdoDrive[i] = new JRadioButton();
            rdoFirearms[i] = new JRadioButton();
            rdoLarceny[i] = new JRadioButton();
            rdoStealth[i] = new JRadioButton();
            rdoSurvival[i] = new JRadioButton();
            rdoWeaponry[i] = new JRadioButton();
            //Social
            rdoAnimalKen[i] = new JRadioButton();
            rdoEmpathy[i] = new JRadioButton();
            rdoExpression[i] = new JRadioButton();
            rdoIntimidation[i] = new JRadioButton();
            rdoPersuasion[i] = new JRadioButton();
            rdoSocialize[i] = new JRadioButton();
            rdoStreetwise[i] = new JRadioButton();
            rdoSubterfuge[i] = new JRadioButton();

            //Naming and Labels
            //Mental
            rdoAcademics[i].setName("academics " + i);
            rdoAcademics[i].setToolTipText("Experience Cost: New Dots x3");
            rdoAcademics[i].addActionListener(this);
            rdoComputer[i].setName("computer " + i);
            rdoComputer[i].setToolTipText("Experience Cost: New Dots x3");
            rdoComputer[i].addActionListener(this);
            rdoCrafts[i].setName("crafts " + i);
            rdoCrafts[i].setToolTipText("Experience Cost: New Dots x3");
            rdoCrafts[i].addActionListener(this);
            rdoInvestigation[i].setName("investigation " + i);
            rdoInvestigation[i].addActionListener(this);
            rdoMedicine[i].setName("medicine " + i);
            rdoMedicine[i].setToolTipText("Experience Cost: New Dots x3");
            rdoMedicine[i].addActionListener(this);
            rdoOccult[i].setName("occult " + i);
            rdoOccult[i].setToolTipText("Experience Cost: New Dots x3");
            rdoOccult[i].addActionListener(this);
            rdoPolitics[i].setName("politics " + i);
            rdoPolitics[i].setToolTipText("Experience Cost: New Dots x3");
            rdoPolitics[i].addActionListener(this);
            rdoScience[i].setName("science " + i);
            rdoScience[i].setToolTipText("Experience Cost: New Dots x3");
            rdoScience[i].addActionListener(this);
            //Physical
            rdoAthletics[i].setName("athletics " + i);
            rdoAthletics[i].setToolTipText("Experience Cost: New Dots x3");
            rdoAthletics[i].addActionListener(this);
            rdoBrawl[i].setName("brawl " + i);
            rdoBrawl[i].setToolTipText("Experience Cost: New Dots x3");
            rdoBrawl[i].addActionListener(this);
            rdoDrive[i].setName("drive " + i);
            rdoDrive[i].setToolTipText("Experience Cost: New Dots x3");
            rdoDrive[i].addActionListener(this);
            rdoFirearms[i].setName("firearms " + i);
            rdoFirearms[i].setToolTipText("Experience Cost: New Dots x3");
            rdoFirearms[i].addActionListener(this);
            rdoLarceny[i].setName("larceny " + i);
            rdoLarceny[i].addActionListener(this);
            rdoLarceny[i].setToolTipText("Experience Cost: New Dots x3");
            rdoStealth[i].setName("stealth " + i);
            rdoStealth[i].addActionListener(this);
            rdoStealth[i].setToolTipText("Experience Cost: New Dots x3");
            rdoSurvival[i].setName("survival " + i);
            rdoSurvival[i].setToolTipText("Experience Cost: New Dots x3");
            rdoSurvival[i].addActionListener(this);
            rdoWeaponry[i].setName("weaponry " + i);
            rdoWeaponry[i].setToolTipText("Experience Cost: New Dots x3");
            rdoWeaponry[i].addActionListener(this);
            //Social
            rdoAnimalKen[i].setName("animal " + i);
            rdoAnimalKen[i].setToolTipText("Experience Cost: New Dots x3");
            rdoAnimalKen[i].addActionListener(this);
            rdoEmpathy[i].setName("empathy " + i);
            rdoEmpathy[i].setToolTipText("Experience Cost: New Dots x3");
            rdoEmpathy[i].addActionListener(this);
            rdoExpression[i].setName("expression " + i);
            rdoExpression[i].setToolTipText("Experience Cost: New Dots x3");
            rdoExpression[i].addActionListener(this);
            rdoIntimidation[i].setName("intimidation " + i);
            rdoIntimidation[i].setToolTipText("Experience Cost: New Dots x3");
            rdoIntimidation[i].addActionListener(this);
            rdoPersuasion[i].setName("persuasion " + i);
            rdoPersuasion[i].setToolTipText("Experience Cost: New Dots x3");
            rdoPersuasion[i].addActionListener(this);
            rdoSocialize[i].setName("socialize " + i);
            rdoSocialize[i].setToolTipText("Experience Cost: New Dots x3");
            rdoSocialize[i].addActionListener(this);
            rdoStreetwise[i].setName("streetwise " + i);
            rdoStreetwise[i].setToolTipText("Experience Cost: New Dots x3");
            rdoStreetwise[i].addActionListener(this);
            rdoSubterfuge[i].setName("subterfuge " + i);
            rdoSubterfuge[i].setToolTipText("Experience Cost: New Dots x3");
            rdoSubterfuge[i].addActionListener(this);

        } // Done componant setup
        //</editor-fold>

        //<editor-fold desc="Merits and Contracts" defaultstate="collapsed">

        // loop again, try and intergrate this with above later on, loop as little as possible
        String[] merit = {
            "--Mental--", "Common Sense", "Danger Sense", "Eidetic Memory",
            "Encyclopedic Knowledge", "Holistic Awareness", "Language", "Meditative Mind",
            "Unseen Sense",
            "--Physical--", "Ambidextrous", "Brawling Dodge", "Direction Sense", "Disarm",
            "Fast Reflexes", "Fighting Finese", "Fighting Style: Boxing", "Fighting Style: Kung Fu",
            "Fighting Style: Two Weapons", "Fleet of Foot", "Fresh Start", "Giant", "Gunslinger",
            "Iron Stamina", "Iron Stomach", "Natural Immunity", "Quick Draw", "Quick Healer",
            "Strong Back", "Strong Lungs", "Stunt Driver", "Toxin Resistance", "Weaponry Dodge",
            "--Social Merits--", "Allies", "Barfly", "Contacts", "Fame", "Inspiring", "Mentor",
            "Resources", "Retainer", "Status", "Striking Looks",
            "--Changeling--", "Court Goodwill: Spring", "Court Goodwill: Summer", "Court Goodwill: Autumn",
            "Court Goodwill: Winter", "Harvest", "Hollow", "Mantle: Spring", "Mantle: Summer", "Mantle: Autumn", "Mantle: Winter", "New Identity", "Token" },
                contract = { "--Seeming--", "Artifice", "Darkness", "Elements", "Fang and Talon", "Stone", "Vainglory",
            "--General--", "Dream", "Hearth", "Mirror", "Smoke",
            "--Court--", "Eternal Spring", "Fleeting Spring", "Eternal Summer", "Fleeting Summer",
            "Eternal Autumn", "Fleeting Autumn", "Eternal Winter", "Fleeting Winter",
            "--Goblin--" },
                speciality = { "--Mental--", "Academics", "Computer", "Crafts", "Investigation", "Medicine",
            "Occult", "Politics", "Science",
            "--Physical--", "Athletics", "Brawl", "Drive", "Firearms", "Larceny", "Stealth",
            "Survival", "Weaponry",
            "--Social--", "Animal Ken", "Empathy", "Intimidation", "Persuasion", "Socialize",
            "Streetwise", "Subterfuge" };
        String ttContracts = "<html>Experience Cost:  <br />(Affinity)New Dots x4 <br />(Non-Affinity)New Dots x6 <br />(Goblin)New Dots x3</html>";
        for ( int i = 0;
                i < 10; i++ ) {
            cboMerits[i] = new JComboBox<>(merit);
            cboMerits[i].setToolTipText("Experience Cost: New Dots x2");
            cboMerits[i].setName("merit name " + i);
            cboMerits[i].addActionListener(this);
            cboContracts[i] = new JComboBox<>(contract);
            cboContracts[i].setName("contract name" + i);
            cboContracts[i].setToolTipText(ttContracts);
            cboContracts[i].addActionListener(this);
            cboSpecialty[i] = new JComboBox<>(speciality);
            cboSpecialty[i].setName("speciality name" + i);
            cboSpecialty[i].setToolTipText("Experience Cost: 3");
            cboSpecialty[i].addActionListener(this);
            txtSpecialty[i] = new JTextField();
            txtSpecialty[i].setName("speciality info" + i);
            txtSpecialty[i].setToolTipText("Skill Speciality Details");
            txtSpecialty[i].addActionListener(this);
            for ( int j = 0; j < 5; j++ ) {
                rdoMerits[i][j] = new JRadioButton();
                rdoMerits[i][j].setName("merit level " + i + "-" + j);
                rdoMerits[i][j].setToolTipText("Experience Cost: New Dots x2");
                rdoMerits[i][j].addActionListener(this);
                rdoContracts[i][j] = new JRadioButton();
                rdoContracts[i][j].setName("contract level " + i + "-" + j);
                rdoContracts[i][j].setToolTipText(ttContracts);
                rdoContracts[i][j].addActionListener(this);
            }
        }
        //</editor-fold>

        //<editor-fold desc="Tool Tips" defaultstate="collapsed">
        // Tool Tips
        lblIntelligence.setToolTipText(
                "Experience Cost: New Dots x5");
        lblWits.setToolTipText(
                "Experience Cost: New Dots x5");
        lblResolve.setToolTipText(
                "Experience Cost: New Dots x5");
        lblStrength.setToolTipText(
                "Experience Cost: New Dots x5");
        lblDexterity.setToolTipText(
                "Experience Cost: New Dots x5");
        lblStamina.setToolTipText(
                "Experience Cost: New Dots x5");
        lblPresence.setToolTipText(
                "Experience Cost: New Dots x5");
        lblManipulation.setToolTipText(
                "Experience Cost: New Dots x5");
        lblComposure.setToolTipText(
                "Experience Cost: New Dots x5");
        //Skill labels
        //Mental
        lblAcademics.setToolTipText(
                "Experience Cost: New Dots x3");
        lblComputer.setToolTipText(
                "Experience Cost: New Dots x3");
        lblCrafts.setToolTipText(
                "Experience Cost: New Dots x3");
        lblInvestigation.setToolTipText(
                "Experience Cost: New Dots x3");
        lblMedicine.setToolTipText(
                "Experience Cost: New Dots x3");
        lblOccult.setToolTipText(
                "Experience Cost: New Dots x3");
        lblPolitics.setToolTipText(
                "Experience Cost: New Dots x3");
        lblScience.setToolTipText(
                "Experience Cost: New Dots x3");
        //Physical
        lblAthletics.setToolTipText(
                "Experience Cost: New Dots x3");
        lblBrawl.setToolTipText(
                "Experience Cost: New Dots x3");
        lblDrive.setToolTipText(
                "Experience Cost: New Dots x3");
        lblFirearms.setToolTipText(
                "Experience Cost: New Dots x3");
        lblLarceny.setToolTipText(
                "Experience Cost: New Dots x3");
        lblStealth.setToolTipText(
                "Experience Cost: New Dots x3");
        lblSurvival.setToolTipText(
                "Experience Cost: New Dots x3");
        lblWeaponry.setToolTipText(
                "Experience Cost: New Dots x3");
        //Social
        lblAnimalKen.setToolTipText(
                "Experience Cost: New Dots x3");
        lblEmpathy.setToolTipText(
                "Experience Cost: New Dots x3");
        lblExpression.setToolTipText(
                "Experience Cost: New Dots x3");
        lblIntimidation.setToolTipText(
                "Experience Cost: New Dots x3");
        lblPersuasion.setToolTipText(
                "Experience Cost: New Dots x3");
        lblSocialize.setToolTipText(
                "Experience Cost: New Dots x3");
        lblStreetwise.setToolTipText(
                "Experience Cost: New Dots x3");
        lblSubterfuge.setToolTipText(
                "Experience Cost: New Dots x3");
        lblBlessing.setToolTipText(
                "Experience Cost: New Dots x3");
        lblCurse.setToolTipText(
                "Experience Cost: New Dots x3");
        lblSize.setToolTipText(
                "Experience Cost: New Dots x3");
        //</editor-fold>

        //<editor-fold desc="Tabs and Layout Setup Creation" defaultstate="collapsed">
        // Create the layout for the form
        GroupLayout layout = new GroupLayout(contentPane);
        // Set auto-create gaps, makes it look neater
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // tell the panel to use the layout
        contentPane.setLayout(layout);
        // create attributes tab
        // first the panel
        JPanel pnlAttributes = new JPanel();
        //now the group layout for that panel
        GroupLayout glAttributes = new GroupLayout(pnlAttributes);
        //then set the layout of the panel
        pnlAttributes.setLayout(glAttributes);
        //now create the tab and add the panel too it
        tabber.addTab("Attributes", pnlAttributes);
        //repeat for the rest of the tabbs
        // Skills
        JPanel pnlSkills = new JPanel();
        GroupLayout glSkills = new GroupLayout(pnlSkills);
        pnlSkills.setLayout(glSkills);
        tabber.addTab("Skills", pnlSkills);
        //Specialty
        JPanel pnlSpec = new JPanel();
        GroupLayout glSpec = new GroupLayout(pnlSpec);
        pnlSpec.setLayout(glSpec);
        tabber.addTab("Skill Specialty", pnlSpec);
        glSpec.setAutoCreateGaps(true);
        glSpec.setAutoCreateContainerGaps(true);
        //Merits
        JPanel pnlMerits = new JPanel();
        GroupLayout glMerits = new GroupLayout(pnlMerits);
        pnlMerits.setLayout(glMerits);
        tabber.addTab("Merits", pnlMerits);
        glMerits.setAutoCreateContainerGaps(true);
        // Contracts
        JPanel pnlContracts = new JPanel();
        GroupLayout glContracts = new GroupLayout(pnlContracts);
        pnlContracts.setLayout(glContracts);
        tabber.addTab("Contracts", pnlContracts);
        glContracts.setAutoCreateContainerGaps(true);
        // Pledges
        JPanel pnlPledges = new JPanel();
        GroupLayout glPledges = new GroupLayout(pnlPledges);
        pnlPledges.setLayout(glPledges);
        tabber.addTab("Pledges", pnlPledges);
        glContracts.setAutoCreateContainerGaps(true);
        // Vitals
        tabber.addTab("Vitals", vitals);

        //</editor-fold>

        //<editor-fold desc="Horizontal Group" defaultstate="collapsed">
        //<editor-fold desc="Character Info" defaultstate="collapsed">
        SequentialGroup characterInfoSectionH = layout.createSequentialGroup();

        // MID
        characterInfoSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblName, txtName))// end name
                .addGroup(sequentialPair(layout, lblPlayer, txtPlayer))// end Player
                .addGroup(sequentialPair(layout, lblChronicle, txtChronicle))// end Chronicle
                );// end MID

        // LHS
        characterInfoSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblVirtue, cboVirtue))// end Virtue
                .addGroup(sequentialPair(layout, lblVice, cboVice))// end Vice
                .addGroup(sequentialPair(layout, lblConcept, txtConcept))// end Concept
                );// end LHS

        // RHS
        characterInfoSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblSeeming, cboSeeming))// end seeming
                .addGroup(sequentialPair(layout, lblKith, txtKith))// end Kith
                .addGroup(sequentialPair(layout, lblCourt, cboCourt))// end Court
                );// end RHS
        //</editor-fold> Character info

        //Create a group to add everything into
        ParallelGroup fullh = layout.createParallelGroup(Alignment.CENTER);

        //game header
        fullh.addGroup(layout.createSequentialGroup().addComponent(lblGame));
        fullh.addGroup(characterInfoSectionH);

        // add tabbed pane
        fullh.addGroup(layout.createSequentialGroup().addComponent(tabber));

        // Finalise the main layout
        layout.setHorizontalGroup(fullh);

        //set the layout for the attributes tab
        glAttributes.setHorizontalGroup(buildAttributesH(glAttributes));

        //set the layout for the skills tab, you get the jist
        glSkills.setHorizontalGroup(buildSkillsH(glSkills));
        glSpec.setHorizontalGroup(buildSpecH(glSpec));
        glMerits.setHorizontalGroup(buildMeritsH(glMerits));
        glContracts.setHorizontalGroup(buildContractsH(glContracts));

        //</editor-fold>

        //<editor-fold desc="Vertical Group" defaultstate="collapsed">


        //<editor-fold desc="Character Info" defaultstate="collapsed">
        ParallelGroup characterInfoSectionV = layout.createParallelGroup(
                GroupLayout.Alignment.CENTER);

        //left hand side
        characterInfoSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblName, txtName))// end name
                .addGroup(parallelPair(layout, lblPlayer, txtPlayer))// end player
                .addGroup(parallelPair(layout, lblChronicle, txtChronicle))// end Chronicle
                );//end left hand side

        //MID
        characterInfoSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblVirtue, cboVirtue))// end virtue
                .addGroup(parallelPair(layout, lblVice, cboVice))// end vice
                .addGroup(parallelPair(layout, lblConcept, txtConcept))// end Concept
                );//end MID

        //rhs
        characterInfoSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblSeeming, cboSeeming))// end Seeming
                .addGroup(parallelPair(layout, lblKith, txtKith))// end kith
                .addGroup(parallelPair(layout, lblCourt, cboCourt))// end Court
                );//end RHS
        //</editor-fold> character info

        SequentialGroup fullV = layout.createSequentialGroup();

        //game Header
        fullV.addGroup(layout.createParallelGroup().addComponent(lblGame));
        fullV.addGroup(characterInfoSectionV);

        // add the tabed pane
        fullV.addGroup(layout.createParallelGroup().addComponent(tabber));
        //finalise the main layout
        layout.setVerticalGroup(fullV);

        // set the attributes tab
        glAttributes.setVerticalGroup(buildAttributesV(glAttributes));

        //set the skills tab
        glSkills.setVerticalGroup(buildSkillsV(glSkills));
        glSpec.setVerticalGroup(buildSpecV(glSpec));
        glMerits.setVerticalGroup(buildMeritsV(glMerits));
        glContracts.setVerticalGroup(buildContractsV(glContracts));

        //</editor-fold>

        //<editor-fold desc="Size Linking" defaultstate="collapsed">
        // link the sizes of componants
        // link all labels with each other
        // section at a time
        layout.linkSize(lblName, lblPlayer, lblChronicle,
                lblVirtue, lblVice, lblConcept,
                lblSeeming, lblKith, lblCourt);

        glAttributes.linkSize(
                lblIntelligence, lblWits, lblResolve,
                lblStrength, lblDexterity, lblStamina,
                lblPresence, lblManipulation, lblComposure);

        glSkills.linkSize(
                //mental
                lblAcademics, lblComputer, lblCrafts, lblInvestigation,
                lblMedicine, lblOccult, lblPolitics, lblScience,
                //Physical
                lblAthletics, lblBrawl, lblDrive, lblFirearms,
                lblLarceny, lblStealth, lblSurvival, lblWeaponry,
                //Social
                lblAnimalKen, lblEmpathy, lblExpression, lblIntimidation,
                lblPersuasion, lblSocialize, lblStreetwise, lblSubterfuge);

        // link all editable items too
        layout.linkSize(txtName, txtPlayer, txtChronicle,
                cboVirtue, cboVice, txtConcept,
                cboSeeming, txtKith, cboCourt);
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="Build Menu" defaultstate="collapsed">
    private void buildMenu() {
        JMenuBar bar = new JMenuBar();

        // file menu
        JMenu menu = new JMenu("File");
        JMenuItem item = new JMenuItem("New");
        item.addActionListener(this);
        menu.add(item);
        item = new JMenuItem("Load");
        item.addActionListener(this);
        menu.add(item);
        item = new JMenuItem("Save");
        item.addActionListener(this);
        menu.add(item);
        item = new JMenuItem("Save as...");
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("From String...");
        item.addActionListener(this);
        menu.add(item);
        item = new JMenuItem("To String...");
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Quit");
        item.addActionListener(this);
        menu.add(item);

        bar.add(menu);

        this.setJMenuBar(bar);

    }
    //</editor-fold>

    //<editor-fold desc="Stats Layout" defaultstate="collapsed">
    private Group buildAttributesH( GroupLayout layout ) {

        SequentialGroup statsSectionH = layout.createSequentialGroup();

        statsSectionH.addGap(10, 75, Short.MAX_VALUE);

        statsSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblIntelligence,
                sequentialRadioArray(layout, rdoIntelligence)))
                .addGroup(sequentialPair(layout, lblWits,
                sequentialRadioArray(layout, rdoWits)))
                .addGroup(sequentialPair(layout, lblResolve,
                sequentialRadioArray(layout, rdoResolve))));

        statsSectionH.addGap(10, 15, 50);

        statsSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblStrength,
                sequentialRadioArray(layout, rdoStrength)))
                .addGroup(sequentialPair(layout, lblDexterity,
                sequentialRadioArray(layout, rdoDexterity)))
                .addGroup(sequentialPair(layout, lblStamina,
                sequentialRadioArray(layout, rdoStamina))));

        statsSectionH.addGap(10, 15, 50);

        statsSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblPresence,
                sequentialRadioArray(layout, rdoPresence)))
                .addGroup(sequentialPair(layout, lblManipulation,
                sequentialRadioArray(layout, rdoManipulation)))
                .addGroup(sequentialPair(layout, lblComposure,
                sequentialRadioArray(layout, rdoComposure))));

        statsSectionH.addGap(10, 75, Short.MAX_VALUE);

        return statsSectionH;
    }

    private Group buildAttributesV( GroupLayout layout ) {
        ParallelGroup statsSectionV = layout.createParallelGroup();

        statsSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblIntelligence,
                parallelRadioArray(layout, rdoIntelligence)))
                .addGroup(parallelPair(layout, lblWits,
                parallelRadioArray(layout, rdoWits)))
                .addGroup(parallelPair(layout, lblResolve,
                parallelRadioArray(layout, rdoResolve))));
        statsSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblStrength,
                parallelRadioArray(layout, rdoStrength)))
                .addGroup(parallelPair(layout, lblDexterity,
                parallelRadioArray(layout, rdoDexterity)))
                .addGroup(parallelPair(layout, lblStamina,
                parallelRadioArray(layout, rdoStamina))));

        statsSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblPresence,
                parallelRadioArray(layout, rdoPresence)))
                .addGroup(parallelPair(layout, lblManipulation,
                parallelRadioArray(layout, rdoManipulation)))
                .addGroup(parallelPair(layout, lblComposure,
                parallelRadioArray(layout, rdoComposure))));

        return statsSectionV;
    }
    //</editor-fold>

    //<editor-fold desc="Skills Layout" defaultstate="collapsed">
    private Group buildSkillsH( GroupLayout layout ) {

        SequentialGroup skillsSectionH = layout.createSequentialGroup();
        // Mental

        skillsSectionH.addGap(10, 75, Short.MAX_VALUE);

        skillsSectionH.addGroup(layout.createParallelGroup()
                .addGroup(
                sequentialPair(layout, lblAcademics, sequentialRadioArray(
                layout, rdoAcademics)))
                .addGroup(sequentialPair(layout, lblComputer,
                sequentialRadioArray(
                layout, rdoComputer)))
                .addGroup(sequentialPair(layout, lblCrafts,
                sequentialRadioArray(
                layout, rdoCrafts)))
                .addGroup(sequentialPair(layout, lblInvestigation,
                sequentialRadioArray(
                layout, rdoInvestigation)))
                .addGroup(sequentialPair(layout, lblMedicine,
                sequentialRadioArray(
                layout, rdoMedicine)))
                .addGroup(sequentialPair(layout, lblOccult,
                sequentialRadioArray(
                layout, rdoOccult)))
                .addGroup(sequentialPair(layout, lblPolitics,
                sequentialRadioArray(
                layout, rdoPolitics)))
                .addGroup(sequentialPair(layout, lblScience,
                sequentialRadioArray(
                layout, rdoScience))));

        skillsSectionH.addGap(10, 15, 50);

        // Physical
        skillsSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblAthletics,
                sequentialRadioArray(
                layout, rdoAthletics)))
                .addGroup(sequentialPair(layout, lblBrawl, sequentialRadioArray(
                layout, rdoBrawl)))
                .addGroup(sequentialPair(layout, lblDrive, sequentialRadioArray(
                layout, rdoDrive)))
                .addGroup(sequentialPair(layout, lblFirearms,
                sequentialRadioArray(
                layout, rdoFirearms)))
                .addGroup(sequentialPair(layout, lblLarceny,
                sequentialRadioArray(
                layout, rdoLarceny)))
                .addGroup(sequentialPair(layout, lblStealth,
                sequentialRadioArray(
                layout, rdoStealth)))
                .addGroup(sequentialPair(layout, lblSurvival,
                sequentialRadioArray(
                layout, rdoSurvival)))
                .addGroup(sequentialPair(layout, lblWeaponry,
                sequentialRadioArray(
                layout, rdoWeaponry))));

        skillsSectionH.addGap(10, 15, 50);

        // Social
        skillsSectionH.addGroup(layout.createParallelGroup()
                .addGroup(sequentialPair(layout, lblAnimalKen,
                sequentialRadioArray(
                layout, rdoAnimalKen)))
                .addGroup(sequentialPair(layout, lblEmpathy,
                sequentialRadioArray(
                layout, rdoEmpathy)))
                .addGroup(sequentialPair(layout, lblExpression,
                sequentialRadioArray(
                layout, rdoExpression)))
                .addGroup(sequentialPair(layout, lblIntimidation,
                sequentialRadioArray(
                layout, rdoIntimidation)))
                .addGroup(sequentialPair(layout, lblPersuasion,
                sequentialRadioArray(
                layout, rdoPersuasion)))
                .addGroup(sequentialPair(layout, lblSocialize,
                sequentialRadioArray(
                layout, rdoSocialize)))
                .addGroup(sequentialPair(layout, lblStreetwise,
                sequentialRadioArray(
                layout, rdoStreetwise)))
                .addGroup(sequentialPair(layout, lblSubterfuge,
                sequentialRadioArray(
                layout, rdoSubterfuge))));

        skillsSectionH.addGap(10, 75, Short.MAX_VALUE);
        return skillsSectionH;
    }

    private Group buildSkillsV( GroupLayout layout ) {
        ParallelGroup skillsSectionV = layout.createParallelGroup(
                GroupLayout.Alignment.CENTER);

        // Mental
        skillsSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(
                parallelPair(layout, lblAcademics, parallelRadioArray(
                layout, rdoAcademics)))
                .addGroup(parallelPair(layout, lblComputer, parallelRadioArray(
                layout, rdoComputer)))
                .addGroup(parallelPair(layout, lblCrafts, parallelRadioArray(
                layout, rdoCrafts)))
                .addGroup(parallelPair(layout, lblInvestigation,
                parallelRadioArray(
                layout, rdoInvestigation)))
                .addGroup(parallelPair(layout, lblMedicine, parallelRadioArray(
                layout, rdoMedicine)))
                .addGroup(parallelPair(layout, lblOccult, parallelRadioArray(
                layout, rdoOccult)))
                .addGroup(parallelPair(layout, lblPolitics, parallelRadioArray(
                layout, rdoPolitics)))
                .addGroup(parallelPair(layout, lblScience, parallelRadioArray(
                layout, rdoScience))));

        // Physical
        skillsSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblAthletics,
                parallelRadioArray(
                layout, rdoAthletics)))
                .addGroup(parallelPair(layout, lblBrawl, parallelRadioArray(
                layout, rdoBrawl)))
                .addGroup(parallelPair(layout, lblDrive, parallelRadioArray(
                layout, rdoDrive)))
                .addGroup(parallelPair(layout, lblFirearms, parallelRadioArray(
                layout, rdoFirearms)))
                .addGroup(parallelPair(layout, lblLarceny, parallelRadioArray(
                layout, rdoLarceny)))
                .addGroup(parallelPair(layout, lblStealth, parallelRadioArray(
                layout, rdoStealth)))
                .addGroup(parallelPair(layout, lblSurvival, parallelRadioArray(
                layout, rdoSurvival)))
                .addGroup(parallelPair(layout, lblWeaponry, parallelRadioArray(
                layout, rdoWeaponry))));

        // Social
        skillsSectionV.addGroup(layout.createSequentialGroup()
                .addGroup(parallelPair(layout, lblAnimalKen,
                parallelRadioArray(
                layout, rdoAnimalKen)))
                .addGroup(parallelPair(layout, lblEmpathy, parallelRadioArray(
                layout, rdoEmpathy)))
                .addGroup(parallelPair(layout, lblExpression,
                parallelRadioArray(
                layout, rdoExpression)))
                .addGroup(parallelPair(layout, lblIntimidation,
                parallelRadioArray(
                layout, rdoIntimidation)))
                .addGroup(parallelPair(layout, lblPersuasion,
                parallelRadioArray(
                layout, rdoPersuasion)))
                .addGroup(parallelPair(layout, lblSocialize, parallelRadioArray(
                layout, rdoSocialize)))
                .addGroup(parallelPair(layout, lblStreetwise,
                parallelRadioArray(
                layout, rdoStreetwise)))
                .addGroup(parallelPair(layout, lblSubterfuge,
                parallelRadioArray(
                layout, rdoSubterfuge))));

        return skillsSectionV;
    }//</editor-fold>

    //<editor-fold desc="Speciality Layout" defaultstate="collapsed">
    private Group buildSpecH( GroupLayout layout ) {
        ParallelGroup specSectionH = layout.createParallelGroup(
                Alignment.CENTER);
        for ( int i = 0; i < cboSpecialty.length; i++ ) {
            specSectionH.addGroup(sequentialPair(layout, cboSpecialty[i],
                    txtSpecialty[i]));
        }
        return specSectionH;
    }

    private Group buildSpecV( GroupLayout layout ) {
        SequentialGroup specSectionV = layout.createSequentialGroup();

        //specSectionV.addGap(5, 10, 15);
        for ( int i = 0; i < cboSpecialty.length; i++ ) {
            specSectionV.addGroup(parallelPair(layout, cboSpecialty[i],
                    txtSpecialty[i]));
            if ( i == cboSpecialty.length - 1 ) {
                //specSectionV.addGap(10, 15, Short.MAX_VALUE);
            } else {
                //specSectionV.addGap(5, 10, 15);
            }

        }
        return specSectionV;
    }
    //</editor-fold>

    //<editor-fold desc="Merits Layout" defaultstate="collapsed">
    private Group buildMeritsH( GroupLayout layout ) {
        ParallelGroup contractsSectionH = layout.createParallelGroup(
                Alignment.CENTER);
        for ( int i = 0; i < cboMerits.length; i++ ) {
            contractsSectionH.addGroup(sequentialPair(layout, cboMerits[i],
                    sequentialRadioArray(layout, rdoMerits[i])));
        }
        return contractsSectionH;
    }

    private Group buildMeritsV( GroupLayout layout ) {
        SequentialGroup contractsSectionV = layout.createSequentialGroup();

        contractsSectionV.addGap(5, 10, 15);
        for ( int i = 0; i < cboMerits.length; i++ ) {
            contractsSectionV.addGroup(parallelPair(layout, cboMerits[i],
                    parallelRadioArray(layout, rdoMerits[i])));
            if ( i == rdoContracts.length - 1 ) {
                contractsSectionV.addGap(10, 15, Short.MAX_VALUE);
            } else {
                contractsSectionV.addGap(5, 10, 15);
            }

        }
        return contractsSectionV;
    }

    //</editor-fold>
    //<editor-fold desc="Contracts Layout" defaultstate="collapsed">
    private Group buildContractsH( GroupLayout layout ) {
        ParallelGroup contractsSectionH = layout.createParallelGroup(
                Alignment.CENTER);
        for ( int i = 0; i < cboContracts.length; i++ ) {
            contractsSectionH.addGroup(sequentialPair(layout, cboContracts[i],
                    sequentialRadioArray(layout, rdoContracts[i])));

        }
        return contractsSectionH;
    }

    private Group buildContractsV( GroupLayout layout ) {
        SequentialGroup contractsSectionV = layout.createSequentialGroup();

        contractsSectionV.addGap(5, 10, 15);
        for ( int i = 0; i < cboContracts.length; i++ ) {
            contractsSectionV.addGroup(parallelPair(layout, cboContracts[i],
                    parallelRadioArray(layout, rdoContracts[i])));
            if ( i == rdoContracts.length - 1 ) {
                contractsSectionV.addGap(10, 15, Short.MAX_VALUE);
            } else {
                contractsSectionV.addGap(5, 10, 15);
            }

        }
        return contractsSectionV;
    }
    //</editor-fold>

    //<editor-fold desc="Derived and Extra Stats" defaultstate="collapsed">
    private Group buildDerivedH( GroupLayout layout ) {
        ParallelGroup meritsSectionH = layout.createParallelGroup();
        for ( int i = 0; i < cboMerits.length; i++ ) {
            meritsSectionH.addGroup(sequentialPair(layout, cboMerits[i],
                    sequentialRadioArray(layout, rdoMerits[i])));
        }
        return meritsSectionH;
    }

    private Group buildDerivedV( GroupLayout layout ) {
        SequentialGroup meritsSectionV = layout.createSequentialGroup();
        for ( int i = 0; i < cboMerits.length; i++ ) {
            meritsSectionV.addGroup(parallelPair(layout, cboMerits[i],
                    parallelRadioArray(layout, rdoMerits[i])));
        }
        return meritsSectionV;
    }
    //</editor-fold>

    @Override
    public void actionPerformed( ActionEvent ae ) {

        if ( ae.getSource() instanceof JMenuItem ) {
            parseMenu(ae);
            System.out.println(ae.getActionCommand());
        } else {

            updateStats(ae);
        }

        update();
    }

    private void parseMenu( ActionEvent ae ) {
        String name = ae.getActionCommand().toLowerCase();
        switch ( name ) {
            case "new":
                stats = new ChangelingStats();
                vitals.setVitals(new Vitals());
                break;
            case "save":
            case "save as...":
                try {
                    new ScrollAlert(stats.toString(),
                            "Manual Save Function... Yup.",
                            "Saveing/Loading not currently implemented, "
                            + "copy output below, then use \"From String...\" function.");
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
                break;

            case "to string...":
                new ScrollAlert(stats.toString() + vitals.getVitals().toString());
                break;

            case "load":
            case "from string...":
                String tmp = JOptionPane.showInputDialog("Enter the string that contains all "
                        + "character info." + '\n'
                        + "Invalid strings will be silently ignored ;)");
                if ( tmp != null ) {
                    stats.fromString(tmp);
                    Vitals v = vitals.getVitals();
                    v.fromString(tmp);
                    vitals.setVitals(v);
                }
                break;

            case "quit":
                this.dispose();
                break;
        }
    }

    protected void updateStats( ActionEvent ae ) {

        String name = parseAeName(ae);
        switch ( name.split(" ")[0] ) {
            case "name":
                stats.name = ((JTextField) ae.getSource()).getText();
                break;
            case "player":
                stats.player = ((JTextField) ae.getSource()).getText();
                break;
            case "chronicle":
                stats.chronicle = ((JTextField) ae.getSource()).getText();
                break;
            case "virtue":
                stats.virtue = ((JComboBox) ae.getSource()).getSelectedIndex();
                break;
            case "vice":
                stats.vice = ((JComboBox) ae.getSource()).getSelectedIndex();
                break;
            case "concept":
                stats.concept = ((JTextField) ae.getSource()).getText();
                break;
            case "seeming":
                stats.seeming = ((JComboBox) ae.getSource()).getSelectedIndex();
                break;
            case "kith":
                stats.kith = ((JTextField) ae.getSource()).getText();
                break;
            case "court":
                stats.court = ((JComboBox) ae.getSource()).getSelectedIndex();
                break;

            case "intelligence":
                stats.intelligence = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "wits":
                stats.wits = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "resolve":
                stats.resolve = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "strength":
                stats.strength = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "dexterity":
                stats.dexterity = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "stamina":
                stats.stamina = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "presence":
                stats.presence = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "manipulation":
                stats.manipulation = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "composure":
                stats.composure = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;

            case "academics":
                stats.academics = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "computer":
                stats.computer = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "crafts":
                stats.crafts = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "investigation":
                stats.investigation = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "medicine":
                stats.medicine = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "occult":
                stats.occult = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "politics":
                stats.politics = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "science":
                stats.science = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;

            case "athletics":
                stats.athletics = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "brawl":
                stats.brawl = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "drive":
                stats.drive = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "firearms":
                stats.firearms = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "larceny":
                stats.larceny = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "stealth":
                stats.stealth = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "survival":
                stats.survival = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "weaponry":
                stats.weaponry = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;

            case "animal":
                stats.animalKen = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "empathy":
                stats.empathy = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "expression":
                stats.expression = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "intimidation":
                stats.intimidation = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "persuasion":
                stats.persuasion = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "socialize":
                stats.socialize = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "streetwise":
                stats.streetwise = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;
            case "subterfuge":
                stats.subterfuge = updateStatDots(
                        ((JRadioButton) ae.getSource()).isSelected(), name);
                break;

            case "merit":
                break;
        }
    }

    public void update() {
        for ( int root = 0; root < this.getContentPane().getComponentCount(); root++ ) {
            Component cmpt = this.getContentPane().getComponent(root);
            if ( cmpt instanceof JTabbedPane ) {
                updateGui((Container) ((JTabbedPane) cmpt).getSelectedComponent());
            }
        }
    }

    public void updateGui( Container cont ) {
        for ( int i = 0; i < cont.getComponentCount(); i++ ) {
            Component c = cont.getComponent(i);

            String name = c.getName();
            if ( name != null ) {
                switch ( name.split(" ")[0] ) {

                    case "name":
                        ((JTextField) c).setText(stats.name);
                        break;
                    case "player":
                        ((JTextField) c).setText(stats.player);
                        break;
                    case "chronicle":
                         ((JTextField) c).setText(stats.chronicle);
                        break;
                    case "virtue":
                         ((JComboBox) c).setSelectedIndex(stats.virtue);
                        break;
                    case "vice":
                         ((JComboBox) c).setSelectedIndex(stats.vice );
                        break;
                    case "concept":
                         ((JTextField) c).setText(stats.concept);
                        break;
                    case "seeming":
                       ((JComboBox) c).setSelectedIndex( stats.seeming);
                        break;
                    case "kith":
                        ((JTextField) c).setText(stats.kith);
                        break;
                    case "court":
                        ((JComboBox) c).setSelectedIndex(stats.court );
                        break;

                    case "intelligence":
                        if ( stats.intelligence <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "wits":
                        if ( stats.wits <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "resolve":
                        if ( stats.resolve <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "strength":
                        if ( stats.strength <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "dexterity":
                        if ( stats.dexterity <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "stamina":
                        if ( stats.stamina <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "presence":
                        if ( stats.presence <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "manipulation":
                        if ( stats.manipulation <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "composure":
                        if ( stats.composure <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }

                    case "academics":
                        if ( stats.academics <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "computer":
                        if ( stats.computer <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "crafts":
                        if ( stats.crafts <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "investigation":
                        if ( stats.investigation <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "medicine":
                        if ( stats.medicine <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "occult":
                        if ( stats.occult <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "politics":
                        if ( stats.politics <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "science":
                        if ( stats.science <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }

                    case "athletics":
                        if ( stats.athletics <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "brawl":
                        if ( stats.brawl <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "drive":
                        if ( stats.drive <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "firearms":
                        if ( stats.firearms <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "larceny":
                        if ( stats.larceny <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "stealth":
                        if ( stats.stealth <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "survival":
                        if ( stats.survival <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "weaponry":
                        if ( stats.weaponry <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }

                    case "animal":
                        if ( stats.animalKen <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "empathy":
                        if ( stats.empathy <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "expression":
                        if ( stats.expression <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "intimidation":
                        if ( stats.intimidation <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "persuasion":
                        if ( stats.persuasion <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "socialize":
                        if ( stats.socialize <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "streetwise":
                        if ( stats.streetwise <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }
                    case "subterfuge":
                        if ( stats.subterfuge <= Integer.parseInt(name.split(
                                " ")[1]) ) {
                            ((JRadioButton) c).setSelected(false);
                            break;
                        } else {
                            ((JRadioButton) c).setSelected(true);
                            break;
                        }

                    case "merit":
                        break;
                }
            }
        }

        Vitals v = vitals.getVitals();
        v.health = stats.stamina + v.size;
        v.willpower = stats.resolve + stats.composure;
        v.speed = stats.strength + stats.dexterity + 5;
        v.defense = (stats.wits > stats.dexterity) ? stats.wits : stats.dexterity;
        vitals.setVitals(v);
    }

    @Override
    public void itemStateChanged( ItemEvent ie ) {
    }
}
