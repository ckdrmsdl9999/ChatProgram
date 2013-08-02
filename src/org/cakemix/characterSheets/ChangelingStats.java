/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.characterSheets;

/**
 *
 * @author cakemix
 */
public class ChangelingStats {

    String name, player, chronicle, concept, kith;
    int virtue, vice, seeming, court;
    int intelligence, wits, resolve,
            strength, dexterity, stamina,
            presence, manipulation, composure,
            //Mental
            academics,
            computer,
            crafts,
            investigation,
            medicine,
            occult,
            politics,
            science,
            //Physical
            athletics,
            brawl,
            drive,
            firearms,
            larceny,
            stealth,
            survival,
            weaponry,
            //Social
            animalKen,
            empathy,
            expression,
            intimidation,
            persuasion,
            socialize,
            streetwise,
            subterfuge,
            size;
    int[] specialtiesStat = new int[10],
            merits = new int[10],
            meritLevels = new int[10],
            contracts = new int[10],
            contractLevels = new int[10];
    String[] specialtiesDescription = new String[10];

    public ChangelingStats() {
        intelligence = 1;
        wits = 1;
        resolve = 1;

        strength = 1;
        dexterity = 1;
        stamina = 1;

        presence = 1;
        manipulation = 1;
        composure = 1;

        academics = 0;
        computer = 0;
        crafts = 0;
        investigation = 0;
        medicine = 0;
        occult = 0;
        politics = 0;
        science = 0;
        //Physical
        athletics = 0;
        brawl = 0;
        drive = 0;
        firearms = 0;
        larceny = 0;
        stealth = 0;
        survival = 0;
        weaponry = 0;
        //Social
        animalKen = 0;
        empathy = 0;
        expression = 0;
        intimidation = 0;
        persuasion = 0;
        socialize = 0;
        streetwise = 0;
        subterfuge = 0;

        name = " ";
        player = " ";
        chronicle = " ";
        concept = " ";
        kith = " ";

        for ( int i = 0; i < 10; i++ ) {
            contractLevels[i] = 0;
            contracts[i] = 0;
            merits[i] = 0;
            meritLevels[i] = 0;
            specialtiesDescription[i] = " ";
            specialtiesStat[i] = 0;
        }

    }

    public void fromString( String s ) {
        String[] split = s.split(";");
        for ( String stat : split ) {
            switch ( stat.split(",")[0] ) {
                case "name":
                    name = stat.split(",")[1];
                    break;
                case "player":
                    player = stat.split(",")[1];
                    break;
                case "chronicle":
                    chronicle = stat.split(",")[1];
                    break;
                case "virtue":
                    virtue = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "vice":
                    vice = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "concept":
                    concept = stat.split(",")[1];
                    break;
                case "seeming":
                    seeming = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "kith":
                    kith = stat.split(",")[1];
                    break;
                case "court":
                    court = Integer.parseInt(stat.split(",")[1]);
                    break;

                case "intelligence":
                    intelligence = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "wits":
                    wits = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "resolve":
                    resolve = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "strength":
                    strength = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "dexterity":
                    dexterity = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "stamina":
                    stamina = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "presence":
                    presence = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "manipulation":
                    manipulation = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "composure":
                    composure = Integer.parseInt(stat.split(",")[1]);
                    break;

                case "academics":
                    academics = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "computer":
                    computer = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "crafts":
                    crafts = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "investigation":
                    investigation = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "medicine":
                    medicine = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "occult":
                    occult = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "politics":
                    politics = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "science":
                    science = Integer.parseInt(stat.split(",")[1]);
                    break;

                case "athletics":
                    athletics = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "brawl":
                    brawl = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "drive":
                    drive = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "firearms":
                    firearms = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "larceny":
                    larceny = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "stealth":
                    stealth = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "survival":
                    survival = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "weaponry":
                    weaponry = Integer.parseInt(stat.split(",")[1]);
                    break;

                case "animalKen":
                    animalKen = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "empathy":
                    empathy = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "expression":
                    expression = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "intimidation":
                    intimidation = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "persuasion":
                    persuasion = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "socialize":
                    socialize = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "streetwise":
                    streetwise = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "subterfuge":
                    subterfuge = Integer.parseInt(stat.split(",")[1]);
                    break;

                case "merit":
                    if ( "level".equals(stat.split(",")[1]) ) {
                    meritLevels[Integer.parseInt(stat.split(",")[2])] =
                            Integer.parseInt(stat.split(",")[3]);
                    } else {
                        merits[Integer.parseInt(stat.split(",")[1])] =
                            Integer.parseInt(stat.split(",")[2]);
                    }
                    break;
                case "contract":
                    if ( "level".equals(stat.split(",")[1]) ) {
                        contractLevels[Integer.parseInt(stat.split(",")[2])] =
                            Integer.parseInt(stat.split(",")[3]);
                    } else {
                        contracts[Integer.parseInt(stat.split(",")[1])] =
                            Integer.parseInt(stat.split(",")[2]);
                    }
                    break;
                case "specialty":
                    if ( "description".equals(stat.split(",")[1]) ) {
                        specialtiesDescription[Integer.parseInt(stat.split(",")[2])]
                                = stat.split(",")[3];
                    } else {
                        specialtiesStat[Integer.parseInt(stat.split(",")[1])] =
                            Integer.parseInt(stat.split(",")[2]);
                    }
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String val = "name," + name + ";"
                + "player," + player + ";"
                + "chronicle," + chronicle + ";"
                + "virtue," + virtue + ";"
                + "vice," + vice + ";"
                + "concept," + concept + ";"
                + "seeming," + seeming + ";"
                + "kith," + kith + ";"
                + "court," + court + ";"
                + "intelligence," + intelligence + ";"
                + "wits," + wits + ";"
                + "resolve," + resolve + ";"
                + "strength," + strength + ";"
                + "dexterity," + dexterity + ";"
                + "stamina," + stamina + ";"
                + "presence," + presence + ";"
                + "manipulation," + manipulation + ";"
                + "composure," + composure + ";"
                + ""
                + "academics," + academics + ";"
                + "computer," + computer + ";"
                + "crafts," + crafts + ";"
                + "investigation," + investigation + ";"
                + "medicine," + medicine + ";"
                + "occult," + occult + ";"
                + "politics," + politics + ";"
                + "science," + science + ";"
                + ""
                + "athletics," + athletics + ";"
                + "brawl," + brawl + ";"
                + "drive," + drive + ";"
                + "firearms," + firearms + ";"
                + "larceny," + larceny + ";"
                + "stealth," + stealth + ";"
                + "survival," + survival + ";"
                + "weaponry," + weaponry + ";"
                + ""
                + "animalKen," + animalKen + ";"
                + "empathy," + empathy + ";"
                + "expression," + expression + ";"
                + "intimidation," + intimidation + ";"
                + "persuasion," + persuasion + ";"
                + "socialize," + socialize + ";"
                + "streetwise," + streetwise + ";"
                + "subterfuge," + subterfuge + ";";

        for ( int i = 0; i < 10; i++ ) {
            val += "merit,"             + i + "," + merits[i] + ";"
                    + "merit,level,"           + i + "," + meritLevels[i] + ";"
                    + "contract,"       + i + "," + contracts[i] + ";"
                    + "contract,level,"        + i + "," + contractLevels[i] + ";"
                    + "specialty,"      + i + "," + specialtiesStat[i] + ";"
                    + "specialty,description," + i + "," + specialtiesDescription[i] + ";"
                    + "";

        }

        return val;
    }
}