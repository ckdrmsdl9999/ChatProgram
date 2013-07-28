/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.util;

/**
 *
 * @author cakemix
 */
public class Vitals {

    public int health, willpower, usedWill, power, powerLeft, afinity, clarity;
    public String damage;
    // Derived Stats
    public int speed, size, defense,  initiative, experience;
    public String armor;

    public Vitals() {

        this(6, "                ", 2, 0, 10, 5, 1, 7);
    }

    public Vitals( int hp, String cHp, int will, int uWill, int pow, int powL,
            int af, int cl ) {
        health = hp;
        damage = cHp;
        willpower = will;
        usedWill = uWill;
        power = pow;
        powerLeft = powL;
        afinity = af;
        clarity = cl;
        armor = "0/0";
        experience = 0;
    }

    // What the fuck was i thinking when i wrote this?
    // Have a look at it and try and make it readable
    // that means re writing alot in StatTracker too no doubt...
    // *sigh*
    public void updateDamage( char val, int loc ) {

        char[] dmg = damage.toCharArray();
        dmg[loc] = val;
        damage = new String(dmg);

    }

    public void fromString( String s ) {
        String[] split = s.split(";");
        for ( String stat : split ) {
            switch ( stat.split(",")[0] ) {
                case "health":
                    health = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "damage":
                    damage = stat.split(",")[1];
                    break;
                case "willpower":
                    willpower = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "usedwill":
                    usedWill = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "power":
                    power = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "powerleft":
                    powerLeft = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "afinity":
                    afinity = Integer.parseInt(stat.split(",")[1]);
                    break;
                case "clarity":
                    clarity = Integer.parseInt(stat.split(" ")[1]);
                    break;
                case "size":
                    size = Integer.parseInt(stat.split(" ")[1]);
                    break;
                case "speed":
                    speed = Integer.parseInt(stat.split(" ")[1]);
                    break;
                case "defense":
                    defense = Integer.parseInt(stat.split(" ")[1]);
                    break;
                case "armor":
                    armor = stat.split(" ")[1];
                    break;
                case "initiative":
                    initiative = Integer.parseInt(stat.split(" ")[1]);
                    break;
                case "experience":
                    experience = Integer.parseInt(stat.split(" ")[1]);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return ""
                + "health," + health + ";"
                + "damage," + damage + ";"
                + "willpower," + willpower + ";"
                + "usedwill," + usedWill + ";"
                + "power," + power + ";"
                + "powerleft," + powerLeft + ";"
                + "afinity," + afinity + ";"
                + "clarity," + clarity + ";"
                + "size," + size + ";"
                + "speed," + speed + ";"
                + "defense," + defense + ";"
                + "armor," + armor + ";"
                + "initiative," + initiative + ";"
                + "experience," + experience + ";";

    }
}
