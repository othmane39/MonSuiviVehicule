package com.mac10_1.monsuivivehicule.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Car implements Parcelable {

    int idCar;
    String immatriculation;
    String nchassis;
    String marque;
    String modele;
    int logo;
    int millesime;
    List<MemoCar> memoCar;
    List<Facture> factures;

    public Car() {
    }

    public Car(Parcel in){
        readFromParcel(in);
    }

    public Car(int id_car, String immatriculation, String nchassis, String marque, String modele, int logo, int millesime) {
        this.idCar = id_car;
        this.immatriculation = immatriculation;
        this.nchassis = nchassis;
        this.marque = marque;
        this.modele = modele;
        this.logo = logo;
        this.millesime = millesime;
    }

    public int getId_car() {
        return idCar;
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
    public int getLogo() {
        return logo;
    }
    public int getMillesime() {
        return millesime;
    }
    public List<MemoCar> getMemoCar() {
        return memoCar;
    }
    public List<Facture> getFactures() {
        return factures;
    }

    public void setId_car(int id_car) {
        this.idCar = id_car;
    }
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
    public void setNchassis(String nchassis) {
        this.nchassis = nchassis;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public void setLogo(int logo) {
        this.logo = logo;
    }
    public void setMillesime(int millesime) {
        this.millesime = millesime;
    }
    public void setMemoCar(List<MemoCar> memoCar) {
        this.memoCar = memoCar;
    }
    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    @Override
    public String toString() {
        return "Car{" +
                "idCar=" + idCar +
                ", immatriculation='" + immatriculation + '\'' +
                ", nchassis='" + nchassis + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", logo=" + logo +
                ", millesime=" + millesime +
                ", memoCar=" + memoCar +
                ", factures=" + factures +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idCar);
        dest.writeString(immatriculation);
        dest.writeString(nchassis);
        dest.writeString(marque);
        dest.writeString(modele);
        dest.writeInt(logo);
        dest.writeInt(millesime);

    }

    public void readFromParcel(Parcel in){
        idCar = in.readInt();
        immatriculation = in.readString();
        nchassis = in.readString();
        marque = in.readString();
        modele = in.readString();
        logo = in.readInt();
        millesime = in.readInt();

    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Car createFromParcel(Parcel in) {
                    return new Car(in);
                }

                public Car[] newArray(int size) {
                    return new Car[size];
                }
            };

}
