package com.example.donghua;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by terry on 2016/5/27.
 */
public class MyAnim extends Animation{
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime*20) * 100),0);
        super.applyTransformation(interpolatedTime, t);

    }
}
