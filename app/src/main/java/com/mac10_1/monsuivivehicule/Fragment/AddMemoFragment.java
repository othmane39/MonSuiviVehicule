package com.mac10_1.monsuivivehicule.Fragment;

import android.app.Fragment;
import android.graphics.Picture;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.Car;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import org.w3c.dom.Text;

/**
 * Created by mac10-1 on 05/08/2016.
 */
public class AddMemoFragment extends Fragment implements View.OnClickListener {


    private SQLiteHandler db;
    private TextView name;
    private TextView unit;
    private TextView value;
    private Car car;
    private RelativeLayout rl_goback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_add_memo, container, false);

        Bundle b = getActivity().getIntent().getExtras();
        car = b.getParcelable("Car");
        name = (TextView) rootView.findViewById(R.id.memo_name);
        value = (TextView) rootView.findViewById(R.id.memo_value);
        unit = (TextView) rootView.findViewById(R.id.memo_unit);
        rl_goback = (RelativeLayout) rootView.findViewById(R.id.rl_go_back);

        rl_goback.setOnClickListener(this);
        db = new SQLiteHandler(getContext());


        return rootView;
    }

    public void addMemo() {

        db.addMemo(car.getId_car(), name.getText().toString(), Integer.parseInt(value.getText().toString()), unit.getText().toString());

    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStack();
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        getActivity().setTitle("Info Vehicule");
        fab.setImageResource(android.R.drawable.ic_input_add);
        getFragmentManager().popBackStack();
    }
}
