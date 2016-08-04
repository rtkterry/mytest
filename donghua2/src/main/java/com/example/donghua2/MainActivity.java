package com.example.donghua2;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private myFramgent mf = null;
    private ArrayAdapter<String> arrayAdapter = null;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  /*      if(savedInstanceState == null) {
            mf = new myFramgent();
            android.support.v4.app.FragmentManager FM = getSupportFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.add(R.id.linear,mf);
            FT.commit();
        }*/
        list = (ListView) findViewById(R.id.list);
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1);
        sa.setDuration(5000);
        LayoutAnimationController LA = new LayoutAnimationController(sa,0.5f);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"xx","aa","cc"});
        list.setAdapter(arrayAdapter);
        list.setLayoutAnimation(LA);
    }
}
