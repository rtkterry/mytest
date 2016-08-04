package com.example.mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by terry on 2016/7/25.
 */
public class Mydatabase extends SQLiteOpenHelper{

    private Context mcontext;
    private int mversion = 0;
    private static final String CREAT_TABLE = "create table RTK("
            + "id integer primary key autoincrement, author text, price real, pages integer)";

    public interface versioncallbeck{
        int getversion();
    }

    public Mydatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
        mversion = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_TABLE);
        Toast.makeText(mcontext,"数据库创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists RTK");
        onCreate(db);
    }
}
