package com.health.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.bubles.med_bubble;
import com.health.myapplication.bubles.sleep_buble;

import java.util.Calendar;

import androidx.annotation.RequiresApi;

public class MedReciever extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
      /*  DatabaseHelper db=new DatabaseHelper(context);
        Calendar updateTime=Calendar.getInstance();
        updateTime.setTimeInMillis(Long.parseLong(intent.getStringExtra("sleep")));
        Log.d("Unchanged",""+updateTime.getTime());
        updateTime.set(updateTime.get(Calendar.YEAR), updateTime.get(Calendar.MONTH), updateTime.get(Calendar.DATE)+1);
        Log.d("changed",""+updateTime.getTime());
        db.nextDateAlarm(intent.getStringExtra("id"),"sleep",""+updateTime.getTimeInMillis());*/
        Log.v("Inside Receiver", "Starting service");
        new med_bubble(context,"");


    }
}