package com.health.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MedicationDbHelber {
    DatabaseHelper.myDbHelper myhelper;
    public MedicationDbHelber(Context context)
    {
        myhelper = new DatabaseHelper.myDbHelper(context);
    }
    int insertMedReminder(String name, String unit, String doze, String startDate, String endDate, String frequency,String description, ArrayList<String> times)
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


        return id;


    }
}
