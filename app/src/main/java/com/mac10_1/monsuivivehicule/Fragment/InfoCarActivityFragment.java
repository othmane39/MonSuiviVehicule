package com.mac10_1.monsuivivehicule.Fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.Activity.InfoCarActivity;
import com.mac10_1.monsuivivehicule.Adapter.FactureCarAdapter;
import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.Car;
import com.mac10_1.monsuivivehicule.utils.Facture;
import com.mac10_1.monsuivivehicule.utils.MemoCar;
import com.mac10_1.monsuivivehicule.Adapter.MemoCarAdapter;
import com.mac10_1.monsuivivehicule.utils.SQLiteHandler;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfoCarActivityFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView memoListView;
    private ListView factureListView;
    private TextView marque;
    private TextView modele;
    private TextView nchassis;
    private ImageView logo;
    private SQLiteHandler db;
    private Car car;
    private List<MemoCar> memoCarList;
    private List<Facture> facturesList;
    private MemoCarAdapter memoCarAdapter;
    private FactureCarAdapter factureCarAdapter;
    private ShowFactureFragment factureFragment;
    private ViewGroup container;
    private FloatingActionButton fab;

    private View viewForContextMenu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView =  inflater.inflate(R.layout.fragment_info_car, container, false);
        this.container = container;
        Bundle b = getActivity().getIntent().getExtras();
        car = b.getParcelable("Car");
        marque = (TextView) rootView.findViewById(R.id.marque);
        modele = (TextView) rootView.findViewById(R.id.modele);
        nchassis = (TextView) rootView.findViewById(R.id.n_chassis);
        logo = (ImageView) rootView.findViewById(R.id.logo);

        memoListView = (ListView) rootView.findViewById(R.id.memo_list);
        factureListView = (ListView) rootView.findViewById(R.id.factures_list);

        db = new SQLiteHandler(getContext());

        marque.setText(car.getMarque());
        modele.setText(car.getModele());
        nchassis.setText(car.getNchassis());

        memoCarList = db.getMemoCarList(car.getId_car());
        facturesList = db.getFacturesList(car.getId_car());

        memoCarAdapter = new MemoCarAdapter(getContext(), memoCarList);
        memoListView.setAdapter(memoCarAdapter);
        registerForContextMenu(memoListView);


        factureCarAdapter = new FactureCarAdapter(getContext(), facturesList);
        factureListView.setAdapter(factureCarAdapter);
        factureListView.setOnItemClickListener(this);

        registerForContextMenu(factureListView);

        String urilogo = "@drawable/"+ car.getMarque().toLowerCase();
        int imageResource = getContext().getResources().getIdentifier(urilogo, null, getContext().getPackageName());
        if(imageResource != 0x0) {
            Drawable res = getContext().getResources().getDrawable(imageResource);
            logo.setImageDrawable(res);
        }else {
            logo.setImageResource(R.drawable.nologo);
        }

        justifyListViewHeightBasedOnChildren(memoListView);
        justifyListViewHeightBasedOnChildren(factureListView);


        return rootView;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            //menu.setHeaderTitle("Vidange");
            String[] menuItems = new String[1];
            if (v.getId()==R.id.memo_list) {
                menuItems[0] = "Supprimer memo";
                viewForContextMenu = memoListView;
            }
            else if(v.getId()==R.id.factures_list) {
                menuItems[0] = "Supprimer facture";
                viewForContextMenu = factureListView;
            }

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
            if(viewForContextMenu == memoListView) {
                if (db.removeMemoById(memoCarList.get(info.position).getIdMemo())) {
                    Log.d("DB", "memo removed successfully");
                    refreshMemo();
                }
            } else if(viewForContextMenu == factureListView){
                if (db.removeFactureById(facturesList.get(info.position).getIdFact())){
                    Log.d("DB", "facture removed successfully");
                    refreshFactures();
                }
            }
        }

        return true;
    }

    public void refreshMemo(){
        memoCarList = db.getMemoCarList(car.getId_car());
        memoCarAdapter.clear();
        memoCarAdapter.addAll(memoCarList);
        memoCarAdapter.notifyDataSetChanged();
        justifyListViewHeightBasedOnChildren(memoListView);
    }

    public void refreshFactures(){
        facturesList = db.getFacturesList(car.getId_car());
        factureCarAdapter.clear();
        factureCarAdapter.addAll(facturesList);
        factureCarAdapter.notifyDataSetChanged();
        justifyListViewHeightBasedOnChildren(factureListView);
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
        Log.d("Missa", "NotFrag " + par.height);
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    public ShowFactureFragment getFactureFragment() {
        return factureFragment;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Facture facture = facturesList.get(position);
        facture.setReparations(db.getReparationsList(facture.getIdFact()));
        Bundle args = new Bundle();
        args.putParcelable("Facture", facture);
        getActivity().getIntent().putExtras(args);
        factureFragment = new ShowFactureFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout, factureFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();


    }



}
