package com.tour.tourapp.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/**
 * Created by ATRSnail on 2017/7/27.
 * 搜索历史 --数据库操作
 */

public class DBUtils {
    /*数据库变量*/
    private RecordSQLiteOpenHelper helper ;
    private SQLiteDatabase db;

    public DBUtils(Context context) {
        //实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(context);
    }

    /*插入数据*/
    public void insertData(String tempName) {
        if (CheckDataIsEmpty.checkString(tempName)){
            return;
        }
        //去掉输入的特殊字符 ’
        String name = tempName.replaceAll("'", "");
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + name + "')");
        db.close();
    }

    /*清空数据*/
    public void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

    /*模糊查询数据 并显示在ListView列表上*/
    public Cursor queryData(String tempName) {

        String name = tempName.replaceAll("'","");
        //模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + name + "%' order by id desc ", null);
        return cursor;
    }

    /*检查数据库中是否已经有该条记录*/
    public boolean hasData(String tempName) {
        //从Record这个表里找到name=name
        //去掉输入的特殊字符 ’
        String name = tempName.replaceAll("'", "");
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{name});
        //判断是否有下一个
        return cursor.moveToNext();
    }
}
