package com.mac10_1.monsuivivehicule.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.Car;
import com.mac10_1.monsuivivehicule.utils.MemoCar;
import com.mac10_1.monsuivivehicule.Adapter.MemoCarAdapter;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

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
    private List<MemoCar> memoCarList;
    private MemoCarAdapter adapter;

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

        memoCarList = db.getMemoCarList(car.getId_car());
        adapter = new MemoCarAdapter(getContext(), memoCarList);
        memoList.setAdapter(adapter);
        registerForContextMenu(memoList);

        return rootView;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.memo_list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            //menu.setHeaderTitle("Vidange");
            String[] menuItems = {"modifier", "supprimer"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
            Log.e("TAG", "onCreate");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        if(menuItemIndex == 0){ //modifier

        }else if(menuItemIndex == 1){ //supprimer
            if(db.removeMemoById(memoCarList.get(info.position).getIdMemo())) {
                Log.d("DB", "memo removed successfully");
                memoCarList = db.getMemoCarList(car.getId_car());
                adapter.clear();
                adapter.addAll(memoCarList);
                adapter.notifyDataSetChanged();
            }


        }

        return true;
    }

}
