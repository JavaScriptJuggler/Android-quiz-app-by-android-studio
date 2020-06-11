package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class connection extends SQLiteOpenHelper {
    private static final String databasename="Mydatabase";
    private  static final int version=1;

    public connection(Context context) {
        super(context, databasename, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists table1(_id integer primary key autoincrement,qs text,op1 text,op2 text,op3 text,op4 text,ans text)");

        insertdata("Developeer Name","Surojit","Soumya","Sumanta","Abinash","Soumya",db);
        insertdata("First user","Soumya","Surojit","Abinash","Sumanta","Surojit",db);
        insertdata("favourite fruits","mango","papaya","lichi","bedana","lichi",db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertdata(String a,String b,String c,String d,String e,String f,SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();
        values.put("qs",a);
        values.put("op1",b);
        values.put("op2",c);
        values.put("op3",d);
        values.put("op4",e);
        values.put("ans",f);

        db.insert("table1",null,values);
    }
}
