package com.mac10_1.monsuivivehicule.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private CarAdapter carAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_my_cars, container, false);

        listViewCar = (ListView) rootView.findViewById(R.id.list_view_cars);

        db = new SQLiteHandler(getContext());

        cars = db.getCarsList();

        carAdapter = new CarAdapter(getContext(), cars);
        listViewCar.setAdapter(carAdapter);
        listViewCar.setOnItemClickListener(carAdapter);
        registerForContextMenu(listViewCar);
        return rootView;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        String[] menuItems = new String[1];

        if(v.getId() == R.id.list_view_cars)
            menuItems[0] = "Supprimer vehicule";

        for (int i = 0; i<menuItems.length; i++) {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }
        Log.e("TAG", "onCreate");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        if(menuItemIndex == 0){
            if (db.removeCarById(cars.get(info.position).getId_car())) {
                Log.d("DB", "car removed successfully");
                refreshCars();
            }

        }

        return true;
    }

    private void refreshCars(){
        cars = db.getCarsList();
        carAdapter.clear();
        carAdapter.addAll(cars);
        carAdapter.notifyDataSetChanged();
        //justifyListViewHeightBasedOnChildren(memoListView);
    }
}
