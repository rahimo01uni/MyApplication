package com.health.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;

import com.health.myapplication.TeamActivity.MemList.add_logMem_model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GroupDbHelper {
    static  DatabaseHelper.myDbHelper myhelper;
    Context context;
    public GroupDbHelper(Context context)
    { this.context=context;
        myhelper = new DatabaseHelper.myDbHelper(context);
    }
   public void insertMember(String email,String name)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("EMAIL", email);
        contentValues.put("FREQUENCY", "");
        contentValues.put("STARTDATE", "");
        contentValues.put("NEXTDATE", "");
        int id = Integer.parseInt(""+db.insert("people", null , contentValues));
        Log.d("id",""+id);
    }
    public ArrayList<member_model> get_members()
    { ArrayList<member_model> List=new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        //SleepReminders

        Cursor cursor =db.query("people",null,null,null,null,null,null);
       member_model item;
        while (cursor.moveToNext())
        {
            item=new member_model();
            item.setId(cursor.getString(cursor.getColumnIndex("ID")));
            item.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            item.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            List.add(item);
        }
        return List;
    }
    public void insert_LogNames(String Name)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", Name);
        int id = Integer.parseInt(""+db.insert("Log_name", null , contentValues));
    }

    public  ArrayList<add_logMem_model> get_LogNames()
    {
        ArrayList<add_logMem_model> list =new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor =db.query("Log_name",null,null,null,null,null,null);
       add_logMem_model item;
        while (cursor.moveToNext())
        {
            item=new add_logMem_model();
            item.setId(cursor.getString(cursor.getColumnIndex("ID")));
            item.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            list.add(item);
        }
        return  list;
    }

    public void insert_LogSelection(String id, ArrayList<add_logMem_model> list)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(add_logMem_model item:list){
            if(item.isChecked()) {
                Log.d("Checked",item.getName());
                contentValues.put("MEMBERID", id);
                contentValues.put("NAME", item.getName());
                int idr = Integer.parseInt("" + db.insert("SELECTIONS", null, contentValues));
                Log.d("inserted?",""+idr);
            }
        }

    }

    public void delete_selection(String id)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={id};

        int count =db.delete("SELECTIONS" ,"ID = ?",whereArgs);
    }
    public ArrayList<selections_model> get_selections(String id){
        ArrayList<selections_model>list=new ArrayList<>();
 Log.d("what",id);
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor =db.query("Selections",null,"MEMBERID=?",new String[]{id},null,null,null);
    selections_model item;
        while (cursor.moveToNext())
        {   item=new selections_model();
            item.setId(cursor.getString(cursor.getColumnIndex("ID")));
            item.setMemberId(cursor.getString(cursor.getColumnIndex("MEMBERID")));
            item.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            item.setChecked(false);
            list.add(item);
        }
        return  list;
    }

    public void update_frequency(String id,String frequency,String date,String nextDate)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String strFilter = "ID=" +id ;
        ContentValues args = new ContentValues();
        args.put("FREQUENCY", frequency);
        args.put("STARTDATE",date);
        args.put("NEXTDATE",nextDate);

        Log.d("WhatsnEWs",""+  db.update("people", args, strFilter, null));
    }
    public  member_model get_info(String id)
    { member_model member=new member_model();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor =db.query("people",null,"ID=?",new String[]{id},null,null,null);
        cursor.moveToNext();

        member.setId(cursor.getString(cursor.getColumnIndex("ID")));
        member.setName(cursor.getString(cursor.getColumnIndex("NAME")));
        member.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
        member.setFrequency(cursor.getString(cursor.getColumnIndex("FREQUENCY")));
        member.setStartDate(cursor.getString(cursor.getColumnIndex("STARTDATE")));
        member.setNextdate(cursor.getString(cursor.getColumnIndex("NEXTDATE")));
        return member;

    }
    public void sender_db(String from,String till, ArrayList<selections_model> list)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor;
        ArrayList<send_model> send=new ArrayList<>();
        ArrayList<symptom_model> l1=new ArrayList<>();
        ArrayList<medication_model> l2=new ArrayList<>();
        ArrayList<sleep_model> l3=new ArrayList<>();
        //ArrayList<selections_model>list=new ArrayList<>();
        for (selections_model item:list)
        {
             switch (item.getName())
             {
                 case "Mood":
                     symptom_model s_model;
                       cursor=db.query("SymptomLog",null,"DATE>=? AND DATE<=? ",new String[]{from,till},null,null,null);
                     while (cursor.moveToNext()){
                       s_model=new symptom_model();
                     s_model.setId(cursor.getString(cursor.getColumnIndex("ID")));
                     s_model.setMood(cursor.getString(cursor.getColumnIndex("MOOD")));
                     s_model.setSymptom(cursor.getString(cursor.getColumnIndex("SYMPTOMS")));
                     //  item.setNote(cursor.getString(cursor.getColumnIndex("NOTES")));
                     s_model.setNote("");
                     s_model.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
                      l1.add(s_model);
                     }
                     send_model s= new send_model();
                     s.setName(item.getName());
                     s.setSymptom(l1);
                     send.add(s);
                     break;
                 case "Sleep":
                     cursor=db.query("SleepLog",null,"DATE>=? AND DATE<=? ",new String[]{from,till},null,null,null);
                     while (cursor.moveToNext()){
                        sleep_model s1_model=new sleep_model();
                         s1_model.setId(cursor.getString(cursor.getColumnIndex("ID")));
                         s1_model.setDuration(cursor.getString(cursor.getColumnIndex("DURATION")));
                         s1_model.setQualityOfSleep(cursor.getString(cursor.getColumnIndex("QUALITYOFSLEEP")));
                         s1_model.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
                         s1_model.setSleepTime(cursor.getString(cursor.getColumnIndex("STARTTIME")));
                         s1_model.setWakeUpTime(cursor.getString(cursor.getColumnIndex("ENDTIME")));
                         s1_model.setNightWokeUp(cursor.getString(cursor.getColumnIndex("NIGHTWOKEUP")));
                         //  item.setNote(cursor.getString(cursor.getColumnIndex("NOTES")));
                         s1_model.setNote(cursor.getString(cursor.getColumnIndex("NIGHTWOKEUP")));
                         l3.add(s1_model);}
                      s= new send_model();
                     s.setName(item.getName());
                     s.setSleep(l3);
                     send.add(s);
                     break;
                     default:
                         cursor=db.query("MedLog",null,"NAME=? AND DATE>=? AND DATE<=? ",new String[]{item.getName(),from,till},null,null,null);
                         while (cursor.moveToNext()){
                             medication_model item1=new medication_model();
                             item1.setId(cursor.getString(cursor.getColumnIndex("ID")));
                             item1.setMed_name(cursor.getString(cursor.getColumnIndex("NAME")));
                             item1.setDose(cursor.getString(cursor.getColumnIndex("DOSE")));
                             item1.setUnit(cursor.getString(cursor.getColumnIndex("UNIT")));
                             item1.setDescription(cursor.getString(cursor.getColumnIndex("NOTES")));
                             l2.add(item1);}
                         s= new send_model();
                         s.setName(item.getName());
                         s.setMed(l2);
                         send.add(s);
                         break;
             }

        }
          selections_model item;
    }


}
