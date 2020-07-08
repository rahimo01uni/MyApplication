package com.health.myapplication.Database;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.health.myapplication.MedReciever;
import com.health.myapplication.SymptomReciever;
import com.health.myapplication.bubles.symptom_buble;

import java.util.ArrayList;
import java.util.Calendar;

public class SymptomDbHelper {
    DatabaseHelper.myDbHelper myhelper;
    Context context;
    public  SymptomDbHelper(Context context)
    { this.context=context;
        myhelper = new DatabaseHelper.myDbHelper(context);
    }
    public int insertSymptomReminder(ArrayList<String> times)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int    id=0;
        for(int i=0; i<times.size();i++)
        {
            contentValues = new ContentValues();
            contentValues.put("ALARMTIME",times.get(i));
            contentValues.put("FREQUENCY",times.get(i));
         id = Integer.parseInt(""+db.insert("REMINDERSYMPTOM", null , contentValues));
            Log.d("whatcomes1",""+id);
            setAlarm(times.get(i));
        }
        return id;


    }
    public ArrayList<selections_model> getSymptoms(){
        ArrayList<selections_model> list=new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        //SleepReminders
        selections_model item;
        Cursor cursor =db.query("Symptoms",null,null,null,null,null,"NAME");
        while (cursor.moveToNext())
        {
            item=new selections_model();
            item.setId(cursor.getString(cursor.getColumnIndex("ID")));
            item.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            list.add(item);
        }
        return list;
    }
    static int id=0;
    public void  insertSym(ArrayList<String> list)
    {  list.add("headache");
        list.add("madache");
        list.add("bbdache");
        list.add("sbdache");
        list.add("gbdache");
        list.add("dbdache");
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for(int i=0; i<list.size();i++)
        {
            contentValues = new ContentValues();
            contentValues.put("NAME",list.get(i));
            contentValues.put("USED",0);
       if(id<7)     id = Integer.parseInt(""+db.insert("SYMPTOMS", null , contentValues));

        }

    }
    public  void insertSymptomLog(String mood,String notes,String symptoms)
    {
        Calendar ti=Calendar.getInstance();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int    id=0;
        String sym="";

        contentValues.put("MOOD",mood);
        contentValues.put("NOTES",notes);
        contentValues.put("SYMPTOMS",symptoms);
        id = Integer.parseInt(""+db.insert("SymptomLog", null , contentValues));
        OverviewDbHelper dbHelper=new OverviewDbHelper(context);
        dbHelper.insert_overview(""+id,"Mood",""+ti.getTimeInMillis(),""+ti.getTimeInMillis(),"1");
        GroupDbHelper dbg=new GroupDbHelper(context);
        dbg.insert_LogNames("MOOD");

    }
    void setAlarm(String time)
    {
        Intent intent = new Intent(context, SymptomReciever.class);
        intent.putExtra("time",time);

        PendingIntent intent1 = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC, Long.parseLong(time),intent1);

    }

}
