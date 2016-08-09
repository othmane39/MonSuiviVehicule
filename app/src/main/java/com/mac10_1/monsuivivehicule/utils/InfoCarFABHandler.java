package com.mac10_1.monsuivivehicule.utils;

import android.app.Application;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mac10_1.monsuivivehicule.R;

/**
 * Created by mac10-1 on 08/08/2016.
 */
public class InfoCarFABHandler implements View.OnClickListener{

    FrameLayout fram1;
    FrameLayout fram2;

    RelativeLayout fab_buttons_layout;

    private boolean FAB_Status = false;


    Animation show_layout;



    public InfoCarFABHandler(Application application, FrameLayout fram1, FrameLayout fram2, RelativeLayout fab_buttons_layout) {
        this.fram1 = fram1;
        this.fram2 = fram2;
        this.fab_buttons_layout = fab_buttons_layout;

        show_layout = AnimationUtils.loadAnimation(application, R.anim.fab1_show);




    }

    private void expandFAB() {


        Animation animation = new TranslateAnimation(500, 0,0, 0);
        animation.setDuration(300);

        fram1.startAnimation(animation);
        fram1.setVisibility(View.VISIBLE);
        fram1.setClickable(true);

        fram2.startAnimation(animation);
        fram2.setVisibility(View.VISIBLE);
        fram2.setClickable(true);


        fab_buttons_layout.startAnimation(show_layout);
        fab_buttons_layout.setBackgroundColor(Color.parseColor("#DD000000"));
        fab_buttons_layout.setOnClickListener(this);
        fab_buttons_layout.setClickable(true);

    }

    private void hideFAB() {

        Animation animation = new TranslateAnimation(0, 500,0, 0);
        animation.setDuration(300);


        fram1.startAnimation(animation);
        fram1.setVisibility(View.INVISIBLE);
        fram1.setClickable(false);

        fram2.startAnimation(animation);
        fram2.setVisibility(View.INVISIBLE);
        fram2.setClickable(false);

        //fab_buttons_layout.startAnimation(hide_layout);
        fab_buttons_layout.setBackgroundColor(Color.parseColor("#00000000"));
        fab_buttons_layout.setVisibility(View.INVISIBLE);
        fab_buttons_layout.setClickable(false);
        //fab_buttons_layout.setOnClickListener(null);


   }

    public boolean isFAB_Status() {
        return FAB_Status;
    }

    @Override
    public void onClick(View v) {

        evaluateClick();


    }

    public void evaluateClick(){
        if (FAB_Status == false) {
            //Display FAB menu
            expandFAB();
            FAB_Status = true;
        } else {
            //Close FAB menu
            hideFAB();
            FAB_Status = false;
        }
    }
}
