package com.mac10_1.monsuivivehicule.Activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mac10_1.monsuivivehicule.Fragment.AddFactureFragment;
import com.mac10_1.monsuivivehicule.Fragment.AddMemoFragment;
import com.mac10_1.monsuivivehicule.Fragment.InfoCarActivityFragment;
import com.mac10_1.monsuivivehicule.Fragment.ShowFactureFragment;
import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.InfoCarFABHandler;

public class InfoCarActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    Toolbar toolbar;
    InfoCarActivityFragment infoCarActivityFragment;
    AddMemoFragment addMemoFragment;
    AddFactureFragment addFactureFragment;
    ShowFactureFragment showFactureFragment;

    InfoCarFABHandler fabHandler;

    private View viewForContextMenu;

    FrameLayout fab_facture, fab_memo;
    RelativeLayout fab1_2_container;

    int menuSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_car);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        infoCarActivityFragment = new InfoCarActivityFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment_layout, infoCarActivityFragment); // f1_container is your FrameLayout container
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        toolbar.setTitle("Info Vehicule");

        //getSupportFragmentManager().fi
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_memo = (FrameLayout) findViewById(R.id.fab_ajouter_memo);
        fab_facture = (FrameLayout) findViewById(R.id.fab_ajouter_facture);
        fab1_2_container = (RelativeLayout) findViewById(R.id.fab_1_2_layout_container);

        //fab3 = (FrameLayout) findViewById(R.id.fab3_layout);


        fabHandler = new InfoCarFABHandler(getApplication(), fab_memo, fab_facture, fab1_2_container);



        fab.setOnClickListener(this);
        fab_memo.setOnClickListener(this);
        fab_facture.setOnClickListener(this);
    }


    public void onClick(View view) {
        if(view == fab ) {

            if (addMemoFragment != null && addMemoFragment.isVisible()) {
                if(addMemoFragment.saveMemo()) {

                    toolbar.setTitle("Info Vehicule");
                    Snackbar.make(view, "Mémo enregistré", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fab.setImageResource(android.R.drawable.ic_input_add);
                    getFragmentManager().popBackStack();
                    infoCarActivityFragment.refreshMemo();
                    //Hide Keyboard
                    View v = this.getCurrentFocus();
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }else Snackbar.make(view, "Erreur sur l'enregistrement", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else if (addFactureFragment != null && addFactureFragment.isVisible()){
                if(addFactureFragment.saveFacture()) {
                    Snackbar.make(view, "Facture enregistrée", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fab.setImageResource(android.R.drawable.ic_input_add);
                    getFragmentManager().popBackStack();
                    infoCarActivityFragment.refreshFactures();
                    //Hide Keyboard
                    View v = this.getCurrentFocus();

                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }

                }
            } else if (infoCarActivityFragment != null && infoCarActivityFragment.isVisible())
                fabHandler.evaluateClick();
        }else if(view == fab_memo){
            fabHandler.evaluateClick();
            addMemoFragment = new AddMemoFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.add(R.id.fragment_layout, addMemoFragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("Ajouter Mémo");
            fab.setImageResource(R.drawable.ic_save_white);
        }else if(view == fab_facture){

            fabHandler.evaluateClick();
            addFactureFragment = new AddFactureFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.add(R.id.fragment_layout, addFactureFragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("Ajouter Facture");
            fab.setImageResource(R.drawable.ic_save_white);
        }




    }

    @Override
    public void onBackPressed() {

        if(addMemoFragment != null && addMemoFragment.isVisible()){

            toolbar.setTitle("Info Vehicule");
            fab.setImageResource(android.R.drawable.ic_input_add);
            getFragmentManager().popBackStack();


        }
        else if(addFactureFragment != null && addFactureFragment.isVisible()){
            toolbar.setTitle("Info Vehicule");
            fab.setImageResource(android.R.drawable.ic_input_add);
            getFragmentManager().popBackStack();

        }
        else if(infoCarActivityFragment != null && infoCarActivityFragment.getFactureFragment() != null && infoCarActivityFragment.getFactureFragment().isVisible()) {
            getFragmentManager().popBackStack();
            fab.setImageResource(android.R.drawable.ic_input_add);
            fab.show();
        }
        else if(infoCarActivityFragment != null && infoCarActivityFragment.isVisible()) {
            finish();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //menu.setHeaderTitle("Vidange");
        String[] menuItems = new String[1];
        if (v.getId()==R.id.memo_list) {
            menuItems[0] = "Supprimer memo";
            menuSwitcher = 1;
        }
        else if(v.getId()==R.id.factures_list) {
            menuItems[0] = "Supprimer facture";
            menuSwitcher = 2;
        }
        else  if(v.getId() == R.id.reparations_list_view) {
            menuItems[0] = "Supprimer reparation";
            menuSwitcher = 3;
        }else if(v.getId() == R.id.facture_reparations_list_view) {
            menuItems[0] = "Supprimer reparation";
            menuSwitcher = 4;
        }
        else menuSwitcher = -1;
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
            if(menuSwitcher ==1) {
                infoCarActivityFragment.removeMemo(info.position);

            } else if(menuSwitcher ==2){
                infoCarActivityFragment.removeFacture(info.position);
            }
            else if(menuSwitcher ==3){
                addFactureFragment.removeReparation(info.position);
            }
            else if(menuSwitcher ==4){
                infoCarActivityFragment.getFactureFragment().removeReparation(info.position);
            }



        }

        return true;
    }
}
