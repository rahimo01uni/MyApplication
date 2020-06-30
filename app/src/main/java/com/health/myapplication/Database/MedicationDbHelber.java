package com.health.myapplication.Database;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.health.myapplication.MedReciever;
import com.health.myapplication.Reminder.AddSleep;
import com.health.myapplication.UnlockReceiver;
import com.health.myapplication.WakeUpReceiver;

import java.util.ArrayList;
import java.util.Calendar;

public class MedicationDbHelber {
    DatabaseHelper.myDbHelper myhelper;
    Context context;
    public MedicationDbHelber(Context context)
    { this.context=context;
        myhelper = new DatabaseHelper.myDbHelper(context);
    }
   public int insertMedReminder(String name, String unit, String doze, String startDate, String endDate, String frequency,String description, ArrayList<String> times)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", name);
        contentValues.put("UNIT", unit);
        contentValues.put("DOZE",doze );
        contentValues.put("STARTDATE",startDate );
        contentValues.put("ENDDATE",endDate );
        contentValues.put("FREQUENCY", frequency);
        contentValues.put("DESCRIPTION", description);
        int id = Integer.parseInt(""+db.insert("MEDREMINDER", null , contentValues));
//"Create table REMINDER(ID INTEGER PRIMARY KEY AUTOINCREMENT,TABLER VARCHAR(100),IDR INTEGER,ALARMTIME VARCHAR(50)" +
//                        ",FREQUENCY VARCHAR(50));";

    for(int i=0; i<times.size();i++)
    {
        contentValues = new ContentValues();

        contentValues.put("IDR", ""+id);
        contentValues.put("ALARMTIME",times.get(i));
        contentValues.put("FREQUENCY",frequency);
         id = Integer.parseInt(""+db.insert("REMINDER", null , contentValues));
        Log.d("whatcomes1",""+id);
     setAlarm(name,doze,unit,times.get(i));
    }
        return id;


    }
    void setAlarm(String name,String doze,String unit,String time)
    {
        Intent intent = new Intent(context, MedReciever.class);
        intent.putExtra("name",""+name);
        intent.putExtra("doze",""+doze);
        intent.putExtra("unit",unit);
        intent.putExtra("time",time);

        PendingIntent intent1 = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC, Long.parseLong(time),intent1);

    }

}
