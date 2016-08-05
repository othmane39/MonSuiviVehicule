package com.mac10_1.monsuivivehicule.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Choreographer;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.Fragment.AddMemoFragment;
import com.mac10_1.monsuivivehicule.Fragment.InfoCarActivityFragment;
import com.mac10_1.monsuivivehicule.R;

public class InfoCarActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    Toolbar toolbar;
    InfoCarActivityFragment infoCarActivityFragment;
    AddMemoFragment addMemoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_car);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        infoCarActivityFragment = new InfoCarActivityFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment_layout, infoCarActivityFragment); // f1_container is your FrameLayout container
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //getSupportFragmentManager().fi




        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }


    public void onClick(View view) {

        if(addMemoFragment != null && addMemoFragment.isVisible()){
            addMemoFragment.addMemo();
            toolbar.setTitle("Info Vehicule");
            Snackbar.make(view, "Mémo enregistré", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            fab.setImageResource(android.R.drawable.ic_input_add);
            getFragmentManager().popBackStack();


        }
        else if(infoCarActivityFragment!= null && infoCarActivityFragment.isVisible()) {
            addMemoFragment = new AddMemoFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_layout, addMemoFragment); // f1_container is your FrameLayout container
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("Ajouter Mémo");
            fab.setImageResource(R.drawable.ic_done);
        }


    }

    






}
