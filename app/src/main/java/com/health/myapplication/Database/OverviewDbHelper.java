package com.health.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class OverviewDbHelper {

    final String SleepLogs="Create Table SleepLogs(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE VARCHAR(20),STARTTIME VARCHAR(10),ENDTIME VARCHAR(10),DURATION VARCHAR(100),QUALITYOFSLEEP VARCHAR(20)," +
            "NIGHTWOKEUP VARCHAR(2),NOTES VARCHAR(20));";
    static  DatabaseHelper.myDbHelper myhelper;
    Context context;
    public OverviewDbHelper(Context context)
    { this.context=context;
        myhelper = new DatabaseHelper.myDbHelper(context);
    }
    public ArrayList<model_overview> get_overview(String date)
    { ArrayList<model_overview> list=new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        //SleepReminders
        model_overview item;
        Cursor cursor =db.query("Overview",null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            item=new model_overview();
            item.setId(cursor.getString(cursor.getColumnIndex("ID")));
            item.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            item.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("STATUS")));
            item.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
            list.add(item);
        }

        return  list;
    }
    public  static  void insert_overview(String id,String name,String time,String date,String status)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NAME", name);
        contentValues.put("DATE", date);
        contentValues.put("TIME", time);
        contentValues.put("STATUS", status);

        Integer.parseInt(""+db.insert("OVERVIEW", null , contentValues));
    }
    public  static void  status_update(String id, String time, String status)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String strFilter = "ID=" + id+",TIME="+time;
        ContentValues args = new ContentValues();
        args.put("STATUS", status);
        db.update("OVERVIEW", args, strFilter, null);
    }
}
