package com.mac10_1.monsuivivehicule.utils;

import java.util.Date;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class Facture {

    private Date date;
    private int numFacture;
    private Reparation[] reparations;
    private int total_facture;

    public Facture(Date date, int numFacture) {
        this.date = date;
        this.numFacture = numFacture;
        total_facture = 0;
    }

    public Facture(Date date, int numFacture, Reparation[] reparations) {
        this.date = date;
        this.numFacture = numFacture;
        this.reparations = reparations;
    }

    void getTotalFacture(){
        for(Reparation r : reparations){
            total_facture += r.getCout();
        }
    }
}
