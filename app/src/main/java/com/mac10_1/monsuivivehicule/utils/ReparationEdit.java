package com.mac10_1.monsuivivehicule.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mac10_1.monsuivivehicule.R;

/**
 * Created by mac10-1 on 09/08/2016.
 */
public class ReparationEdit implements TextWatcher{
    private View view;
    private EditText v;

    boolean validate_view;

    public ReparationEdit( View view) {
        this.view = view;
        v = (EditText) view;
        validate_view = false;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        /*
        switch (view.getId()) {
            case R.id.reparation_cout_edit:
                if(validateTxt())
                    validate_view = true;
                else validate_view = false;
                break;
            case R.id.reparation_txt_edit:
                if(validateTxt())
                    validate_view = true;
                else validate_view = false;
                break;
            case R.id.no_facture:
                if(validateTxt())
                    validate_view = true;
                else validate_view = false;
                break;
            case R.id.date_facture:
                if(validateTxt())
                    validate_view = true;
                else validate_view = false;
                break;
            case R.id.kilometrage_facture:
                if(validateTxt())
                    validate_view = true;
                else validate_view = false;
                break;
        }
        */

    }

    public boolean isValidate_view() {
        return notEmptyEdit();
    }

    private boolean notEmptyEdit() {
        if (v.getText().toString().trim().isEmpty()) {
            v.setError("Champ requis!");
            return false;
        } else return true;

    }

    public void validateSubmit(){

    }
}
