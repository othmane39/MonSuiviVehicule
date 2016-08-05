package com.mac10_1.monsuivivehicule.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mac10_1.monsuivivehicule.Activity.MyCarsActivity;
import com.mac10_1.monsuivivehicule.Adapter.CarAdapter;
import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.Car;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import java.util.List;

/**
 * Created by mac10-1 on 05/08/2016.
 */
public class MyCarsFragment extends Fragment implements View.OnClickListener {

    private ListView listViewCar;
    private SQLiteHandler db;
    private List<Car> cars;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_my_cars, container, false);

        listViewCar = (ListView) rootView.findViewById(R.id.list_view_cars);

        db = new SQLiteHandler(getContext());

        cars = db.getCarsList();

        CarAdapter adapter = new CarAdapter(getContext(), cars);
        listViewCar.setAdapter(adapter);
        listViewCar.setOnItemClickListener(adapter);
        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
