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
    String[] goblinContracts = new String[10];
    int age, apparentAge;
    String background, description, eyes, hair, height, weight, race, sex;
    // Becase the code just got messy...
    String midSplit = Character.toString((char) 888),
            endSplit = Character.toString((char) 889);

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

//        name = " ";
//        player = " ";
//        chronicle = " ";
//        concept = " ";
//        kith = " ";

        for ( int i = 0; i < 10; i++ ) {
            contractLevels[i] = 0;
            contracts[i] = 0;
            merits[i] = 0;
            meritLevels[i] = 0;
//            specialtiesDescription[i] = " ";
            specialtiesStat[i] = 0;
//            goblinContracts[i] = " ";
        }
        age = 25;
        apparentAge = 25;
//        description = "Short description of how your character looks.";
//        background = "Breif background information about your character.";
//        eyes = "Characters eyecolour.";
//        hair = "Hair colour and style.";
//        height = "Characters height.";
//        weight = "Characters weight.";
//        race = "Characters race.";
//        sex = "Characters sex.";

    }

    public void fromString( String s ) {
        String[] split = s.split(endSplit);
        for ( String stat : split ) {
            switch ( stat.split(midSplit)[0] ) {
                case "name":
                    if ( stat.split(midSplit).length > 1 ) {
                        name = stat.split(midSplit)[1];
                    }
                    break;
                case "player":
                    if ( stat.split(midSplit).length > 1 ) {
                        player = stat.split(midSplit)[1];
                    }
                    break;
                case "chronicle":
                    if ( stat.split(midSplit).length > 1 ) {
                        chronicle = stat.split(midSplit)[1];
                    }
                    break;
                case "virtue":
                    virtue = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "vice":
                    vice = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "concept":
                    if ( stat.split(midSplit).length > 1 ) {
                        concept = stat.split(midSplit)[1];
                    }
                    break;
                case "seeming":
                    seeming = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "kith":
                    if ( stat.split(midSplit).length > 1 ) {
                        kith = stat.split(midSplit)[1];
                    }
                    break;
                case "court":
                    court = Integer.parseInt(stat.split(midSplit)[1]);
                    break;

                case "intelligence":
                    intelligence = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "wits":
                    wits = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "resolve":
                    resolve = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "strength":
                    strength = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "dexterity":
                    dexterity = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "stamina":
                    stamina = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "presence":
                    presence = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "manipulation":
                    manipulation = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "composure":
                    composure = Integer.parseInt(stat.split(midSplit)[1]);
                    break;

                case "academics":
                    academics = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "computer":
                    computer = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "crafts":
                    crafts = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "investigation":
                    investigation = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "medicine":
                    medicine = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "occult":
                    occult = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "politics":
                    politics = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "science":
                    science = Integer.parseInt(stat.split(midSplit)[1]);
                    break;

                case "athletics":
                    athletics = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "brawl":
                    brawl = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "drive":
                    drive = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "firearms":
                    firearms = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "larceny":
                    larceny = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "stealth":
                    stealth = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "survival":
                    survival = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "weaponry":
                    weaponry = Integer.parseInt(stat.split(midSplit)[1]);
                    break;

                case "animalKen":
                    animalKen = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "empathy":
                    empathy = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "expression":
                    expression = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "intimidation":
                    intimidation = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "persuasion":
                    persuasion = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "socialize":
                    socialize = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "streetwise":
                    streetwise = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "subterfuge":
                    subterfuge = Integer.parseInt(stat.split(midSplit)[1]);
                    break;

                case "merit":
                    if ( "level".equals(stat.split(midSplit)[1]) ) {
                        meritLevels[Integer.parseInt(stat.split(midSplit)[2])] =
                                Integer.parseInt(stat.split(midSplit)[3]);
                    } else {
                        merits[Integer.parseInt(stat.split(midSplit)[1])] =
                                Integer.parseInt(stat.split(midSplit)[2]);
                    }
                    break;
                case "contract":
                    if ( "level".equals(stat.split(midSplit)[1]) ) {
                        contractLevels[Integer.parseInt(stat.split(midSplit)[2])] =
                                Integer.parseInt(stat.split(midSplit)[3]);
                    } else {
                        contracts[Integer.parseInt(stat.split(midSplit)[1])] =
                                Integer.parseInt(stat.split(midSplit)[2]);
                    }
                    break;
                case "specialty":
                    if ( "description".equals(stat.split(midSplit)[1]) ) {

                        if ( stat.split(midSplit).length > 3 ) {
                            specialtiesDescription[Integer.parseInt(
                                    stat.split(midSplit)[2])] = stat.split(
                                    midSplit)[3];
                        }
                    } else {
                        specialtiesStat[Integer.parseInt(stat.split(midSplit)[1])] =
                                Integer.parseInt(stat.split(midSplit)[2]);
                    }
                    break;
                case "goblin":

                    if ( stat.split(midSplit).length > 2 ) {
                        goblinContracts[Integer.parseInt(stat.split(midSplit)[1])] = stat.split(
                                midSplit)[2];
                    }
                    break;

                case "age":
                    age = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "apparentAge":
                    apparentAge = Integer.parseInt(stat.split(midSplit)[1]);
                    break;
                case "background":
                    if ( stat.split(midSplit).length > 2 ) {
                        background = stat.split(midSplit)[1];
                    }
                    break;
                case "description":
                    if ( stat.split(midSplit).length > 2 ) {
                        description = stat.split(midSplit)[1];
                    }
                    break;
                case "eyes":
                    if ( stat.split(midSplit).length > 2 ) {
                        eyes = stat.split(midSplit)[1];
                    }
                    break;
                case "hair":
                    if ( stat.split(midSplit).length > 2 ) {
                        hair = stat.split(midSplit)[1];
                    }
                    break;
                case "height":
                    if ( stat.split(midSplit).length > 2 ) {
                        height = stat.split(midSplit)[1];
                    }
                    break;
                case "weight":
                    if ( stat.split(midSplit).length > 2 ) {
                        weight = stat.split(midSplit)[1];
                    }
                    break;
                case "race":
                    if ( stat.split(midSplit).length > 2 ) {
                        race = stat.split(midSplit)[1];
                    }
                    break;
                case "sex":
                    if ( stat.split(midSplit).length > 2 ) {
                        sex = stat.split(midSplit)[1];
                    }
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String val = "name" + midSplit + name + endSplit
                + "player" + midSplit + player + endSplit
                + "chronicle" + midSplit + chronicle + endSplit
                + "virtue" + midSplit + virtue + endSplit
                + "vice" + midSplit + vice + endSplit
                + "concept" + midSplit + concept + endSplit
                + "seeming" + midSplit + seeming + endSplit
                + "kith" + midSplit + kith + endSplit
                + "court" + midSplit + court + endSplit
                + "intelligence" + midSplit + intelligence + endSplit
                + "wits" + midSplit + wits + endSplit
                + "resolve" + midSplit + resolve + endSplit
                + "strength" + midSplit + strength + endSplit
                + "dexterity" + midSplit + dexterity + endSplit
                + "stamina" + midSplit + stamina + endSplit
                + "presence" + midSplit + presence + endSplit
                + "manipulation" + midSplit + manipulation + endSplit
                + "composure" + midSplit + composure + endSplit
                + ""
                + "academics" + midSplit + academics + endSplit
                + "computer" + midSplit + computer + endSplit
                + "crafts" + midSplit + crafts + endSplit
                + "investigation" + midSplit + investigation + endSplit
                + "medicine" + midSplit + medicine + endSplit
                + "occult" + midSplit + occult + endSplit
                + "politics" + midSplit + politics + endSplit
                + "science" + midSplit + science + endSplit
                + ""
                + "athletics" + midSplit + athletics + endSplit
                + "brawl" + midSplit + brawl + endSplit
                + "drive" + midSplit + drive + endSplit
                + "firearms" + midSplit + firearms + endSplit
                + "larceny" + midSplit + larceny + endSplit
                + "stealth" + midSplit + stealth + endSplit
                + "survival" + midSplit + survival + endSplit
                + "weaponry" + midSplit + weaponry + endSplit
                + ""
                + "animalKen" + midSplit + animalKen + endSplit
                + "empathy" + midSplit + empathy + endSplit
                + "expression" + midSplit + expression + endSplit
                + "intimidation" + midSplit + intimidation + endSplit
                + "persuasion" + midSplit + persuasion + endSplit
                + "socialize" + midSplit + socialize + endSplit
                + "streetwise" + midSplit + streetwise + endSplit
                + "subterfuge" + midSplit + subterfuge + endSplit
                + "age" + midSplit + age + endSplit
                + "apparentAge" + midSplit + apparentAge + endSplit
                + "background" + midSplit + background + endSplit
                + "description" + midSplit + description + endSplit
                + "eyes" + midSplit + eyes + endSplit
                + "hair" + midSplit + hair + endSplit
                + "height" + midSplit + height + endSplit
                + "weight" + midSplit + weight + endSplit
                + "race" + midSplit + race + endSplit
                + "sex" + midSplit + sex + endSplit;


        for ( int i = 0; i < 10; i++ ) {
            val += "merit" + midSplit + i + midSplit + merits[i] + endSplit
                    + "merit" + midSplit + "level" + midSplit + i + midSplit + meritLevels[i] + endSplit
                    + "contract" + midSplit + i + midSplit + contracts[i] + endSplit
                    + "contract" + midSplit + "level" + midSplit + i + midSplit + contractLevels[i] + endSplit
                    + "specialty" + midSplit + i + midSplit + specialtiesStat[i] + endSplit
                    + "specialty" + midSplit + "description" + midSplit + i + midSplit + specialtiesDescription[i] + endSplit
                    + "goblin" + midSplit + i + midSplit + goblinContracts[i] + endSplit
                    + "";

        }

        return val;
    }
}