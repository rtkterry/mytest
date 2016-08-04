package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;
    private static final int Noti_ID = 1200;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn){
            count ++;
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("你有"+count+"个消息");
            builder.setContentInfo("哇哦，很高兴见到你!");
            Notification notification = builder.build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(Noti_ID,notification);
        }
    }
}
