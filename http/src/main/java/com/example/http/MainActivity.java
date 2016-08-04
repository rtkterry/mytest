package com.example.http;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button send;
    private TextView viewtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button) findViewById(R.id.send);
        viewtext = (TextView) findViewById(R.id.viewtext);
        send.setOnClickListener(this);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    String request = (String) msg.obj;
                    viewtext.setText(request);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        sendwithhttpconntion();
    }

    private void sendwithhttpconntion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://apistore.baidu.com/microservice/weather?citypinyin=beijing");
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null){
                        sb.append(line);
                    }

                    Message message = new Message();
                    message.what = 1;
                    message.obj = sb.toString();
                    handler.sendMessage(message);

//                    parseXMLwithPull(sb.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(connection != null)
                        connection.disconnect();
                }

            }
        }).start();

    }

    private void parseXMLwithPull(String xmldata) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmldata));

            int type = parser.getEventType();
            String id = "";
            String name = "";
            String version = "";

            while (type != XmlPullParser.END_DOCUMENT){
                String nodename = parser.getName();
                switch (type){
                    case XmlPullParser.START_TAG:
                        if("id".equals(nodename))
                            id = parser.nextText();
                        else if("name".equals(nodename))
                            name = parser.nextText();
                        else if("version".equals(nodename))
                            version = parser.nextText();
                        break;

                    case XmlPullParser.END_TAG:
                        if("app".equals(nodename)){
                            Log.d("Terry","id = "+id);
                            Log.d("Terry","name = "+name);
                            Log.d("Terry","version = "+version);
                        }
                        break;

                }
                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
