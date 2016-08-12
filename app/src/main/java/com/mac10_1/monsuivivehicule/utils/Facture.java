package com.mac10_1.monsuivivehicule.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;
import java.util.List;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Facture implements Parcelable{

    private int idFact;
    private int idCar;
    private int numFacture;
    private String date;
    private int kilometrage;
    private double totalFacture;
    private List<Reparation> reparations;

    public Facture(Parcel in){
        readFromParcel(in);
    }

    public Facture(){
        totalFacture = 0;
    }
    public Facture(int idFact, int idCar, int numFacture, String date, int kilometrage, double totalFacture) {
        this.idFact = idFact;
        this.idCar = idCar;
        this.numFacture = numFacture;
        this.date = date;
        this.kilometrage = kilometrage;
        this.totalFacture = totalFacture;
    }

    public int getIdFact() {
        return idFact;
    }
    public int getIdCar() {
        return idCar;
    }
    public int getNumFacture() {
        return numFacture;
    }
    public String getDate() {
        return date;
    }
    public int getKilometrage() {
        return kilometrage;
    }
    public List<Reparation> getReparations() {
        return reparations;
    }
    public double getTotalFacture() {
        return totalFacture;
    }

    public void setIdFact(int idFact) {
        this.idFact = idFact;
    }
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }
    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }
    public void setTotalFacture(double totalFacture) {
        this.totalFacture = totalFacture;
    }
    public void setReparations(List<Reparation> reparations) {
        this.reparations = reparations;
    }

    public void addToTotalFacture(double toAdd){
        totalFacture += toAdd;
        Log.d("ADDTOTAL", "New Total" + totalFacture);
    }

    public void subFromTotalFacture(double toSub){
        totalFacture -= toSub;
        Log.d("SUBTOTAL", "New Total" + totalFacture);
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFact=" + idFact +
                ", idCar=" + idCar +
                ", numFacture=" + numFacture +
                ", date=" + date +
                ", kilometrage=" + kilometrage +
                ", totalFacture=" + totalFacture +
                ", reparations=" + reparations +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idFact);
        dest.writeInt(idCar);
        dest.writeInt(numFacture);
        dest.writeString(date);
        dest.writeInt(kilometrage);
        dest.writeDouble(totalFacture);
        dest.writeTypedList(reparations);

    }

    public void readFromParcel(Parcel in){
        idFact = in.readInt();
        idCar = in.readInt();
        numFacture = in.readInt();
        date = in.readString();
        kilometrage = in.readInt();
        totalFacture = in.readDouble();
        in.readTypedList(reparations, Reparation.CREATOR);

    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Facture createFromParcel(Parcel in) {
                    return new Facture(in);
                }

                public Facture[] newArray(int size) {
                    return new Facture[size];
                }
            };
}
