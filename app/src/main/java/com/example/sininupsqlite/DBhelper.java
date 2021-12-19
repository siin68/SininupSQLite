package com.example.sininupsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public  static  final String DBNAME = "Longin.db";

    public DBhelper( Context context) {
        super(context,"Longin.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users (username varchar(100) PRIMARY KEY, password varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS users ");

    }
    public  boolean insertData(String username, String password){
    SQLiteDatabase MyDB= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users",null, contentValues);
        if (result == -1) return  false;
        else
            return  true;

    }
    public  boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ?",new String[] {username});
        if (cursor.getCount()>0)
            return  true;
        else
            return  false;
    }
    public  boolean checkusernamepassword (String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ? and password = ? ",new String[] {username,password} );
        if (cursor.getCount()>0)
            return  true;
        else
            return  false;
    }
}
