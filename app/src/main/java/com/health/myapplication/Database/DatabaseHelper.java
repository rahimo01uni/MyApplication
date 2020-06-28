package com.health.myapplication.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.Calendar;


public    class DatabaseHelper {
        myDbHelper myhelper;
        public DatabaseHelper(Context context)
        {
            myhelper = new myDbHelper(context);
        }
        public void nextDateAlarm(String id,String updateType,String updateValue)
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
           switch (updateType)
           { case "sleep":
                String strFilter = "ID=" + id;
               ContentValues args = new ContentValues();
               args.put("SLEEPTIME", updateValue);
               db.update("SleepReminder", args, strFilter, null);

               break;
               case "wake_up":
                   strFilter = "ID=" + id;
                   args = new ContentValues();
                   args.put("WAKEUPTIME", updateValue);
                   db.update("SleepReminder", args, strFilter, null);

                   break;
               default: break;
           }
        }
        public void updateSleepLog(String quality)
        {
            Calendar wake_up=Calendar.getInstance();



            SQLiteDatabase db = myhelper.getWritableDatabase();
            Cursor cursor =db.query("SleepLogs",null,null,null,null,null,null);
            cursor.moveToLast();
            int duration=(int)(wake_up.getTimeInMillis()-Long.parseLong(cursor.getString(cursor.getColumnIndex("STARTTIME"))));
            int hour=duration/3600000;
            int minutes=(duration%3600000)/60000;

            //   String strFilter = "ID=" + id;
            String strFilter = "ID=" +cursor.getString(cursor.getColumnIndex("ID")) ;
            ContentValues args = new ContentValues();
            args.put("ENDTIME",  ""+wake_up.getTimeInMillis());
            args.put("QUALITYOFSLEEP",  quality);
            args.put("DURATION",  ""+hour+":"+minutes);
            args.put("DATE",  wake_up.getTimeInMillis());
            Log.d("WhatsnEWs",""+  db.update("SleepLogs", args, strFilter, null));
            cursor =db.query("SleepLogs",null,null,null,null,null,null);
            cursor.moveToLast();
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
       public ArrayList<general_model> getReminders()
       {
           ArrayList<general_model> list=new ArrayList<>();
           SQLiteDatabase db = myhelper.getWritableDatabase();
           //SleepLogs
           sleep_model sleep_log;
           Cursor cursor =db.query("SleepReminder",null,null,null,null,null,null);
           while (cursor.moveToNext())

           {          sleep_log=new sleep_model();
               sleep_log.setId(cursor.getString(cursor.getColumnIndex("ID")));
               sleep_log.setSleepTime(cursor.getString(cursor.getColumnIndex("SLEEPTIME")));
               sleep_log.setWakeUpTime(cursor.getString(cursor.getColumnIndex("WAKEUPTIME")));
               list.add(new general_model("sleep",sleep_log.getTime(),sleep_log));
           }
           return list;
       }
    public boolean EditSleepReminder(String id, String sleep_time, String wakeUp) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String strFilter = "ID=" + id;
        ContentValues args = new ContentValues();
        args.put("SLEEPTIME", sleep_time);
        args.put("WAKEUPTIME", wakeUp);
        db.update("SleepReminder", args, strFilter, null);
        return true;
    }

        public ArrayList<general_model> getData(String Date)
        {
            ArrayList<general_model> list=new ArrayList<>();
            SQLiteDatabase db = myhelper.getWritableDatabase();
            //SleepLogs
            Cursor cursor =db.query("SleepLog",null,"Date",new String[]{Date},null,null,null);
             sleep_model sleep_log;
            while (cursor.moveToNext())
            {          sleep_log=new sleep_model();
                       sleep_log.setId(cursor.getString(cursor.getColumnIndex("ID")));
                       sleep_log.setDate(cursor.getString(cursor.getColumnIndex("QUALITYOFSLEEP")));
                sleep_log.setDuration(cursor.getString(cursor.getColumnIndex("DURATION")));
                sleep_log.setNightWokeUp(cursor.getString(cursor.getColumnIndex("NIGHTWOKEUP")));
                sleep_log.setNote(cursor.getString(cursor.getColumnIndex("NOTES ")));
                sleep_log.setTime(cursor.getString(cursor.getColumnIndex("TIME ")));
                 list.add(new general_model("sleep",sleep_log.getTime(),sleep_log));
            }
            return list;
        }
    public  ArrayList<general_model> bubble_srt(ArrayList<general_model> List) {
        int n = List.size();
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (List.get(i).getTime().compareTo(List.get(k).getTime())>0) {
                    swapNumbers(i, k, List);
                }
            }
        }
        return List;
    }

    private static void swapNumbers(int i, int j, ArrayList<general_model> List) {

        general_model temp;
        temp = List.get(i);
        List.set(i,List.get(j));
        List.set(j,temp);

    }
/*

        public  int delete(String uname)
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] whereArgs ={uname};

            int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
            return  count;
        }

        public int updateName(String oldName , String newName)
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME,newName);
            String[] whereArgs= {oldName};
            int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
            return count;
        }
*/
        static class myDbHelper extends SQLiteOpenHelper
        {
            private static final String DATABASE_NAME = "Health";    // DatabaseHelper Name
            private static final int DATABASE_Version = 2;    // DatabaseHelper Version
            //Tables
            //People
                final String People = "CREATE TABLE PEOPLE(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " GROUPS VARCHAR(100) ,NAME VARCHAR(225),EMAIL VARCHAR(225));";
                //GROUP
                final String Groups = "CREATE TABLE  GROUPS(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(255));";
                //REMINDER

                final String Reminder = "CREATE TABLE REMINDER( ID INTEGER PRIMARY KEY AUTOINCREMENT, CATEGORY VARCHAR(255) ,NAME VARCHAR(225)," +
                        "   STATUS VARCHAR(2), FREQUENCY VARCHAR(255),STARTDATE VARCHAR(20), ENDDATE VARCHAR(20),TIME VARCHAR(10) );";
                //Id, SleepTime,WakeUpTime,Date,Duration,QualityOfSleep,NightWokeUp, Note;
                final String SleepReminder="Create Table SleepReminder(ID INTEGER PRIMARY KEY AUTOINCREMENT,SLEEPTIME VARCHAR(100)," +
                        "WAKEUPTIME VARCHAR(100));";
                final String SleepLogs="Create Table SleepLogs(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE VARCHAR(20),STARTTIME VARCHAR(10),ENDTIME VARCHAR(10),DURATION VARCHAR(100),QUALITYOFSLEEP VARCHAR(20)," +
                        "NIGHTWOKEUP VARCHAR(2),NOTES VARCHAR(20));";

                final String ReminderTimes="Create table REMINDER(ID INTEGER PRIMARY KEY AUTOINCREMENT,TABLER VARCHAR(100),IDR INTEGER,ALARMTIME VARCHAR(50)" +
                        ",FREQUENCY VARCHAR(50));";

                final String MedicationReminder="CREATE TABLE MEDREMINDER(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(250),UNIT VARCHAR(50)," +
                        "DOZE VARCHAR(50),STARTDATE VARCHAR(50),ENDDATE VARCHAR(50),FREQUENCY VARCHAR(50),DESCRIPTION VARCHAR(1000);";
                final String DROP_TABLE_1="DROP TABLE IF EXISTS SleepReminder";
                final String DROP_TABLE_2="DROP TABLE IF EXISTS  SleepLogs";
                final String DROP_TABLE_3="DROP TABLE IF EXISTS REMINDER";
                final String DROP_TABLE_4="DROP TABLE IF EXISTS NOTES";
                final String DROP_TABLE_5="DROP TABLE IF EXISTS SUBCATEGORIES";



            private Context context;

            public myDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_Version);
                this.context=context;
            }

            public void onCreate(SQLiteDatabase db) {

                try {
                    db.execSQL(SleepReminder);
                    db.execSQL(SleepLogs);
                    db.execSQL(ReminderTimes);

                } catch (Exception e) {

                }

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {

                    db.execSQL(DROP_TABLE_1);
                    db.execSQL(DROP_TABLE_2);
                    db.execSQL(DROP_TABLE_3);
                    db.execSQL(DROP_TABLE_4);
                    db.execSQL(DROP_TABLE_5);
                    onCreate(db);
                }catch (Exception e) {

                }
            }
        }
    }

