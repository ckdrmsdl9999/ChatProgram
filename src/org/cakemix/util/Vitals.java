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

    public int health, willpower, usedWill, power, powerLeft, afinity;
    public String damage;

    public Vitals() {

        this(6, "                ", 2, 0, 10, 5, 1);
    }

    public Vitals( int hp, String cHp, int will, int uWill, int pow, int powL,
            int af ) {
        health = hp;
        damage = cHp;
        willpower = will;
        usedWill = uWill;
        power = pow;
        powerLeft = powL;
        afinity = af;
    }

    // What the fuck was i thinking when i wrote this?
    // Have a look at it and try and make it readable
    // that means re writing alot in StatTracker too no doubt...
    // *sigh*
    public void updateDamage( char var, int val ) {

            char[] dmg = damage.toCharArray();
            dmg[val] = var;
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
                + "afinity," + afinity + ";";

    }
}
