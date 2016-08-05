package com.mac10_1.monsuivivehicule.utils;

/**
 * Created by mac10-1 on 03/08/2016.
 */
public class MemoCar {

    private int idMemo;
    private int idCar;
    private String memo;
    private int val;
    private String unit;

    public MemoCar(){

    }

    public MemoCar(int idMemo, int idCar, String memo, int val, String unit) {
        this.idMemo = idMemo;
        this.idCar = idCar;
        this.memo = memo;
        this.val = val;
        this.unit = unit;
    }

    public int getIdMemo() {
        return idMemo;
    }
    public int getIdCar() {
        return idCar;
    }
    public String getMemo() {
        return memo;
    }
    public int getVal() {
        return val;
    }
    public String getUnit() {
        return unit;
    }

    public void setIdMemo(int idMemo) {
        this.idMemo = idMemo;
    }
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public void setVal(int val) {
        this.val = val;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "MemoCar{" +
                "idMemo=" + idMemo +
                ", idCar=" + idCar +
                ", memo='" + memo + '\'' +
                ", val=" + val +
                ", unit='" + unit + '\'' +
                '}';
    }
}
