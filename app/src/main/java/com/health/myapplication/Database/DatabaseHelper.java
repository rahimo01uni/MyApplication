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

                final String OVERVIEW = "CREATE TABLE OVERVIEW( ID INTEGER ,NAME VARCHAR(200),TIME VARCHAR(50),DATE VARCHAR(50),STATUS VARCHAR(2));";
                //Id, SleepTime,WakeUpTime,Date,Duration,QualityOfSleep,NightWokeUp, Note;
                final String SleepReminder="Create Table SleepReminder(ID INTEGER PRIMARY KEY AUTOINCREMENT,SLEEPTIME VARCHAR(100)," +
                        "WAKEUPTIME VARCHAR(100));";
                final String SleepLogs="Create Table SleepLogs(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE VARCHAR(20),STARTTIME VARCHAR(10),ENDTIME VARCHAR(10),DURATION VARCHAR(100),QUALITYOFSLEEP VARCHAR(20)," +
                        "NIGHTWOKEUP VARCHAR(2),NOTES VARCHAR(20));";

                final String ReminderTimes="Create table REMINDER(ID INTEGER PRIMARY KEY AUTOINCREMENT,IDR INTEGER,ALARMTIME VARCHAR(50)" +
                        ",FREQUENCY VARCHAR(50));";
            final String ReminderSymptom="Create table REMINDERSYMPTOM(ID INTEGER PRIMARY KEY AUTOINCREMENT,IDR INTEGER,ALARMTIME VARCHAR(50)" +
                    ",FREQUENCY VARCHAR(50));";
                final String MedicationReminder="CREATE TABLE MEDREMINDER(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(250),UNIT VARCHAR(50)," +
                        "DOZE VARCHAR(50),STARTDATE VARCHAR(50),ENDDATE VARCHAR(50),FREQUENCY VARCHAR(50),DESCRIPTION VARCHAR(1000));";
                final String DROP_TABLE_1="DROP TABLE IF EXISTS SleepReminder";
                final String DROP_TABLE_2="DROP TABLE IF EXISTS  SleepLogs";
                final String DROP_TABLE_3="DROP TABLE IF EXISTS REMINDER";
                final String DROP_TABLE_4="DROP TABLE IF EXISTS MEDREMINDER";
                final String DROP_TABLE_5="DROP TABLE IF EXISTS REMINDERSYMPTOM";
            final String DROP_TABLE_6="DROP TABLE IF EXISTS OVERVIEW";


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
                    db.execSQL(MedicationReminder);
                    db.execSQL(ReminderSymptom);
                    db.execSQL(OVERVIEW);

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
                    db.execSQL(DROP_TABLE_6);
                    onCreate(db);
                }catch (Exception e) {

                }
            }
        }
    }

