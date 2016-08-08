package com.mac10_1.monsuivivehicule.utils;

import java.util.Date;
import java.util.List;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Facture {

    private int idFact;
    private int idCar;
    private int numFacture;
    private String date;
    private int kilometrage;
    private double totalFacture;
    private List<Reparation> reparations;

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

    void countTotalFacture(){
        for(Reparation r : reparations){
            totalFacture += r.getCout();
        }
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
}
