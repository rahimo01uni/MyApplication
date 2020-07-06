package com.health.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

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
        Cursor cursor =db.query("Overview",null,"Date=?",new String[]{date},null,null,null);
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
         Calendar date1=Calendar.getInstance();
         date1.setTimeInMillis(Long.parseLong(date));
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NAME", name);
        contentValues.put("DATE", date1.get(Calendar.DATE)+"/"+date1.get(Calendar.MONTH)+"/"+date1.get(Calendar.YEAR));
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
    public  sleep_model get_sleep(String id)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor =db.query("SleepLogs",null,"ID=?",new String[]{id},null,null,null);
        cursor.moveToNext();
        sleep_model item=new sleep_model();
        item.setId(cursor.getString(cursor.getColumnIndex("ID")));
        item.setSleepTime(cursor.getString(cursor.getColumnIndex("STARTTIME")));
        item.setWakeUpTime(cursor.getString(cursor.getColumnIndex("ENDTIME")));
        item.setNote(cursor.getString(cursor.getColumnIndex("NOTES")));
        item.setDuration(cursor.getString(cursor.getColumnIndex("DURATION")));
        item.setQualityOfSleep(cursor.getString(cursor.getColumnIndex("QUALITYOFSLEEP")));
        item.setNightWokeUp(cursor.getString(cursor.getColumnIndex("NIGHTWOKEUP")));
        return item;  }
        public medication_model get_medic(String id)
        {
            Log.d("id",id);
            SQLiteDatabase db = myhelper.getWritableDatabase();
            Cursor cursor =db.query("MedLog",null,"ID=?",new String[]{id},null,null,null);
            cursor.moveToNext();

            medication_model item=new medication_model();
            item.setId(cursor.getString(cursor.getColumnIndex("ID")));
            item.setMed_name(cursor.getString(cursor.getColumnIndex("NAME")));
            item.setDose(cursor.getString(cursor.getColumnIndex("DOSE")));
            item.setUnit(cursor.getString(cursor.getColumnIndex("UNIT")));
            item.setDescription(cursor.getString(cursor.getColumnIndex("NOTES")));
            return item;
        }
        public  symptom_model get_symptom(String id)
        {
            SQLiteDatabase db = myhelper.getWritableDatabase();
            Cursor cursor =db.query("SymptomLog",null,"ID=?",new String[]{id},null,null,null);
            cursor.moveToNext();

            symptom_model item=new symptom_model();
            item.setId(cursor.getString(cursor.getColumnIndex("ID")));
            item.setMood(cursor.getString(cursor.getColumnIndex("MOOD")));
            item.setSymptom(cursor.getString(cursor.getColumnIndex("SYMPTOMS")));
       //  item.setNote(cursor.getString(cursor.getColumnIndex("NOTES")));
            item.setNote("");
            return item;

         }
}
