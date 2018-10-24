package com.cdivtc.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private Button button;
    private Button addData;
    private Button updateData;
    private Button deleteData;
    private Button queryData;
    private  static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);
        button = findViewById(R.id.create_database);
        addData = findViewById(R.id.add_data);
        updateData = findViewById(R.id.update_data);
        deleteData = findViewById(R.id.delete_data);
        queryData = findViewById(R.id.query_data);
    }

    public void createDB(View view) {
        dbHelper.getWritableDatabase();

    }

    public void addData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //实例化一个ContentValues方法来对要添加的数据进行组装
        ContentValues values = new ContentValues();
        //此处不需要添加id，因为id是自增长类型数据
        //开始第一组数据
        values.put("name","The Da Vinci Code");
        values.put("author","Dan Brown");
        values.put("pages",454);
        values.put("price",16.96);
        db.insert("Book",null,values);
        values.clear();

        //开始第二组数据
        values.put("name","The Lost Symbol");
        values.put("author","Dan Brown");
        values.put("pages",510);
        values.put("price",19.95);
        db.insert("Book",null,values);
        //db.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",new String[] {"The Da Vinci Code","Dan Brown","454","16.96"});

    }

    public void updateData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price",10.99);
        db.update("Book",values,"name = ?",new String[]{"The Da Vince Code"});
        //db.execSQL("update Book set price = ? where name = ?",new String[] {"10.99","The Da Vinci Code"});
    }

    public void deleteData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Book","pages > ?",new String[]{"500"});
        //db.execSQL("delete from Book where pages > ?",new String[]{"500"});
    }

    public void queryData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //查询Book表中的所有数据
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                //遍历Cursor对象，取出数据并打印
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d(TAG, "book name is "+name);
                Log.d(TAG, "book author is "+author);
                Log.d(TAG, "book pages is "+pages);
                Log.d(TAG, "book price is "+price);
            }while(cursor.moveToNext());
        }
        cursor.close();
       // db.rawQuery("select * from Book",null);
    }
}
