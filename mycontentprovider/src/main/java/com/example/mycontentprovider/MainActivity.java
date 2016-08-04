package com.example.mycontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button add,query,update,delete;
    private static final String uri_dir = "content://com.example.mysql.provider/RTK";
    private Uri uri;
    private String id_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.add);
        query = (Button) findViewById(R.id.Query);
        update = (Button) findViewById(R.id.Update);
        delete = (Button) findViewById(R.id.Delete);

        add.setOnClickListener(this);
        query.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                uri = Uri.parse("content://com.example.mysql.provider/RTK");
                ContentValues values = new ContentValues();
                values.put("author","terry");
                values.put("price",0.99);
                values.put("pages",100);
                Uri uri_new = getContentResolver().insert(uri,values);
                id_new = uri_new.getPathSegments().get(1);
                break;

            case R.id.Delete:
                uri = Uri.parse(uri_dir);
                getContentResolver().delete(uri,"id = ?",new String[]{id_new});
                break;

            case R.id.Update:
                uri = Uri.parse(uri_dir+"/"+id_new);
                Log.d("myprovider == >","id_new =="+id_new);
                ContentValues values1 = new ContentValues();
                values1.put("author","terry1");
                values1.put("price",30.99);
                values1.put("pages",50);

                getContentResolver().update(uri,values1,"id = ?",new  String[]{id_new});
                break;

            case R.id.Query:
                uri = Uri.parse(uri_dir);
//                Log.d("myprovide ==>","id_new == "+id_new);
//                Cursor cursor = getContentResolver().query(uri,null,"id = ?",new String[]{id_new},null);
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor != null){
                    if(cursor.moveToFirst()) {
                        do {
                            Log.d("myprovider ==>", "author ==" + cursor.getString(cursor.getColumnIndex("author")));
                            Log.d("myprovider ==>", "price ==" + cursor.getDouble(cursor.getColumnIndex("price")));
                            Log.d("myprovider ==>", "pages ==" + cursor.getInt(cursor.getColumnIndex("pages")));
                        }while (cursor.moveToNext());
                    }
                }

                break;

        }
    }
}
