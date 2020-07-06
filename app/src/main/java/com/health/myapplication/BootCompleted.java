package com.health.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Reminder.AddSleep;

import java.util.Calendar;

public class BootCompleted extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i1=new Intent(context,FloatingButtonService.class);
        i1.putExtra("wake_up",intent.getStringExtra("wake_up"));
        context.startService(i1);



    }
}