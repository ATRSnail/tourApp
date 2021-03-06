package com.tour.tourapp.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ATRSnail on 2017/7/27.
 * 继承自SQLiteOpenHelper数据库类的子类，用于创建、管理数据库和版本控制
 */

public class RecordSQLiteOpenHelper extends SQLiteOpenHelper{
    public static final  String NAME = "tour.db";
    public static final Integer VERSION = 1;

    public RecordSQLiteOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //打开数据库，建立了一个叫records的表，里面只有一列name来存储历史记录：
        String sql = "create table records (id integer primary key autoincrement,name varchar(200)  )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion){
            db.execSQL("drop table if exists records");
            onCreate(db);
        }
    }
}
