package com.mac10_1.monsuivivehicule;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.mac10_1.monsuivivehicule.utils.Car;
import com.mac10_1.monsuivivehicule.utils.CarAdapter;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCarsActivity extends AppCompatActivity {


    private ListView listViewCar;
    private ArrayList<HashMap<String, ?>> listCar;
    private SQLiteHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listViewCar = (ListView) findViewById(R.id.list_view_cars);

        db = new SQLiteHandler(getApplicationContext());

        db.addCar("EB-643-YV","VW", "GOLF IV", 2004, "HJGSDHJGFHJSDGJFHGSJHFG");
        db.addCar("32-3-4","Audi", "A3", 2004, "331232414324HFG");
        db.addMemo(1, "Vidange", 275349, "km");
        db.addMemo(1, "Filtre a air", 290349, "km");
        db.addMemo(1, "Filtre a carburant", 290349, "km");

        List<Car> cars = db.getCarsList();

        CarAdapter adapter = new CarAdapter(MyCarsActivity.this, cars);
        listViewCar.setAdapter(adapter);
        listViewCar.setOnItemClickListener(adapter);

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
