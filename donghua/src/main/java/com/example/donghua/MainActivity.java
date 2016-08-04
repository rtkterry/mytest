package com.example.donghua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Animation an;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        an = new MyAnim();
        an.setDuration(1000);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////               v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim));
//                animation = new AnimationUtils().loadAnimation(MainActivity.this,R.anim.anim);
                btn.startAnimation(an);


            }
        });
    }
}
