package com.example.viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private int[] res = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4};
    private float startX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);

        for(int i = 0; i < res.length;i ++)
        viewFlipper.addView(getview(i));


    }

    private ImageView getview(int re) {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(res[re]);
        return image;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:


//                viewFlipper.startFlipping();
                startX = event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                if((event.getX() -startX)>100){
                    viewFlipper.setInAnimation(this,R.anim.left_in);
                    viewFlipper.setOutAnimation(this,R.anim.left_out);
                    viewFlipper.showPrevious();
                }
                if((startX -  event.getX()) > 100){
                    viewFlipper.setInAnimation(this,R.anim.right_in);
                    viewFlipper.setOutAnimation(this,R.anim.right_out);
                    viewFlipper.showNext();
                }

                viewFlipper.startFlipping();
                break;

            case MotionEvent.ACTION_UP:
                viewFlipper.stopFlipping();
                break;

        }
        return super.onTouchEvent(event);
    }
}
