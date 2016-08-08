package com.mac10_1.monsuivivehicule.utils;

import android.app.Application;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.mac10_1.monsuivivehicule.R;

/**
 * Created by mac10-1 on 08/08/2016.
 */
public class InfoCarFABHandler implements View.OnClickListener{

    FrameLayout fram1;
    FrameLayout fram2;
    FrameLayout fram3;

    private boolean FAB_Status = false;

    //Animations
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;

    public InfoCarFABHandler(Application application, FrameLayout fram1, FrameLayout fram2, FrameLayout fram3) {
        this.fram1 = fram1;
        this.fram2 = fram2;
        this.fram3 = fram3;
        show_fab_1 = AnimationUtils.loadAnimation(application, R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(application, R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(application, R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(application, R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(application, R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(application, R.anim.fab3_hide);
    }

    private void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fram1.getLayoutParams();
        layoutParams.rightMargin += (int) (fram1.getWidth() * 1.);
        layoutParams.bottomMargin += (int) (fram1.getHeight() * 0.25);
        fram1.setLayoutParams(layoutParams);
        fram1.startAnimation(show_fab_1);
        fram1.setVisibility(View.VISIBLE);
        fram1.setClickable(true);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fram2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fram2.getWidth() * 1.5);
        layoutParams2.bottomMargin += (int) (fram2.getHeight() * 1.5);
        fram2.setLayoutParams(layoutParams2);
        fram2.startAnimation(show_fab_2);
        fram2.setClickable(true);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fram3.getLayoutParams();
        layoutParams3.rightMargin += (int) (fram3.getWidth() * 0.25);
        layoutParams3.bottomMargin += (int) (fram3.getHeight() * 1.7);
        fram3.setLayoutParams(layoutParams3);
        fram3.startAnimation(show_fab_3);
        fram3.setClickable(true);
    }

    private void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fram1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fram1.getWidth() * 1.);
        layoutParams.bottomMargin -= (int) (fram1.getHeight() * 0.25);
        fram1.setLayoutParams(layoutParams);
        fram1.startAnimation(hide_fab_1);
        fram1.setClickable(false);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fram2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fram2.getWidth() * 1.5);
        layoutParams2.bottomMargin -= (int) (fram2.getHeight() * 1.5);
        fram2.setLayoutParams(layoutParams2);
        fram2.startAnimation(hide_fab_2);
        fram2.setClickable(false);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fram3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fram3.getWidth() * 0.25);
        layoutParams3.bottomMargin -= (int) (fram3.getHeight() * 1.7);
        fram3.setLayoutParams(layoutParams3);
        fram3.startAnimation(hide_fab_3);
        fram3.setClickable(false);
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
