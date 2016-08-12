package com.mac10_1.monsuivivehicule.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.Adapter.ReparationCarAdapter;
import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.AddFactureAnimHandler;
import com.mac10_1.monsuivivehicule.utils.Facture;
import com.mac10_1.monsuivivehicule.utils.Reparation;
import com.mac10_1.monsuivivehicule.utils.ReparationEdit;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import java.util.ArrayList;

/**
 * Created by mac10-1 on 10/08/2016.
 */
public class ShowFactureFragment extends Fragment {

    private Facture facture;
    private TextView no_facture, date, kilometrage;
    private ListView reparation_list;
    private ReparationCarAdapter reparationCarAdapter;
    private TextView total;
    private SQLiteHandler db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_show_facture, container, false);

        Bundle b = getActivity().getIntent().getExtras();
        facture = b.getParcelable("Facture");
        Log.d("SHOWFACTURE", facture.toString());

        db = new SQLiteHandler(getContext());

        no_facture = (TextView) rootView.findViewById(R.id.no_facture);
        date = (TextView) rootView.findViewById(R.id.date_facture);
        kilometrage = (TextView) rootView.findViewById(R.id.kilometrage_facture);
        reparation_list = (ListView) rootView.findViewById(R.id.facture_reparations_list_view);
        total = (TextView) rootView.findViewById(R.id.total_facture);




        no_facture.setText(String.valueOf(facture.getNumFacture()));
        date.setText(facture.getDate());
        kilometrage.setText(String.valueOf(facture.getKilometrage()));
        total.setText(String.valueOf(facture.getTotalFacture()));

        reparationCarAdapter = new ReparationCarAdapter(getContext(), facture.getReparations());
        reparation_list.setAdapter(reparationCarAdapter);

        registerForContextMenu(reparation_list);


        return rootView;
    }

    public boolean removeReparation(int id){

        if(id<=facture.getReparations().size()){
            Reparation r = facture.getReparations().get(id);
            db.removeReparationById(r.getIdRep());
            return true;
        }
        return false;

    }

}
