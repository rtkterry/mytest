package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.TextView;

/**
 * Created by terry on 2016/5/24.
 */
public class fragment1 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmeng1,container,false);
        TextView text1 = (TextView) view.findViewById(R.id.text1);
        text1.setText("第1个静态fragment");
        return view;
    }
}
