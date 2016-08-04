package com.example.qq;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button Abtn,Bbtn,back,clear;
    private EditText edit;
    private ListView list_item;
    private ArrayList<HashMap<String,Object>> listdata;
    private BaseAdapter adapter;
    private HashMap<String,Object> map,map1;
    private static final int send_left = 1,send_right = 2;
    private int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Abtn = (Button) findViewById(R.id.Abtn);
        Bbtn = (Button) findViewById(R.id.Bbtn);
        back = (Button) findViewById(R.id.back);
        clear = (Button) findViewById(R.id.clear);
        edit = (EditText) findViewById(R.id.edit);
        list_item = (ListView) findViewById(R.id.list_item);

        Abtn.setOnClickListener(this);
        Bbtn.setOnClickListener(this);
        back.setOnClickListener(this);
        clear.setOnClickListener(this);

        listdata = new ArrayList<HashMap<String, Object>>();

        adapter = new Myadapter();
        list_item.setAdapter(adapter);
        Log.d("QQ==>","GIT_TEST");

        Log.d("QQ==>","GIT_TEST1");
        Log.d("QQ==>","GIT_TEST2");
		Log.d("QQ==>","GIT_TEST3");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Abtn:
                savedata(edit.getText().toString(),send_left);
                edit.setText("");
                break;

            case R.id.Bbtn:
                savedata(edit.getText().toString(),send_right);
                edit.setText("");
                break;

            case R.id.back:
                finish();
                break;

            case R.id.clear:
                listdata.clear();
                adapter.notifyDataSetChanged();
                break;

        }


    }

    private void savedata(String str,int key) {
        if(! str.isEmpty()) {
            map = new HashMap<String, Object>();
            map.put("data", str);
            map.put("key", key);
            listdata.add(map);
            adapter.notifyDataSetChanged();
            list_item.setSelection(listdata.size());
        }
    }

    public class Myadapter extends BaseAdapter{


        public class viewHolder{
            public LinearLayout left_layout;
            public LinearLayout right_layout;
            public TextView left_text;
            public TextView right_text;
        }
        private viewHolder vh;
        @Override
        public int getCount() {
            return listdata.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_daa,null);
                vh = new viewHolder();
                vh.left_layout = (LinearLayout) convertView.findViewById(R.id.left_layout);
                vh.right_layout = (LinearLayout) convertView.findViewById(R.id.right_layout);
                vh.left_text = (TextView) convertView.findViewById(R.id.left_text);
                vh.right_text = (TextView) convertView.findViewById(R.id.right_text);
                convertView.setTag(vh);
            }else{
                vh = (viewHolder) convertView.getTag();
            }
            Log.d("myqq ==> ","position == "+position+", key == "+listdata.get(position).get("key"));
            if(listdata.get(position).get("key") == send_left){
                vh.left_text.setText(listdata.get(position).get("data").toString());
                vh.right_layout.setVisibility(View.GONE);
                vh.left_layout.setVisibility(View.VISIBLE);
            }else if(listdata.get(position).get("key") == send_right){
                vh.right_text.setText(listdata.get(position).get("data").toString());
                vh.left_layout.setVisibility(View.GONE);
                vh.right_layout.setVisibility(View.VISIBLE);
            }
            return convertView;
        }
    }
}
