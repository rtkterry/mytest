package com.example.addremove;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootview;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        MyFragment MF = new MyFragment();
        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction FT = fm.beginTransaction();
          FT.add(R.id.main,MF);
        FT.commit();
    }

    public class MyFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            LinearLayout view = (LinearLayout) inflater.inflate(R.id.rootview,container,false);

            ScaleAnimation SA = new ScaleAnimation(0,1,0,1);
            LayoutAnimationController LA = new LayoutAnimationController(SA,0);
            view.setLayoutAnimation(LA);
            return view;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id){
            case R.id.action_settings:
                return  true;

            case R.id.add:
                addBtton();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addBtton() {
        Button btn = new Button(this);
        btn.setText("remove me");
        rootview.addView(btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootview.removeView(v);
            }
        });
    }
}
