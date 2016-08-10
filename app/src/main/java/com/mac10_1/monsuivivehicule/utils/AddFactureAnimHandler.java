package com.mac10_1.monsuivivehicule.utils;

import android.graphics.Color;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by mac10-1 on 09/08/2016.
 */
public class AddFactureAnimHandler {

    FrameLayout add_repa_button;
    RelativeLayout repa_edit;


    boolean status = false;
    boolean firstAdd = true;

    public AddFactureAnimHandler(FrameLayout add_repa_button, RelativeLayout repa_edit) {
        this.add_repa_button = add_repa_button;
        this.repa_edit = repa_edit;

    }

    public void evaluateClick(){
        if(firstAdd == true && status == false){
            expandEdit();
            firstAdd = false;
            status = true;
        }
/*
        else if (status == true && firstAdd == false) {

            expandSave();


        }
        */
    }

    public boolean isFirstAdd() {
        return firstAdd;
    }

    private void expandEdit() {




        Animation animation = new TranslateAnimation(0, 0,200, 0);
        Animation animation1 = new AlphaAnimation((float)0.,(float)1.);

        animation.setDuration(400);
        animation1.setDuration(450);
        animation1.setInterpolator(new DecelerateInterpolator());


        repa_edit.startAnimation(animation);
        repa_edit.setVisibility(View.VISIBLE);
        repa_edit.setClickable(true);


    }

    /*

    private void expandSave(){
        Animation animation = new TranslateAnimation(0, 0,0, 200);
        Animation animation1 = new AlphaAnimation((float)0.,(float)1.);

        animation.setDuration(400);
        animation1.setDuration(450);
        animation1.setInterpolator(new DecelerateInterpolator());

        save_facture_button.startAnimation(animation);
        save_facture_button.setVisibility(View.VISIBLE);
        save_facture_button.setClickable(true);
    }

*/
}
