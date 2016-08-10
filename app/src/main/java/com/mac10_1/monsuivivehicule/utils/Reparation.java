package com.mac10_1.monsuivivehicule.utils;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Reparation implements Parcelable{

    int idRep;
    int idFacture;
    String nom;
    double cout;


    public Reparation(Parcel in){
        readFromParcel(in);
    }

    public Reparation() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idRep);
        dest.writeInt(idFacture);

        dest.writeString(nom);

        dest.writeDouble(cout);


    }

    public void readFromParcel(Parcel in){
        idRep = in.readInt();
        idFacture = in.readInt();

        nom = in.readString();

        cout = in.readDouble();


    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Reparation createFromParcel(Parcel in) {
                    return new Reparation(in);
                }

                public Reparation[] newArray(int size) {
                    return new Reparation[size];
                }
            };


}

