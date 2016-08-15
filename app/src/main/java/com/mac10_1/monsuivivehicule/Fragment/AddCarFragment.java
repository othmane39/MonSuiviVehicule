package com.mac10_1.monsuivivehicule.Fragment;

import android.app.Fragment;
import android.app.SearchableInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.app.AppController;
import com.mac10_1.monsuivivehicule.utils.JSONUtils;
import com.mac10_1.monsuivivehicule.utils.Reparation;
import com.mac10_1.monsuivivehicule.utils.ReparationEdit;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

/**
 * Created by mac10-1 on 11/08/2016.
 */
public class AddCarFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private EditText modele_edit, immatriculation_edit, vin_edit, millesime_edit;
    private SearchableSpinner constructeurSpinner;
    private RelativeLayout rl_to_goback;
    private ReparationEdit modeleWatcher;
    private ReparationEdit millesimeWatcher;
    private Spinner spinner_annee, spinner_zone;
    String immatriculation_zone, immatriculation_annee;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_add_newcar, container, false);





        spinner_annee = (Spinner) rootView.findViewById(R.id.spinner_annee);

        spinner_zone = (Spinner) rootView.findViewById(R.id.spinner_zone);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_zone = ArrayAdapter.createFromResource(getContext(),
                R.array.immatri_zone, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_annee = ArrayAdapter.createFromResource(getContext(),
                R.array.immatri_annee, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_annee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_zone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_annee.setAdapter(adapter_annee);
        spinner_zone.setAdapter(adapter_zone);

        spinner_annee.setOnItemSelectedListener(this);

        spinner_zone.setOnItemSelectedListener(this);



        constructeurSpinner = (SearchableSpinner) rootView.findViewById(R.id.constructeur_spinner);
        modele_edit = (EditText) rootView.findViewById(R.id.modele_edit);
        immatriculation_edit = (EditText) rootView.findViewById(R.id.immatriculation_edit);
        vin_edit = (EditText) rootView.findViewById(R.id.vin_edit);
        millesime_edit = (EditText) rootView.findViewById(R.id.millesime_edit);
        rl_to_goback = (RelativeLayout) rootView.findViewById(R.id.rl_to_goback);

        rl_to_goback.setOnClickListener(this);

        modeleWatcher = new ReparationEdit(modele_edit);
        modele_edit.addTextChangedListener(modeleWatcher);
        millesimeWatcher = new ReparationEdit(millesime_edit);
        millesime_edit.addTextChangedListener(millesimeWatcher);


        constructeurSpinner.setTitle("Marque du Constructeur");
        constructeurSpinner.setPositiveButton("OK");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, AppController.getConstrucBrands(getContext()));
        constructeurSpinner.setAdapter(spinnerArrayAdapter);


        return rootView;
    }

    public boolean saveCar() {
        if (modeleWatcher.isValidate_view() && millesimeWatcher.isValidate_view()) {
            SQLiteHandler db = new SQLiteHandler(getContext());
            db.addCar(setupImmatri(), constructeurSpinner.getSelectedItemPosition(), modele_edit.getText().toString()
            , Integer.parseInt(millesime_edit.getText().toString()), vin_edit.getText().toString().toUpperCase());
            Log.d("SAVE CAR", immatriculation_edit.getText().toString() +" - "+ constructeurSpinner.getSelectedItemPosition() +" - "+ modele_edit.getText().toString()
                    +" - "+ Integer.parseInt(millesime_edit.getText().toString()) +" - "+ vin_edit.getText().toString());
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        //for the goback Relaative Layout
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Mes Véhicules");
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(android.R.drawable.ic_input_add);
        getFragmentManager().popBackStack();
    }



    public boolean isSpinnerVisible(){
        return constructeurSpinner.isAccessibilityFocused();
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String[] s = null;

        String object = (String) parent.getItemAtPosition(pos);

        if (parent == spinner_zone) {
            immatriculation_zone = object.toString();

        } else if(parent == spinner_annee){
            immatriculation_annee = object.toString();
        }
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        if (parent == spinner_zone) {
            immatriculation_zone = "";

        } else if(parent == spinner_annee){
            immatriculation_annee = "";
        }

    }

    public String setupImmatri(){
        Log.d("TEST", new String(immatriculation_zone+"-"+immatriculation_annee+"-"+immatriculation_edit.getText().toString()));
        return new String(immatriculation_zone+" - "+immatriculation_annee+" - "+immatriculation_edit.getText().toString());
    }


}
