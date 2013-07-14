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

    protected int health,willpower,usedWill,power,powerLeft,afinity;
    protected String damage;

    public Vitals(){

        this(6,"                ",2,0,10,5,1);
    }

    public Vitals(int hp, String cHp, int will, int uWill, int pow, int powL, int af){
        health = hp;
        damage = cHp;
        willpower = will;
        usedWill = uWill;
        power = pow;
        powerLeft = powL;
        afinity = af;
    }

    public void setStats(String var, int val){
        if (var.length() > 2){
            char[] dmg = damage.toCharArray();
            dmg[val] = var.charAt(0);
            damage = new String(dmg);
        }
        if (var.length() == 2){
            switch (var){
                case "hp":
                    health = val;
                    break;
                case "wp":
                    willpower = val;
                    break;
                case "uw":
                    usedWill = val;
                    break;
                case "po":
                    power = val;
                    break;
                case "pl":
                    powerLeft = val;
                    break;
                case "af":
                    afinity = val;
                    break;
            }
        }
    }

}
