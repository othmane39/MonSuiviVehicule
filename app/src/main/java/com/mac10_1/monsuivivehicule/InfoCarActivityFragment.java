package com.mac10_1.monsuivivehicule;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.utils.Car;
import com.mac10_1.monsuivivehicule.utils.MemoCar;
import com.mac10_1.monsuivivehicule.utils.MemoCarAdapter;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import java.util.HashMap;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfoCarActivityFragment extends Fragment {

    private ListView memoList;
    private TextView marque;
    private TextView modele;
    private TextView nchassis;
    private ImageView logo;
    private SQLiteHandler db;
    private Car car;

    public InfoCarActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView =  inflater.inflate(R.layout.fragment_info_car, container, false);

        Bundle b = getActivity().getIntent().getExtras();
        car = b.getParcelable("Car");
        marque = (TextView) rootView.findViewById(R.id.marque);
        modele = (TextView) rootView.findViewById(R.id.modele);
        nchassis = (TextView) rootView.findViewById(R.id.n_chassis);

        memoList = (ListView) rootView.findViewById(R.id.memo_list);
        db = new SQLiteHandler(getContext());

        marque.setText(car.getMarque());
        modele.setText(car.getModele());
        nchassis.setText(car.getNchassis());

        List<MemoCar> memoCarList = db.getMemoCarList(car.getId_car());
        MemoCarAdapter adapter = new MemoCarAdapter(getContext(), memoCarList);
        memoList.setAdapter(adapter);

        return rootView;

    }
}
