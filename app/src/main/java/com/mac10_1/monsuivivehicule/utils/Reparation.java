package com.mac10_1.monsuivivehicule.utils;

import java.util.Date;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Reparation {

    int idRep;
    int idFacture;
    String nom;
    int cout;

    public Reparation(){

    }

    public Reparation(int idRep, int idFacture, String nom, int cout) {
        this.idRep = idRep;
        this.idFacture = idFacture;
        this.nom = nom;
        this.cout = cout;
    }

    public int getIdRep() {
        return idRep;
    }
    public int getIdFacture() {
        return idFacture;
    }
    public String getNom() {
        return nom;
    }
    public int getCout() {
        return cout;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }
    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setCout(int cout) {
        this.cout = cout;
    }

    @Override
    public String toString() {
        return "Reparation{" +
                "idRep=" + idRep +
                ", idFacture=" + idFacture +
                ", nom='" + nom + '\'' +
                ", cout=" + cout +
                '}';
    }
}

