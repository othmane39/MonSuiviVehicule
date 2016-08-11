package com.mac10_1.monsuivivehicule.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mac10-1 on 11/08/2016.
 */
public class JSONUtils {

    public static String loadJSONConstructeursFromAsset(Context a) {
        String json = null;
        try {
            InputStream is = a.getAssets().open("Marques.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String[] getArrayConstructeurs(Context a){
        String[] formList = new String[0];
        try {
            JSONObject obj = new JSONObject(loadJSONConstructeursFromAsset(a));
            JSONArray m_jArry = obj.getJSONArray("Marques");
            formList = new String [m_jArry.length()];
            String nom_constructeur;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
               // Log.d("Details-->", jo_inside.getString("Label"));
                nom_constructeur = jo_inside.getString("Label");

/*
                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("formule", formula_value);
                m_li.put("url", url_value);
*/
                formList[i]= nom_constructeur;


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return formList;
    }
}
