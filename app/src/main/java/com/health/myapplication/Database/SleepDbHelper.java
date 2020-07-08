package com.health.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;

public class SleepDbHelper {
    final String SleepLogs="Create Table SleepLogs(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE VARCHAR(20),STARTTIME VARCHAR(10),ENDTIME VARCHAR(10),DURATION VARCHAR(100),QUALITYOFSLEEP VARCHAR(20)," +
            "NIGHTWOKEUP VARCHAR(2),NOTES VARCHAR(20));";
    DatabaseHelper.myDbHelper myhelper;
    Context context;
    public SleepDbHelper(Context context)
    { this.context=context;
        myhelper = new DatabaseHelper.myDbHelper(context);
    }

    public void updateSleepLog(String quality,String notes)
    {
        Calendar wake_up=Calendar.getInstance();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor =db.query("SleepLogs",null,null,null,null,null,null);
        cursor.moveToLast();
        if(cursor.getString(cursor.getColumnIndex("ENDTIME"))!=null)
        {
            ContentValues args = new ContentValues();
            args.put("STARTTIME",  "?");
            args.put("ENDTIME",  ""+wake_up.getTimeInMillis());
            args.put("QUALITYOFSLEEP",  quality);
            args.put("DURATION", "?" );
            args.put("DATE",  wake_up.getTimeInMillis());
            args.put("NOTES",  notes);

            db.insert("SleepLogs", null ,args);
        } else {
        int duration=(int)(wake_up.getTimeInMillis()-Long.parseLong(cursor.getString(cursor.getColumnIndex("STARTTIME"))));
        int hour=duration/3600000;
        int minutes=(duration%3600000)/60000;

        //   String strFilter = "ID=" + id;
        String strFilter = "ID=" +cursor.getString(cursor.getColumnIndex("ID")) ;
        ContentValues args = new ContentValues();
        args.put("ENDTIME",  ""+wake_up.getTimeInMillis());
        args.put("QUALITYOFSLEEP",  quality);
        args.put("DURATION",  TimeFormat(hour,minutes));
        args.put("DATE",  wake_up.getTimeInMillis());
        args.put("NOTES",  notes);
        Log.d("WhatsnEWs",""+  db.update("SleepLogs", args, strFilter, null));}
        cursor =db.query("SleepLogs",null,null,null,null,null,null);
        cursor.moveToLast();

        String id=cursor.getString(cursor.getColumnIndex("ID"));
        String name="Sleep";
        String time=""+wake_up.getTimeInMillis();
        String date=""+wake_up.getTimeInMillis();
        String status="1";
        OverviewDbHelper dbo=new OverviewDbHelper(context);

        dbo.insert_overview(id,name,time,date,status);
        GroupDbHelper dbg=new GroupDbHelper(context);
        dbg.insert_LogNames(name);

        Log.d("cursor",""+cursor.getString(cursor.getColumnIndex("DURATION")));
    }
    public int insertSleepReminder(String sleep,String wake_up )
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("SLEEPTIME", sleep);
        contentValues.put("WAKEUPTIME", wake_up);

        int id = Integer.parseInt(""+dbb.insert("SleepReminder", null , contentValues));
        return id;
    }
    public long insertSleepLog(sleep_model Log)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("STARTTIME", Log.getSleepTime());
        contentValues.put("NIGHTWOKEUP", "0");

        long id = dbb.insert("SleepLogs", null , contentValues);

        return id;
    }
    public int updateNightWakeUp()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor =db.query("SleepLogs",null,null,null,null,null,null);
        cursor.moveToLast();
        ;
        //   String strFilter = "ID=" + id;
        String strFilter = "ID=" +cursor.getString(cursor.getColumnIndex("ID")) ;
        ContentValues args = new ContentValues();
        args.put("NIGHTWOKEUP",  (Integer.parseInt(cursor.getString(cursor.getColumnIndex("NIGHTWOKEUP")))+1));

        Log.d("WhatsnEWs",""+  db.update("SleepLogs", args, strFilter, null));
        cursor =db.query("SleepLogs",null,null,null,null,null,null);
        cursor.moveToLast();
        Log.d("HowMANYTIMES",cursor.getString(cursor.getColumnIndex("NIGHTWOKEUP")));
        return 1;
    }
    private  String TimeFormat(int hourOfDay, int minutes){

        String hours=""+hourOfDay,
                minutess=""+minutes;
        if(hours.length()==1) hours="0"+hours;
        if(minutess.length()==1)minutess="0"+minutess;
        return hours+":"+minutess;
    }
}
