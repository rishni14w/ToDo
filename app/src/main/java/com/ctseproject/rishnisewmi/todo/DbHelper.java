package com.ctseproject.rishnisewmi.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Rishni on 3/21/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="ToDoDatabase";
    private static final String TABLE_NAME="Tasks";
    private static final String COLUMN_ONE="Task";
    private static final String COLUMN_TWO="Description";
    public static final String COLUMN_THREE="Date";
    private static final int DB_VERSION=1;

    private SQLiteDatabase database;

    public DbHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL,%s TEXT,%s TEXT);",TABLE_NAME,COLUMN_ONE,COLUMN_TWO,COLUMN_THREE);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query=String.format("DELETE TABLE IF EXISTS %s",TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertNewTask(String item,String desc,String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_ONE,item);
        contentValues.put(COLUMN_TWO,desc);
        contentValues.put(COLUMN_THREE,date);

        Log.d("dbhelper","add data"+item+"to"+TABLE_NAME);

        long result=db.insertWithOnConflict(TABLE_NAME,null,contentValues,db.CONFLICT_REPLACE);

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
        Cursor c=db.query(TABLE_NAME,new String[]{COLUMN_ONE},null,null,null,null,null);
        if(c.moveToLast())
        {
            do{
                int index=c.getColumnIndex(COLUMN_ONE);
                todoList.add(c.getString(index));
            }
            while (c.moveToPrevious());

        }
        c.close();
        db.close();
        return todoList;
    }

    public Cursor getListContents()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return data;
    }


}
