package com.example.viewpager;

import android.app.Fragment;
import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> list;
    private List<String> tablist;
    private Mypagerview mypagerview;
    private ViewPager viewpager;
    private PagerTabStrip tab;
    private List<android.support.v4.app.Fragment> fraglist;
    private Myfragment myfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tab = (PagerTabStrip) findViewById(R.id.tab);
    /*    View view1 = View.inflate(this,R.layout.view1,null);
        View view2 = View.inflate(this,R.layout.view2,null);
        View view3 = View.inflate(this,R.layout.view3,null);
        View view4 = View.inflate(this,R.layout.view4,null);

        list = new ArrayList<View>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);*/
        fragment1 fm1 = new fragment1();
        fragment2 fm2 = new fragment2();
        fragment3 fm3 = new fragment3();
        fragment4 fm4 = new fragment4();
        fraglist = new ArrayList<android.support.v4.app.Fragment>();
        fraglist.add(fm1);
        fraglist.add(fm2);
        fraglist.add(fm3);
        fraglist.add(fm4);

        tablist = new ArrayList<String>();
        tablist.add("第1页");
        tablist.add("第2页");
        tablist.add("第3页");
        tablist.add("第4页");
        tab.setDrawFullUnderline(false);
        tab.setTabIndicatorColor(Color.RED);

//        mypagerview = new Mypagerview(list,tablist);
        myfragment = new Myfragment(fraglist,tablist);
        viewpager.setAdapter(myfragment);

        viewpager.OnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
