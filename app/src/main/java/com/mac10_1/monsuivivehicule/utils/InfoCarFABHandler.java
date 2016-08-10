package com.mac10_1.monsuivivehicule.utils;

import android.app.Application;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
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

        //show_layout = AnimationUtils.loadAnimation(application, R.anim.fab1_show);




    }

    private void expandFAB() {


        AnimationSet animationSet = new AnimationSet(true);

        Animation animation = new TranslateAnimation(500, 0,0, 0);
        Animation animation1 = new AlphaAnimation((float)0.,(float)1.);
        animation.setDuration(300);
        animation1.setDuration(450);
        animation1.setInterpolator(new DecelerateInterpolator());
        animationSet.addAnimation(animation);
        animationSet.addAnimation(animation1);


        fram1.startAnimation(animationSet);
        fram1.setVisibility(View.VISIBLE);
        fram1.setClickable(true);

        fram2.startAnimation(animationSet);
        fram2.setVisibility(View.VISIBLE);
        fram2.setClickable(true);



        fab_buttons_layout.setBackgroundColor(Color.parseColor("#DD000000"));
        fab_buttons_layout.startAnimation(animation1);
        fab_buttons_layout.setOnClickListener(this);
        fab_buttons_layout.setClickable(true);

    }

    private void hideFAB() {

        AnimationSet animationSet = new AnimationSet(true);
        Animation animation = new TranslateAnimation(0, 500,0, 0);
        Animation animation1 = new AlphaAnimation((float)1.,(float)0.);
        animation.setDuration(300);
        animation1.setDuration(450);
        animation1.setInterpolator(new DecelerateInterpolator());
        animationSet.addAnimation(animation);
        animationSet.addAnimation(animation1);


        fram1.startAnimation(animationSet);
        fram1.setVisibility(View.INVISIBLE);
        fram1.setClickable(false);

        fram2.startAnimation(animationSet);
        fram2.setVisibility(View.INVISIBLE);
        fram2.setClickable(false);

        //fab_buttons_layout.startAnimation(hide_layout);

        fab_buttons_layout.setBackgroundColor(Color.parseColor("#00000000"));
        fab_buttons_layout.startAnimation(animation1);
        //fab_buttons_layout.setVisibility(View.INVISIBLE);
        fab_buttons_layout.setClickable(false);
        //fab_buttons_layout.setOnClickListener(null);


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
