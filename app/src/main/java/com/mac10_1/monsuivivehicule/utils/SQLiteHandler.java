package com.mac10_1.monsuivivehicule.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by mac10-1 on 02/08/2016.
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 23;

    private static final String DATABASE_NAME = "suiviVehicules";

    private static final String TABLE_CONSTRUCTEUR = "constructeur";

    private static final String TABLE_CAR = "car";

    private static final String TABLE_FACTURE = "facture";

    private static final String TABLE_REPARATION = "reparartion";


    private static final String TABLE_MEMO_CAR = "memo_car";


    //private static final String KEY_ID_CONSTRUCTEUR = "id_constr";
    private static final String KEY_NOM_CONSTRUCTEUR = "nom_constr";

    //memo_car table collumn name
    private static final String KEY_ID_MEMO = "id_memo";
    //private static final String KEY_ID_CAR = "id_car";
    private static final String KEY_MEMO_NAME = "memo_name";
    private static final String KEY_MEMO_VAL = "memo_val";
    private static final String KEY_MEMO_UNIT = "memo_unit";


    //car table collumn name
    private static final String KEY_ID_CAR = "id_car";
    private static final String KEY_IMMATRICULATION = "immatriculation";
    private static final String KEY_ID_CONSTRUCTEUR = "id_constr";
    private static final String KEY_MODELE = "modele";
    private static final String KEY_MILLESIME = "millesime";
    private static final String KEY_CHASSIS = "n_chassis";


    //facture table collumn name
    private static final String KEY_ID_FACTURE = "id_facture";
    //private static final String KEY_ID_CAR = "id_car";
    private static final String KEY_NUM_FACTURE = "num_facture";
    private static final String KEY_DATE = "date";
    private static final String KEY_TOTAL = "total";
    private static final String KEY_KILOMETRAGE = "kilometrage";

    //reparatyion table collumn name
    private static final String KEY_ID_REP = "id_rep";
    //private static final String KEY_ID_FACTURE = "id_facture";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_COUT = "cout";


    private Context context;
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONSTRUCTEUR_TABLE = "CREATE TABLE " + TABLE_CONSTRUCTEUR + "(" + KEY_ID_CONSTRUCTEUR + " INTEGER PRIMARY KEY," + KEY_NOM_CONSTRUCTEUR
                + " VARCHAR(255) NOT NULL)";
        db.execSQL(CREATE_CONSTRUCTEUR_TABLE);

        String CREATE_CAR_TABLE = "CREATE TABLE " + TABLE_CAR + "("
                + KEY_ID_CAR + " INTEGER PRIMARY KEY, " + KEY_IMMATRICULATION + " VARCHAR(255), "
                + KEY_ID_CONSTRUCTEUR + " INTEGER NOT NULL, " + KEY_MODELE + " VARCHAR(255) NOT NULL, "
                + KEY_MILLESIME + " INTEGER, " + KEY_CHASSIS + " VARCHAR(255), FOREIGN KEY (" + KEY_ID_CONSTRUCTEUR
                +") REFERENCES " + TABLE_CONSTRUCTEUR + "("+ KEY_ID_CONSTRUCTEUR+"))";// + " DEFAULT CHARSET=utf8";
        db.execSQL(CREATE_CAR_TABLE);

        String CREATE_FACTURE_TABLE = "CREATE TABLE " + TABLE_FACTURE + "(" + KEY_ID_FACTURE + " INTEGER PRIMARY KEY, "
                + KEY_ID_CAR + " INTEGER NOT NULL," + KEY_NUM_FACTURE + " INTEGER," + KEY_DATE + " date,"
                + KEY_KILOMETRAGE + " INTEGER," + KEY_TOTAL + " REAL, "
                + " FOREIGN KEY ("+ KEY_ID_CAR + ") REFERENCES " + TABLE_CAR + "("+ KEY_ID_CAR +"))";// + " DEFAULT CHARSET=utf8";
        db.execSQL(CREATE_FACTURE_TABLE);

        String CREATE_REPARATION_TABLE = "CREATE TABLE " + TABLE_REPARATION + "("
                + KEY_ID_REP + " INTEGER PRIMARY KEY," + KEY_ID_FACTURE + " INTEGER NOT NULL," + KEY_DESCRIPTION + " VARCHAR(255) NOT NULL,"
                + KEY_COUT + " REAL, "+ " FOREIGN KEY ("+ KEY_ID_FACTURE + ") REFERENCES " + TABLE_FACTURE + "("+ KEY_ID_FACTURE + "))";// + " DEFAULT CHARSET=utf8";
        db.execSQL(CREATE_REPARATION_TABLE);

        String CREATE_MEMO_TABLE = "CREATE TABLE " + TABLE_MEMO_CAR + "(" + KEY_ID_MEMO + " INTEGER PRIMARY KEY," + KEY_ID_CAR
                + " INTEGER NOT NULL," + KEY_MEMO_NAME + " VARCHAR(255) NOT NULL," + KEY_MEMO_VAL + " INTEGER NOT NULL," + KEY_MEMO_UNIT
                + " VARCHAR(10)," + " FOREIGN KEY (" + KEY_ID_CAR + ") REFERENCES " + TABLE_CAR + "(" + KEY_ID_CAR + "))";
        db.execSQL(CREATE_MEMO_TABLE);



        Log.d(TAG, "Database tables created");


    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSTRUCTEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACTURE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPARATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMO_CAR);


        // Create tables again
        onCreate(db);
    }

    /**
     * Storing new car details in database
     */
    public void addCar(String immatriculation, int id_constructeur, String modele, int millesime, String nchasssis) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMMATRICULATION, immatriculation);
        values.put(KEY_ID_CONSTRUCTEUR, id_constructeur);
        values.put(KEY_MODELE, modele);
        values.put(KEY_MILLESIME, millesime);
        values.put(KEY_CHASSIS, nchasssis);

        // Inserting Row
        long id = db.insert(TABLE_CAR, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New car inserted into sqlite: " + id);
    }

    /**
     * Storing new facture details in database
     */
    public int addFacture(int id_car , int num_facture, String date , int kilometrage, double total) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NUM_FACTURE, num_facture);
        values.put(KEY_ID_CAR, id_car);
        values.put(KEY_DATE, date);  //TODO a verifier!!
        values.put(KEY_KILOMETRAGE, kilometrage);
        values.put(KEY_TOTAL, total);


        long id = db.insert(TABLE_FACTURE, null, values);
        Log.d(TAG, "New facture inserted into sqlite: " + id);


        db.close(); // Closing database connection
        return (int) id; //TODO a corriger les classes utils id to long!

    }

    public void setTotalFacture(int id_facture, int total){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE " + TABLE_FACTURE + " SET" + KEY_TOTAL +"='"+ total +"' WHERE" +  KEY_ID_FACTURE+"="+id_facture+"");
        Log.d(TAG, "Total Facture Modified ");
    }

    public void addReparation(int id_facture, String description, double cout) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_FACTURE, id_facture);
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_COUT, cout);

        long id = db.insert(TABLE_REPARATION, null, values);
        Log.d(TAG, "New rearation inserted into sqlite: " + id);




        db.close(); // Closing database connection


    }

    public void addMemo(int id_car, String memo, int val, String unit){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_CAR, id_car);
        values.put(KEY_MEMO_NAME, memo);
        values.put(KEY_MEMO_VAL, val);
        values.put(KEY_MEMO_UNIT, unit);

        long id = db.insert(TABLE_MEMO_CAR, null, values);
        Log.d(TAG, "New memo inserted into sqlite: " + id);




        db.close(); // Closing database connection

    }

    public void addAllConstructeursBrands(String[] constructeurs){
        ContentValues values;
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i=0; i<constructeurs.length; i++){
            values = new ContentValues();
            values.put(KEY_NOM_CONSTRUCTEUR, constructeurs[i]);

            long id = db.insert(TABLE_CONSTRUCTEUR, null, values);
            Log.d(TAG, "New constructeur inserted into sqlite: " + id + "   " + constructeurs[i]);

        }


    }


    public String getConstructeurNameFromID(int id_constr){
        String selectQuery = "SELECT * FROM " + TABLE_CONSTRUCTEUR + " WHERE " + KEY_ID_CONSTRUCTEUR + "=?";
        String[] params = new String[]{ String.valueOf(id_constr+1) };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, params);
        cursor.moveToFirst();
        Log.d("TAG", "GOT COUNT"+ cursor.getCount());
        if(cursor.getCount()>0) {
            Log.d("ConstructorNameFromID", cursor.getInt(0) + cursor.getString(1));
            return cursor.getString(1);
        }
        return null;
        //}else {
            //new NullPointerException();
            //Log.d("ConstructorNameFromID", "Eroor");
          //  return null;
        //}


    }
    public Car getCarFromId(int id_car){
        Car car = new Car();

        //String selectQuery = "SELECT * FROM " + TABLE_CAR + " WHERE " + KEY_ID_CAR + "=?";
        String selectQuery = "SELECT * FROM " + TABLE_CAR + " a INNER JOIN "+ TABLE_CONSTRUCTEUR + " b ON a."+KEY_ID_CONSTRUCTEUR+"=b."+KEY_ID_CONSTRUCTEUR
                +" WHERE a."+KEY_ID_CAR+ "=?";

        String[] params = new String[]{ String.valueOf(id_car) };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, params);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            car.setId_car((int) cursor.getInt(0));
            car.setImmatriculation((String) cursor.getString(1));
            car.setMarque((String) cursor.getString(6)); //TODO a tester!!
            car.setModele((String) cursor.getString(3));
            car.setMillesime((int) cursor.getInt(4));
            car.setNchassis((String) cursor.getString(5));

            car.setMemoCar(getMemoCarList(id_car));
            car.setFactures(getFacturesList(id_car));



        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching car from sqlite: " + car.toString());

        return car;

    }

    public Facture getFactureFromId(int id_facture){
        Facture facture = new Facture();

        String[] params = new String[]{ String.valueOf(id_facture) };
        String selectQuery = "SELECT * FROM " + TABLE_FACTURE + " WHERE " + KEY_ID_FACTURE + "= ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, params);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                facture.setIdFact((int) cursor.getInt(0));
                facture.setIdCar((int) cursor.getInt(1));
                facture.setNumFacture((int) cursor.getInt(2));
                facture.setDate((String) cursor.getString(3));
                facture.setKilometrage((int) cursor.getInt(4));
                facture.setTotalFacture((double) cursor.getDouble(5));
                facture.setReparations(getReparationsList(id_facture));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching facture from sqlite: " + facture.toString());

        return facture;

    }

    public List<Car> getCarsList(){
        List<Car> carList = new ArrayList<Car>();

        String selectQuery = "SELECT * FROM " + TABLE_CAR ;//+ " a  JOIN "+ TABLE_CONSTRUCTEUR + " b ON a."+KEY_ID_CONSTRUCTEUR+"=b."+KEY_ID_CONSTRUCTEUR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Car car = new Car();
                car.setId_car((int) cursor.getInt(0));
                car.setImmatriculation((String) cursor.getString(1));
                car.setMarque((String) getConstructeurNameFromID(cursor.getInt(2)));
                car.setModele((String) cursor.getString(3));
                car.setMillesime((int) cursor.getInt(4));
                car.setNchassis((String) cursor.getString(5));
                Log.d(TAG, "Fetching all cars from sqlite: " + car.toString());

                carList.add(car);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching all cars from sqlite: " + carList.toString());

        return carList;


    }


    public List<Facture> getFacturesList(int id_car){
        List<Facture> factureList = new ArrayList<Facture>();

        String[] params = new String[]{ String.valueOf(id_car) };
        String selectQuery = "SELECT * FROM " + TABLE_FACTURE + " WHERE " + KEY_ID_CAR + "= ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, params);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Facture facture = new Facture();
                facture.setIdFact((int) cursor.getInt(0));
                facture.setIdCar((int) cursor.getInt(1));
                facture.setNumFacture((int) cursor.getInt(2));
                facture.setDate((String) cursor.getString(3));
                facture.setKilometrage((int) cursor.getInt(4));
                facture.setTotalFacture((double) cursor.getDouble(5));


                factureList.add(facture);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching all factures from sqlite: " + factureList.toString());

        return factureList;

    }


    public List<Reparation> getReparationsList(int id_facture){
        List<Reparation> reparationList = new ArrayList<Reparation>();

        String[] params = new String[]{ String.valueOf(id_facture) };
        String selectQuery = "SELECT * FROM " + TABLE_REPARATION + " WHERE " + KEY_ID_FACTURE + "= ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, params);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Reparation reparation = new Reparation();
                reparation.setIdRep((int) cursor.getInt(0));
                reparation.setIdFacture((int) cursor.getInt(1));
                reparation.setNom((String) cursor.getString(2));
                reparation.setCout((int) cursor.getInt(3));


                reparationList.add(reparation);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching all reparations from sqlite: " + reparationList.toString());

        return reparationList;

    }

    public List<MemoCar> getMemoCarList(int id_car){
        List<MemoCar> memoCarList = new ArrayList<MemoCar>();

        String[] params = new String[]{ String.valueOf(id_car) };
        String selectQuery = "SELECT * FROM " + TABLE_MEMO_CAR + " WHERE " + KEY_ID_CAR + "= ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, params);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                MemoCar memoCar = new MemoCar();
                memoCar.setIdMemo((int) cursor.getInt(0));
                memoCar.setIdCar((int) cursor.getInt(1));
                memoCar.setMemo((String) cursor.getString(2));
                memoCar.setVal((int) cursor.getInt(3));
                memoCar.setUnit((String) cursor.getString(4));


                memoCarList.add(memoCar);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching all memo car from sqlite: " + memoCarList.toString());

        return memoCarList;

    }

    public boolean removeMemoById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int r = db.delete(TABLE_MEMO_CAR, KEY_ID_MEMO + "=" + id, null);
        db.close();
        return r>0;

    }

    public boolean removeFactureById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int r = db.delete(TABLE_FACTURE, KEY_ID_FACTURE + "=" + id, null);
        db.close();
        return r>0;
    }

    public boolean removeCarById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int r = db.delete(TABLE_CAR, KEY_ID_CAR + "=" + id, null);
        db.close();
        return r>0;
    }


}
