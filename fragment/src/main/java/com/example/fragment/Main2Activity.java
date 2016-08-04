package com.example.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView text1;
    private Button fbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text1 = (TextView) findViewById(R.id.text1);
        fbtn = (Button) findViewById(R.id.fbtn);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText("改变了fragment");
            }
        });
    }
}
