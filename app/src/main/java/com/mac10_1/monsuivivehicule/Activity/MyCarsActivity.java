package com.mac10_1.monsuivivehicule.Activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.mac10_1.monsuivivehicule.Fragment.AddCarFragment;
import com.mac10_1.monsuivivehicule.Fragment.InfoCarActivityFragment;
import com.mac10_1.monsuivivehicule.Fragment.MyCarsFragment;
import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.Car;
import com.mac10_1.monsuivivehicule.Adapter.CarAdapter;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyCarsActivity extends AppCompatActivity implements View.OnClickListener{

    MyCarsFragment myCarsFragment;
    AddCarFragment addCarFragment;

    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //listViewCar = (ListView) findViewById(R.id.list_view_cars);

        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
/*
        db.addCar("EB-643-YV",1078, "GOLF IV", 2003, "WVWZZZ1JZ3B088893");

        db.addCar("32-3-4","Audi", "A3", 2004, "331232414324HFG");
        db.addMemo(1, "Vidange", 275349, "km");
        db.addMemo(1, "Filtre a air", 290349, "km");
        db.addMemo(1, "Filtre a carburant", 290349, "km");
        db.addFacture(1, 2435627, "2016-08-14", 286457, 3456.8);
        db.addFacture(1, 242442, "2016-09-14", 266457, 2500);
        //db = new SQLiteHandler(getApplicationContext());

        db.addCar("EB-643-YV","VW", "GOLF IV", 2004, "HJGSDHJGFHJSDGJFHGSJHFG");
        db.addCar("32-3-4","Audi", "A3", 2004, "331232414324HFG");
        db.addMemo(1, "Vidange", 275349, "km");
        db.addMemo(1, "Filtre a air", 290349, "km");
        db.addMemo(1, "Filtre a carburant", 290349, "km");

        cars = db.getCarsList();

        CarAdapter adapter = new CarAdapter(MyCarsActivity.this, cars);
        listViewCar.setAdapter(adapter);
        listViewCar.setOnItemClickListener(adapter);


*/


        myCarsFragment = new MyCarsFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment_mycar_layout, myCarsFragment); // f1_container is your FrameLayout container
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();

        if(addCarFragment != null && addCarFragment.isVisible()){
            if(addCarFragment.saveCar()) {
                Snackbar.make(view, "Votre véhicule a bien été enregistré", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                getFragmentManager().popBackStack();
                fab.setImageResource(android.R.drawable.ic_input_add);

            }
            else Snackbar.make(view, "Erreur lors de l'enregistrement", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else if(myCarsFragment != null && myCarsFragment.isVisible()) {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            addCarFragment = new AddCarFragment();
            ft.replace(R.id.fragment_mycar_layout, addCarFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();
            toolbar.setTitle("Ajouter Véhicule");
            fab.setImageResource(R.drawable.ic_save_white);
        }
    }



    @Override
    public void onBackPressed() {

        if(addCarFragment != null && addCarFragment.isVisible()){


            fab.setImageResource(android.R.drawable.ic_input_add);
            toolbar.setTitle("Mes Véhicule");
            getFragmentManager().popBackStack();


        }
        else if(myCarsFragment != null && myCarsFragment.isVisible()){
            finish();
        }

    }
}
