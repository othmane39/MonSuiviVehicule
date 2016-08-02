package com.mac10_1.monsuivivehicule.utils;

import java.util.Date;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Reparation {

    String nom;
    int cout;

    public Reparation(String nom, int cout) {
        this.nom = nom;
        this.cout = cout;
    }

    public String getNom() {
        return nom;
    }

    public int getCout() {
        return cout;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }
}
