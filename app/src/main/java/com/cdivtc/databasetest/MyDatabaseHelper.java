package com.cdivtc.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 28461 on 2018/10/21.
 */

class MyDatabaseHelper extends SQLiteOpenHelper{
    //定义创建表的字符串常量
    public static final String CREATE_BOOK = "create table Book ("
            +"id integer primary key autoincrement, "
            +"author text, "
            +"price real, "
            +"pages integer, "
            +"name text)";
    public static final String CREATE_CATEGORY ="create table Category ("
            +"id integer primary key autoincrement, "
            +"category_name text, "
            +"category_code integer)";
    private Context mContext;

    //构造方法
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //调用并执行创建book表的语句
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        //提示消息
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
