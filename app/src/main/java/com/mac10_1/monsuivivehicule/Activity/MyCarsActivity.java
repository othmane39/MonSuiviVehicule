package com.mac10_1.monsuivivehicule.Activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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

public class MyCarsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //listViewCar = (ListView) findViewById(R.id.list_view_cars);
/*
        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
        db.addCar("EB-643-YV","VW", "GOLF IV", 2004, "HJGSDHJGFHJSDGJFHGSJHFG");
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


        MyCarsFragment myCarsFragment = new MyCarsFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment_mycar_layout, myCarsFragment); // f1_container is your FrameLayout container
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



        FloatingActionButton newCarButton = (FloatingActionButton) findViewById(R.id.fab);
        newCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                //TODO jump to AddNewCarActivity;
            }
        });
    }















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_cars, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
