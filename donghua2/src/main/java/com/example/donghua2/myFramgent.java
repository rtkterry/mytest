package com.example.donghua2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

/**
 * Created by terry on 2016/5/27.
 */
public class myFramgent extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment,container,false);
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1);
        sa.setDuration(5000);
        LayoutAnimationController la = new LayoutAnimationController(sa,0.5f);
        view.setLayoutAnimation(la);
        return view;
    }
}
