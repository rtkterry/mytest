package com.example.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;


public class mysqlprovider extends ContentProvider {

    private static final int TABLE_DIR = 0;
    private static final int TABLE_ITEM = 1;
    private static UriMatcher uriMatcher;
    private static final String uri_define = "com.example.mysql.provider";
    private Mydatabase mydatabase = null;
    private SQLiteDatabase db;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.mysql.provider","RTK",0);
        uriMatcher.addURI(uri_define,"RTK/#",TABLE_ITEM);
    }
    public mysqlprovider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int result_delete = 0;
        db = mydatabase.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
                result_delete = db.delete("RTK",selection,selectionArgs);
                break;
            case TABLE_ITEM:
                String RTKId = uri.getPathSegments().get(1);
                result_delete = db.delete("RTK","id = ?",new String[]{RTKId});
                break;
        }
        return result_delete;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        //Log.d("mysqlprovider ==>","getType == "+uriMatcher.match(uri));
//
//        Toast.makeText(getContext(),"myprovider_getType == "+uriMatcher.match(uri),Toast.LENGTH_LONG).show();
//        switch (uriMatcher.match(uri)){
//            case TABLE_DIR:
//                return  "vnd.android.cursor.dir/vndcom.example.mysql.provider.RTK";
//            case TABLE_ITEM:
//                return  "vnd.android.cursor.item/vndcom.example.mysql.provider.RTK";
//        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
//        throw new UnsupportedOperationException("Not yet implemented");
        Uri uri_return = null;
        long result_insert;
        db = mydatabase.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
            case TABLE_ITEM:
                result_insert = db.insert("RTK",null,values);
                uri_return = Uri.parse("content://com.example.mysql.provider/RTK/"+result_insert);
                break;
        }
        return uri_return;

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        mydatabase = new Mydatabase(getContext(),"RTKXX.db",null,6);
        Toast.makeText(getContext(),"RTKXX数据库创建成功",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        db = mydatabase.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
                    cursor = db.query("RTK",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE_ITEM:
                    String RTKid = uri.getPathSegments().get(1);
                    cursor = db.query("RTK",projection,"id = ?",new String[]{RTKid},null,null,sortOrder);
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int result_update = 0;
        db = mydatabase.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
                result_update = db.update("RTK",values,selection,selectionArgs);
                break;
            case TABLE_ITEM:
                String RTKId = uri.getPathSegments().get(1);
                Log.d("myprovider ==>","RTKId == "+RTKId);
                result_update = db.update("RTK",values,"id = ?",new String[]{RTKId});
                break;
        }
        return result_update;
    }
}
