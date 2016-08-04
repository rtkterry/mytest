package com.example.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,fragment2.Work{

    private Button btn1,btn2,btn3,btn4;
    private fragment2 fm = null;
    private FragmentManager FM = null;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                break;

            case R.id.btn2:
                count ++;
                FM = getFragmentManager();
                android.app.FragmentTransaction ft = FM.beginTransaction();
                if(fm == null) {
                    fm = new fragment2();
                    ft.add(R.id.main, fm);
                    ft.commit();
                }else{
                    ft.replace(R.id.main,fm);
                    ft.commit();
                    Toast.makeText(MainActivity.this, "第"+count+"次点击了..", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn3:
                fm = new fragment2();
                FM = getFragmentManager();
                android.app.FragmentTransaction ft2 = FM.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("name","来自Activity信息");
                fm.setArguments(bundle);
                ft2.add(R.id.main,fm);
                ft2.commit();


                break;

            case R.id.btn4:
                break;
        }


    }

    @Override
    public void think(String date) {
        Toast.makeText(MainActivity.this, ""+date, Toast.LENGTH_SHORT).show();
    }
}
