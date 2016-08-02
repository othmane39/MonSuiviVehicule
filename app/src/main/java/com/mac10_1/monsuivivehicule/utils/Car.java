package com.mac10_1.monsuivivehicule.utils;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Car {

    String immatriculation;
    String imagePath;
    String marque;
    String modele;
    int millesime;
    Facture[] factures;

    public Car(String immatriculation, String imagePath, String marque, String modele, int millesime) {
        this.immatriculation = immatriculation;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return imagePath;
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

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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


}
