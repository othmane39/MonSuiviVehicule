package com.mac10_1.monsuivivehicule.Fragment;

import android.app.Fragment;
import android.app.SearchableInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

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
public class AddCarFragment extends Fragment implements View.OnClickListener{

    private EditText modele_edit, immatriculation_edit, vin_edit, millesime_edit;
    private SearchableSpinner constructeurSpinner;
    private RelativeLayout rl_to_goback;
    private ReparationEdit modeleWatcher;
    private ReparationEdit millesimeWatcher;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_add_newcar, container, false);


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
            db.addCar(immatriculation_edit.getText().toString(), constructeurSpinner.getSelectedItemPosition(), modele_edit.getText().toString()
            , Integer.parseInt(millesime_edit.getText().toString()), vin_edit.getText().toString());
            Log.d("SAVE CAR", immatriculation_edit.getText().toString() +" - "+ constructeurSpinner.getSelectedItemPosition() +" - "+ modele_edit.getText().toString()
                    +" - "+ Integer.parseInt(millesime_edit.getText().toString()) +" - "+ vin_edit.getText().toString());
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        //for the goback Relaative Layout
        getFragmentManager().popBackStack();
    }



    public boolean isSpinnerVisible(){
        return constructeurSpinner.isAccessibilityFocused();
    }

}
