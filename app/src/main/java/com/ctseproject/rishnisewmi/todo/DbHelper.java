package com.ctseproject.rishnisewmi.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Rishni on 3/21/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="ToDoDb";
    private static final String TABLE_NAME="Tasks";
    private static final String COLUMN_NAME="Task";
    private static final int DB_VERSION=1;

    private SQLiteDatabase database;

    public DbHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);",TABLE_NAME,COLUMN_NAME);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query=String.format("DELETE TABLE IF EXISTS %s",TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertNewTask(String item)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NAME,item);

        Log.d("dbhelper","add data"+item+"to"+TABLE_NAME);

        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }


    public ArrayList<String> getToDoList()
    {
        ArrayList<String> todoList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(TABLE_NAME,new String[]{COLUMN_NAME},null,null,null,null,null);
        while (c.moveToNext())
        {
            int index=c.getColumnIndex(COLUMN_NAME);
            todoList.add(c.getString(index));
        }
        c.close();
        db.close();
        return todoList;
    }
}
