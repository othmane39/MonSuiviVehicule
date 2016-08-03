package com.mac10_1.monsuivivehicule.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "suiviVehicules";

    private static final String TABLE_CAR = "car";

    private static final String TABLE_FACTURE = "facture";

    private static final String TABLE_REPARATION = "reparartion";

    private static final String TABLE_CAR_FACT = "car_fact";

    private static final String TABLE_FACT_REP = "fact_rep";


    //car table collumn name
    private static final String KEY_ID_CAR = "id_car";
    private static final String KEY_IMMATRICULATION = "immatriculation";
    private static final String KEY_MARQUE = "marque";
    private static final String KEY_MODELE = "modele";
    private static final String KEY_MILLESIME = "millesime";
    private static final String KEY_CHASSIS = "n_chassis";

    //facture table collumn name
    private static final String KEY_NUM_FACTURE = "id_facture";
    private static final String KEY_DATE = "date";
    private static final String KEY_TOTAL = "total";

    //reparatyion table collumn name
    private static final String KEY_ID_REP = "id_rep";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_COUT = "cout";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CAR_TABLE = "CREATE TABLE " + TABLE_CAR + "("
                + KEY_ID_CAR + " INTEGER PRIMARY KEY," + KEY_IMMATRICULATION + " VARCHAR(255) NOT NULL,"
                + KEY_MARQUE + " VARCHAR(255)," + KEY_MODELE + " VARCHAR(255),"
                + KEY_MILLESIME + " INTEGER, " + KEY_CHASSIS + " VARCHAR(255)" +")";// + " DEFAULT CHARSET=utf8";
        db.execSQL(CREATE_CAR_TABLE);

        String CREATE_FACTURE_TABLE = "CREATE TABLE " + TABLE_FACTURE + "("
                + KEY_NUM_FACTURE + " INTEGER PRIMARY KEY," + KEY_DATE + " DATE,"
                + KEY_TOTAL + " REAL " + ")";// + " DEFAULT CHARSET=utf8";
        db.execSQL(CREATE_FACTURE_TABLE);

        String CREATE_REPARATION_TABLE = "CREATE TABLE " + TABLE_REPARATION + "("
                + KEY_ID_REP + " INTEGER PRIMARY KEY," + KEY_DESCRIPTION + " VARCHAR(255) NOT NULL,"
                + KEY_COUT + " REAL " + ")";// + " DEFAULT CHARSET=utf8";
        db.execSQL(CREATE_REPARATION_TABLE);

        String CREATE_CAR_FACT_TABLE = "CREATE TABLE " + TABLE_CAR_FACT + "("
                + KEY_ID_CAR + " INTEGER ," + KEY_NUM_FACTURE + " INTEGER, " + " PRIMARY KEY ( "+KEY_ID_CAR +", "+ KEY_NUM_FACTURE +"))";
        db.execSQL(CREATE_CAR_FACT_TABLE);

        String CREATE_FACT_REP_TABLE = "CREATE TABLE " + TABLE_FACT_REP + "("
                + KEY_NUM_FACTURE + " INTEGER, " + KEY_ID_REP + " INTEGER, " + "PRIMARY KEY ( "+KEY_NUM_FACTURE+", "+ KEY_ID_REP + "))";
        db.execSQL(CREATE_FACT_REP_TABLE);

        Log.d(TAG, "Database tables created");


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACTURE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPARATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR_FACT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACT_REP);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing new car details in database
     */
    public void addCar(String immatriculation, String marque, String modele, int millesime, String nchasssis) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMMATRICULATION, immatriculation);
        values.put(KEY_MARQUE, marque);
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
    public void addFacture(int id_car , int num_facture, Date date , double total) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NUM_FACTURE, num_facture);
        values.put(KEY_DATE, date.getTime());
        values.put(KEY_TOTAL, total);


        long id = db.insert(TABLE_FACTURE, null, values);
        Log.d(TAG, "New facture inserted into sqlite: " + id);

        values = new ContentValues();
        values.put(KEY_ID_CAR, id_car);
        values.put(KEY_NUM_FACTURE, num_facture);

        id = db.insert(TABLE_CAR_FACT, null, values);
        Log.d(TAG, "New asssoss car fact inserted into sqlite: " + id);


        db.close(); // Closing database connection




    }

    public void addReparation(int num_facture, String description, double cout) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_COUT, cout);

        long id = db.insert(TABLE_REPARATION, null, values);
        Log.d(TAG, "New facture inserted into sqlite: " + id);

        if(id == -1) Log.e(TAG, "addReparationError");


        values = new ContentValues();
        values.put(KEY_NUM_FACTURE, num_facture);
        values.put(KEY_ID_REP, id);

        id = db.insert(TABLE_FACT_REP, null, values);
        Log.d(TAG, "New assoc fact rep inserted into sqlite: " + id);


        db.close(); // Closing database connection


    }


    public List<Car> getCarsList(){
        List<Car> carList = new ArrayList<Car>();

        String selectQuery = "SELECT * FROM " + TABLE_CAR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Car car = new Car();
                car.setImmatriculation((String) cursor.getString(1));
                car.setMarque((String) cursor.getString(2));
                car.setModele((String) cursor.getString(3));
                car.setMillesime((int) cursor.getInt(4));
                car.setNchassis((String) cursor.getString(5));
                //car.put(KEY_ID_CAR, (int) cursor.getInt(0));

                carList.add(car);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching all cars from sqlite: " + carList.toString());

        return carList;


    }
    /*
    public ArrayList<HashMap<String, ?>> getCarsList() {

        ArrayList<HashMap<String, ?>> carList = new ArrayList<HashMap<String, ?>>();
        String selectQuery = "SELECT * FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                HashMap<String, Object> car = new HashMap<String, Object>();
                car.put(KEY_ID_CAR, (int) cursor.getInt(0));
                car.put(KEY_IMMATRICULATION, (String) cursor.getString(1));
                car.put(KEY_MARQUE, (String) cursor.getString(2));
                car.put(KEY_MODELE, (String) cursor.getString(3));
                car.put(KEY_MILLESIME, (int) cursor.getInt(4));
                car.put(KEY_CHASSIS, (String) cursor.getString(5));

                carList.add(car);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Log.d(TAG, "Fetching all cars from sqlite: " + carList.toString());

        return carList;

    }
    */

    public ArrayList<HashMap<String, ?>> getFactures(int id_car){
        ArrayList<HashMap<String, ?>> factureList = new ArrayList<HashMap<String, ?>>();
        SQLiteDatabase db = getReadableDatabase();
//TODO change SELECT* with just necesseary to parse from the rest
        String selectQuery = "SELECT * FROM " + TABLE_CAR_FACT +" a INNER JOIN "+ TABLE_FACTURE + " b ON a."+KEY_NUM_FACTURE+"=b."+KEY_NUM_FACTURE
                + " WHERE a."+KEY_ID_CAR+"=?";


        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id_car)});

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                HashMap<String, Object> facture = new HashMap<String, Object>();
                facture.put(KEY_NUM_FACTURE, (int) cursor.getInt(2));
                facture.put(KEY_DATE, (String) cursor.getString(3));
                facture.put(KEY_TOTAL, (double) cursor.getDouble(4));


                factureList.add(facture);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Log.d(TAG, "Fetching factures from sqlite: " + factureList.toString());

        return factureList;
    }

    public ArrayList<HashMap<String, ?>> getReparations(int num_facture){
        ArrayList<HashMap<String, ?>> reparationList = new ArrayList<HashMap<String, ?>>();
        SQLiteDatabase db = getReadableDatabase();
//TODO change SELECT* with just necesseary to parse from the rest
        String selectQuery = "SELECT * FROM " + TABLE_FACT_REP +" a INNER JOIN "+ TABLE_REPARATION + " b ON a."+KEY_ID_REP+"=b."+KEY_ID_REP
                + " WHERE a."+KEY_NUM_FACTURE+"=?";


        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(num_facture)});

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                HashMap<String, Object> reparation = new HashMap<String, Object>();
                reparation.put(KEY_DESCRIPTION, (String) cursor.getString(2));
                reparation.put(KEY_COUT, (double) cursor.getDouble(3));

                reparationList.add(reparation);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Log.d(TAG, "Fetching reparations from sqlite: " + reparationList.toString());

        return reparationList;
    }


}
