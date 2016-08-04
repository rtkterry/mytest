package com.example.mysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int version = 5;
    private Mydatabase mydatabase = null;
    private SQLiteDatabase db;
    private Button creat;
    private Button uptable;
    private Button insert;
    private Button query;
    private Button detect;
    private Button change;
    private ListView list;
    private List<String> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datalist = new ArrayList<String>();
        creat = (Button) findViewById(R.id.creat);
        uptable = (Button) findViewById(R.id.uptable);
        insert = (Button) findViewById(R.id.insert);
        change = (Button) findViewById(R.id.change);
        query = (Button) findViewById(R.id.query);
        detect = (Button) findViewById(R.id.detect);
        list = (ListView) findViewById(R.id.list);

        creat.setOnClickListener(this);
        uptable.setOnClickListener(this);
        insert.setOnClickListener(this);
        change.setOnClickListener(this);
        detect.setOnClickListener(this);
        query.setOnClickListener(this);

        mydatabase = new Mydatabase(getApplicationContext(), "RTKXX.db", null, version);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.creat:
                       mydatabase.getWritableDatabase();
                break;

            case R.id.uptable:
                mydatabase = new Mydatabase(getApplicationContext(),"RTKXX.db",null,version);
                mydatabase.getWritableDatabase();

                break;

            case R.id.insert:

                    db = mydatabase.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("author","terry1");
                    values.put("price",10.99);
                    values.put("pages",10);
                    db.insert("RTK",null,values);
                    db.close();


                break;

            case R.id.query:
                    db = mydatabase.getWritableDatabase();
                    Cursor cursor = db.query("RTK",null,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            String author = cursor.getString(cursor.getColumnIndex("author"));
                            Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                            int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        }while (cursor.moveToNext());
                    }
                    cursor.close();
                db.close();
                break;


            case R.id.detect:
                db = mydatabase.getWritableDatabase();
                db.delete("RTK","author = ?", new String[]{"terry1"});
                db.close();
                break;


            case R.id.change:
                db = mydatabase.getWritableDatabase();
                ContentValues values1 =  new ContentValues();
                values1.put("price",9.99);
                db.update("RTK",values1,"author = ?",new String[]{"terry1"});
                db.close();
                break;
        }

    }
}
