package com.mac10_1.monsuivivehicule.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mac10_1.monsuivivehicule.utils.JSONUtils;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import java.util.ArrayList;

/**
 * Created by mac10-1 on 11/08/2016.
 */
public class AppController extends Application {

    static String[] construcBrands;
    @Override
    public void onCreate() {
        super.onCreate();

        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
        boolean mboolean = false;

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) {
            // do the thing for the first time
            String[] constructeurs = JSONUtils.getArrayConstructeurs(getApplicationContext());
            db.addAllConstructeursBrands(constructeurs);
            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();
        } else {
            String[] constructeurs = JSONUtils.getArrayConstructeurs(getApplicationContext());
            db.addAllConstructeursBrands(constructeurs);
            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();

        }

    }

    public static String[] getConstrucBrands(Context c) {
        if(construcBrands == null)
         construcBrands = JSONUtils.getArrayConstructeurs(c);
        return construcBrands;
    }
}
