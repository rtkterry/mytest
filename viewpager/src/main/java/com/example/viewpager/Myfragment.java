package com.example.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by terry on 2016/5/26.
 */
public class Myfragment extends FragmentPagerAdapter{

    private List<android.support.v4.app.Fragment> fraglist;
    private List<String> tablist;

    public Myfragment(FragmentManager fm,List<android.support.v4.app.Fragment> fraglist, List<String> tablist) {
        super(fm);
        this.fraglist = fraglist;
        this.tablist = tablist;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

   }
