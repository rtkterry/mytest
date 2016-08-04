package com.example.getprovider_data;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Myprovider == >";
    private Button arraydata,simpledata,basedata;
    private ListView listitem;
    private SimpleAdapter adapter;
    private ArrayAdapter arrayAdapter;
    private ArrayList<HashMap<String,Object>> listdata = null;
    private static final String uri_define = "content://com.example.mysql.provider/RTK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arraydata = (Button) findViewById(R.id.arraydata);
        simpledata = (Button) findViewById(R.id.simpledata);
        basedata = (Button) findViewById(R.id.basedata);
        listitem = (ListView) findViewById(R.id.listitem);

        arraydata.setOnClickListener(this);
        simpledata.setOnClickListener(this);
        basedata.setOnClickListener(this);

        listdata = new ArrayList<HashMap<String,Object>>();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.simpledata:
                adapter = new SimpleAdapter(this,getdata(),R.layout.item,new String[]{"author","price","pages"},new int[]{R.id.text1,R.id.text2,R.id.text3});
                listitem.setAdapter(adapter);
                break;

            case R.id.basedata:
                listdata = getdata();
                    Myadapter mydapter = new Myadapter();
                Log.d(TAG, "onClick: Thread == "+Thread.currentThread().getName());
                    listitem.setAdapter(mydapter);
                    listitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            int touch_id = position + 1;
                            Toast.makeText(MainActivity.this,"你选择了第"+touch_id+"项目",Toast.LENGTH_SHORT).show();
                        }
                    });
                break;


            case R.id.arraydata:

//                arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,new String[]{"author","price","pages"});
                listitem.setAdapter(new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_single_choice,new String[]{"author","price","pages"}));
                listitem.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                break;

        }

    }

    private ArrayList<HashMap<String,Object>> getdata() {
        Uri uri = Uri.parse(uri_define);
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        HashMap<String, Object> map;
        if(cursor != null)
        {
            listdata.clear();
            if(cursor.moveToFirst()) {
                do {
                    map = new HashMap<String, Object>();
                    map.put("author",cursor.getString(cursor.getColumnIndex("author")));
                    map.put("price", cursor.getDouble(cursor.getColumnIndex("price")));
                    map.put("pages",cursor.getInt(cursor.getColumnIndex("pages")));
                    listdata.add(map);
//                    Log.d("myprovider ==>", "author ==" + cursor.getString(cursor.getColumnIndex("author")));
//                    Log.d("myprovider ==>", "price ==" + cursor.getDouble(cursor.getColumnIndex("price")));
//                    Log.d("myprovider ==>", "pages ==" + cursor.getInt(cursor.getColumnIndex("pages")));
                }while (cursor.moveToNext());
            }
        }
        Log.d(TAG, "getdata: Thread == "+Thread.currentThread().getName());
        return listdata;

    }

    public class Myadapter extends BaseAdapter{

        public final class ViewHolder{
            public TextView author;
            public TextView price;
            public TextView pages;
            public Button btn;
        }
        private  ViewHolder holder;

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
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item,null);
                holder = new ViewHolder();
                holder.author = (TextView) convertView.findViewById(R.id.text1);
                holder.price = (TextView) convertView.findViewById(R.id.text2);
                holder.pages = (TextView) convertView.findViewById(R.id.text3);
                holder.btn = (Button) convertView.findViewById(R.id.btn);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.author.setText(listdata.get(position).get("author").toString());
            holder.author.setText(listdata.get(position).get("price").toString());
            holder.author.setText(listdata.get(position).get("pages").toString());
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int check_id = position + 1;
                  //  Toast.makeText(getApplicationContext(),"你选择了第"+check_id+"项",Toast.LENGTH_SHORT).show();
                   AlertDialog.Builder  alertDialog = new AlertDialog.Builder(getApplicationContext());
                    alertDialog.setTitle("提示");
                    alertDialog.setMessage("你选择了第\"+check_id+\"项\"");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    alertDialog.show();

                }
            });
//            Log.d(TAG, "base: Thread == "+Thread.currentThread().getName());
           // Log.d(TAG, "author ==: "+getdata().get(position).get("author").toString());
            return convertView;
        }
    }

}
