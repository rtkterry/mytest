package com.example.viewpager;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by terry on 2016/5/26.
 */
public class Mypagerview extends PagerAdapter{

    private List<View> list;
    private List<String> tablist;
    public Mypagerview(List<View> list,List<String> tablist) {
        this.list = list;
        this.tablist = tablist;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("Main->add",""+position);
        container.addView(list.get(position));
        return list.get(position);
//       return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(list.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position);
    }
}
