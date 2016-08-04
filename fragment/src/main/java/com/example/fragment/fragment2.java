package com.example.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by terry on 2016/5/24.
 */
public class fragment2 extends Fragment {

    private String date = "Think you!";
    private Work work;
    public interface Work{
        public void think(String date);
    }

    @Override
    public void onAttach(Activity activity) {
        work = (Work) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
//        TextView tv = (TextView) view.findViewById(R.id.ftext2);
//        tv.setText(name);
        //String name = (String) getArguments().get("name");
        //Toast.makeText(getActivity(), ""+name, Toast.LENGTH_SHORT).show();
//        Log.i("fragment2",""+name);
        work.think(date);
        return view;
    }

}
