package com.mac10_1.monsuivivehicule.Fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.Adapter.ReparationCarAdapter;
import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.AddFactureAnimHandler;
import com.mac10_1.monsuivivehicule.utils.Car;
import com.mac10_1.monsuivivehicule.utils.Facture;
import com.mac10_1.monsuivivehicule.utils.Reparation;
import com.mac10_1.monsuivivehicule.utils.ReparationEdit;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac10-1 on 09/08/2016.
 */
public class AddFactureFragment extends Fragment implements View.OnClickListener, View.OnKeyListener{
    private SQLiteHandler db;
    private Car car;
    private Facture facture;
    private EditText no_facture, date, kilometrage;
    private ListView reparation_list;

    private ReparationCarAdapter reparationCarAdapter;
    private EditText reparation_txt;
    private EditText reparation_cout;
    private FrameLayout add_reparation_button;
    private AddFactureAnimHandler animHandler;
    private RelativeLayout reparation_edit;
    private ReparationEdit no_factureWatcher, reparation_txtWatcher, reparation_coutWatcher, dateWatcher, kilometrageWatcher;
    private FrameLayout save_facture_button;
    private RelativeLayout to_goBack;

    private int idFacture = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_add_facture, container, false);

        Bundle b = getActivity().getIntent().getExtras();
        car = b.getParcelable("Car");
        facture = new Facture();

        no_facture = (EditText) rootView.findViewById(R.id.no_facture);
        date = (EditText) rootView.findViewById(R.id.date_facture);
        kilometrage = (EditText) rootView.findViewById(R.id.kilometrage_facture);
        reparation_list = (ListView) rootView.findViewById(R.id.reparations_list_view);
        add_reparation_button = (FrameLayout) rootView.findViewById(R.id.add_reparation_button);
        reparation_edit = (RelativeLayout) rootView.findViewById(R.id.reparation_edit);
        reparation_txt  = (EditText) rootView.findViewById(R.id.reparation_txt_edit);
        reparation_cout  = (EditText) rootView.findViewById(R.id.reparation_cout_edit);
        //save_facture_button = (FrameLayout) rootView.findViewById(R.id.save_facture_button);
        to_goBack = (RelativeLayout) rootView.findViewById(R.id.rl_go_back);

        no_factureWatcher = new ReparationEdit(no_facture);
        dateWatcher = new ReparationEdit(date);
        kilometrageWatcher = new ReparationEdit(kilometrage);
        reparation_txtWatcher = new ReparationEdit(reparation_txt);
        reparation_coutWatcher = new ReparationEdit(reparation_cout);

        no_facture.addTextChangedListener(no_factureWatcher);
        date.addTextChangedListener(dateWatcher);
        kilometrage.addTextChangedListener(kilometrageWatcher);
        reparation_txt.addTextChangedListener(reparation_txtWatcher);
        reparation_cout.addTextChangedListener(reparation_coutWatcher);

        reparation_cout.setOnKeyListener(this);

        to_goBack.setOnClickListener(this);



        db = new SQLiteHandler(getContext());

        facture.setReparations(new ArrayList<Reparation>());

        reparationCarAdapter = new ReparationCarAdapter(getContext(), facture.getReparations());
        reparation_list.setAdapter(reparationCarAdapter);

        animHandler = new AddFactureAnimHandler(add_reparation_button, reparation_edit);

        add_reparation_button.setOnClickListener(this);
        //save_facture_button.setOnClickListener(this);
        justifyListViewHeightBasedOnChildren(reparation_list);



        return rootView;
    }

    public void addReparation() {

        Reparation r = new Reparation(reparation_txt.getText().toString(), Double.parseDouble(reparation_cout.getText().toString()));

            //db.addReparation(idFacture, r.getNom(), r.getCout());
        facture.addToTotalFacture(r.getCout());
        facture.getReparations().add(r);
        reparationCarAdapter.notifyDataSetChanged();
        justifyListViewHeightBasedOnChildren(reparation_list);

    }

    public boolean saveFacture(){

        if(no_factureWatcher.isValidate_view() && dateWatcher.isValidate_view() && kilometrageWatcher.isValidate_view()){
            facture.setNumFacture(Integer.parseInt(no_facture.getText().toString()));
            facture.setDate(date.getText().toString());
            facture.setKilometrage(Integer.parseInt(kilometrage.getText().toString()));

            idFacture = db.addFacture(car.getId_car(), facture.getNumFacture(), facture.getDate(), facture.getKilometrage(), facture.getTotalFacture());
            facture.setIdFact(idFacture);
            saveReparations();
            Log.d("ADD FACTURE", "SUCCESSFULLY SAVED");
            return true;


        }

        return false;
    }

    public void saveReparations(){
        for( Reparation r : facture.getReparations())
            db.addReparation(facture.getIdFact(), r.getNom(), r.getCout());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_reparation_button:

                reparation_txt.requestFocus();
                if (animHandler.isFirstAdd() ) {
                    animHandler.evaluateClick();


                } else if (reparation_txtWatcher.isValidate_view() && reparation_coutWatcher.isValidate_view()) {
                    addReparation();
                    animHandler.evaluateClick();
                    reparation_txt.setText("");
                    reparation_cout.setText("");
                }
                break;
            case R.id.rl_go_back:
                Log.d("TEST", "TETS");
                getFragmentManager().popBackStack();
                FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                getActivity().setTitle("Info Vehicule");
                fab.setImageResource(android.R.drawable.ic_input_add);
                getFragmentManager().popBackStack();
                break;


        }
    }

    public void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            //listItem.m
            totalHeight += listItem.getMeasuredHeight();
            Log.d("Missa", listItem.getMeasuredHeight() + " H" + listItem.getHeight());

        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        par.height += 100;
        Log.d("Missa", "NotFrag " + par.height);
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) && reparation_txtWatcher.isValidate_view() && reparation_coutWatcher.isValidate_view()) {
            addReparation();
            //animHandler.evaluateClick();
            reparation_txt.setText("");
            reparation_cout.setText("");
            reparation_txt.requestFocus();



            return true;
        }
        return false;
    }
}
