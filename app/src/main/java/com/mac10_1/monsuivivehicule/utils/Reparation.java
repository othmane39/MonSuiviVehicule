package com.mac10_1.monsuivivehicule.utils;



/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Reparation {

    int idRep;
    int idFacture;
    String nom;
    double cout;

    public Reparation(){

    }

    public Reparation(int idRep, int idFacture, String nom, double cout) {
        this.idRep = idRep;
        this.idFacture = idFacture;
        this.nom = nom;
        this.cout = cout;
    }

    public Reparation(String nom, double cout) {
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
    public double getCout() {
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
    public void setCout(double cout) {
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

