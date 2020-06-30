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

import java.util.ArrayList;

public class ReminderOverviewDbHelper {
    DatabaseHelper.myDbHelper myhelper;
    Context context;
    public ReminderOverviewDbHelper(Context context)
    { this.context=context;
        myhelper = new DatabaseHelper.myDbHelper(context);
    }
    public ArrayList<general_model> getReminders()
    {
        ArrayList<general_model> list=new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        //SleepReminders
        sleep_model sleep_log;
        Cursor cursor =db.query("SleepReminder",null,null,null,null,null,null);
        while (cursor.moveToNext())

        {          sleep_log=new sleep_model();
            sleep_log.setId(cursor.getString(cursor.getColumnIndex("ID")));
            sleep_log.setSleepTime(cursor.getString(cursor.getColumnIndex("SLEEPTIME")));
            sleep_log.setWakeUpTime(cursor.getString(cursor.getColumnIndex("WAKEUPTIME")));
            list.add(new general_model("sleep",sleep_log.getTime(),sleep_log));
        }

        final String ReminderTimes="Create table REMINDER(ID INTEGER PRIMARY KEY AUTOINCREMENT,IDR INTEGER,ALARMTIME VARCHAR(50)" +
                ",FREQUENCY VARCHAR(50));";

        final String MedicationReminder="CREATE TABLE MEDREMINDER(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(250),UNIT VARCHAR(50)," +
                "DOZE VARCHAR(50),STARTDATE VARCHAR(50),ENDDATE VARCHAR(50),FREQUENCY VARCHAR(50),DESCRIPTION VARCHAR(1000));";
       //MedReminders
         cursor =db.query("MEDREMINDER",null,null,null,null,null,null);
       medication_model med;
        while (cursor.moveToNext())

        {          med=new medication_model();
            med.setId(cursor.getString(cursor.getColumnIndex("ID")));
          med.setMed_name(cursor.getString(cursor.getColumnIndex("NAME")));
          med.setUnit(cursor.getString(cursor.getColumnIndex("UNIT")));
          med.setDose(cursor.getString(cursor.getColumnIndex("DOZE")));
          med.setStartDate(cursor.getString(cursor.getColumnIndex("STARTDATE")));
          med.setEndDate(cursor.getString(cursor.getColumnIndex("ENDDATE")));
          med.setFrequency(cursor.getString(cursor.getColumnIndex("FREQUENCY")));
          med.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
            Log.d("medID",med.getId());
          ArrayList<String> times=new ArrayList<>();
            Cursor cursor1 =db.query("Reminder",null,"IDR=?",new String[]{med.getId()},null,null,null);
            while (cursor1.moveToNext()){
                times.add(cursor1.getString(cursor1.getColumnIndex("ALARMTIME")));
            }
           med.setTimes(times);
            list.add(new general_model(med.getMed_name(),med.getTime(),med));
        }
        /*symptom_model symptom;
        while (cursor.moveToNext())

        {          med=new medication_model();
            med.setId(cursor.getString(cursor.getColumnIndex("ID")));
            med.setMed_name(cursor.getString(cursor.getColumnIndex("NAME")));
            med.setUnit(cursor.getString(cursor.getColumnIndex("UNIT")));
            med.setDose(cursor.getString(cursor.getColumnIndex("DOZE")));
            med.setStartDate(cursor.getString(cursor.getColumnIndex("STARTDATE")));
            med.setEndDate(cursor.getString(cursor.getColumnIndex("ENDDATE")));
            med.setFrequency(cursor.getString(cursor.getColumnIndex("FREQUENCY")));
            med.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
            ArrayList<String> times=new ArrayList<>();
            Cursor cursor1 =db.query("Reminder",null,"IDR=",new String[]{med.getId()},null,null,null);
            while (cursor1.moveToNext()){
                times.add(cursor.getString(cursor.getColumnIndex("ALARMTIME")));
            }
            med.setTimes(times);
            list.add(new general_model("medication",med.getTime(),med));
        }*/
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
    public int EditMedReminder(String id,String name, String unit, String doze, String startDate, String endDate, String frequency,String description, ArrayList<String> times)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String strFilter = "ID=" + id;
        contentValues.put("NAME", name);
        contentValues.put("UNIT", unit);
        contentValues.put("DOZE",doze );
        contentValues.put("STARTDATE",startDate );
        contentValues.put("ENDDATE",endDate );
        contentValues.put("FREQUENCY", frequency);
        contentValues.put("DESCRIPTION", description);
        db.update("MEDREMINDER", contentValues, strFilter, null);
        Log.d("how","is");
//"Create table REMINDER(ID INTEGER PRIMARY KEY AUTOINCREMENT,TABLER VARCHAR(100),IDR INTEGER,ALARMTIME VARCHAR(50)" +
//
//                       ",FREQUENCY VARCHAR(50));";
        delete(id);
        for(int i=0; i<times.size();i++)
        {
            contentValues = new ContentValues();

            contentValues.put("IDR", ""+id);
            contentValues.put("ALARMTIME",times.get(i));
            contentValues.put("FREQUENCY",frequency);
            db.insert("REMINDER", null , contentValues);
            Log.d("whatcomes1",""+id);
            setAlarm(name,doze,unit,times.get(i));
        }
        return 0;


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
    public  int delete(String id)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={id};

        int count =db.delete("REMINDER" ,"IDR = ?",whereArgs);
        return  count;
    }
}
