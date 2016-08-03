package com.mac10_1.monsuivivehicule.utils;

import java.util.Arrays;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Car {

    int id;
    String immatriculation;
    String nchassis;
    String marque;
    String modele;
    int logo;
    int millesime;
    Facture[] factures;

    public Car() {
    }

    public Car(String immatriculation, String nchassis, String marque, String modele, int millesime) {
        this.immatriculation = immatriculation;
        this.nchassis = nchassis;
        this.marque = marque;
        this.modele = modele;
        this.millesime = millesime;
    }

    public Car(String immatriculation, String marque, String modele) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getNchassis() {
        return nchassis;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public int getMillesime() {
        return millesime;
    }

    public int getLogo(){
        return logo;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setNchassis(String imagePath) {
        this.nchassis = imagePath;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setMillesime(int millesime) {
        this.millesime = millesime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "immatriculation='" + immatriculation + '\'' +
                ", nchassis='" + nchassis + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", logo=" + logo +
                ", millesime=" + millesime +
                ", factures=" + Arrays.toString(factures) +
                '}';
    }
}
