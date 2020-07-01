package com.health.myapplication.Database;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.health.myapplication.MedReciever;
import com.health.myapplication.bubles.symptom_buble;

import java.util.ArrayList;

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
    void setAlarm(String time)
    {
        Intent intent = new Intent(context, symptom_buble.class);
        intent.putExtra("time",time);

        PendingIntent intent1 = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC, Long.parseLong(time),intent1);

    }

}
