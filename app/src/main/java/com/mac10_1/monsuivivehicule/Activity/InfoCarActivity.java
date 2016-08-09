package com.mac10_1.monsuivivehicule.Activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mac10_1.monsuivivehicule.Fragment.AddMemoFragment;
import com.mac10_1.monsuivivehicule.Fragment.InfoCarActivityFragment;
import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.InfoCarFABHandler;

public class InfoCarActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    Toolbar toolbar;
    InfoCarActivityFragment infoCarActivityFragment;
    AddMemoFragment addMemoFragment;

    InfoCarFABHandler fabHandler;


    FrameLayout fab_facture, fab_memo;
    RelativeLayout fab1_2_container;

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
        fab_memo = (FrameLayout) findViewById(R.id.fab_ajouter_memo);
        fab_facture = (FrameLayout) findViewById(R.id.fab_ajouter_facture);
        fab1_2_container = (RelativeLayout) findViewById(R.id.fab_1_2_layout_container);

        //fab3 = (FrameLayout) findViewById(R.id.fab3_layout);


        fabHandler = new InfoCarFABHandler(getApplication(), fab_memo, fab_facture, fab1_2_container);



        fab.setOnClickListener(this);
        fab_memo.setOnClickListener(this);
        fab_facture.setOnClickListener(this);
    }


    public void onClick(View view) {
        if(view == fab ) {
            if (infoCarActivityFragment != null && infoCarActivityFragment.isVisible())
                fabHandler.evaluateClick();
            else if (addMemoFragment != null && addMemoFragment.isVisible()) {
                addMemoFragment.addMemo();
                toolbar.setTitle("Info Vehicule");
                Snackbar.make(view, "Mémo enregistré", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fab.setImageResource(android.R.drawable.ic_input_add);
                getFragmentManager().popBackStack();
            }
        }else if(view == fab_memo){
            fabHandler.evaluateClick();
            addMemoFragment = new AddMemoFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_layout, addMemoFragment); // f1_container is your FrameLayout container
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("Ajouter Mémo");
            fab.setImageResource(R.drawable.ic_done);
        }else if(view == fab_facture){
            //TODO
        }



/*
        if(addMemoFragment != null && addMemoFragment.isVisible()){ //DANS LA PAGE ADDMEMO
            fabHandler.evaluateClick();
            addMemoFragment.addMemo();
            toolbar.setTitle("Info Vehicule");
            Snackbar.make(view, "Mémo enregistré", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            fab.setImageResource(android.R.drawable.ic_input_add);
            getFragmentManager().popBackStack();


        }
        else if(infoCarActivityFragment!= null && infoCarActivityFragment.isVisible()) {
            fabHandler.evaluateClick();
            addMemoFragment = new AddMemoFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_layout, addMemoFragment); // f1_container is your FrameLayout container
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("Ajouter Mémo");
            fab.setImageResource(R.drawable.ic_done);
        }
*/



    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(addMemoFragment != null && addMemoFragment.isVisible()){
            //addMemoFragment.addMemo();
            toolbar.setTitle("Info Vehicule");
            //Snackbar.make(view, "Mémo enregistré", Snackbar.LENGTH_LONG)
            //.setAction("Action", null).show();
            fab.setImageResource(android.R.drawable.ic_input_add);
            getFragmentManager().popBackStack();


        }
        else super.onBackPressed();
    }
}
