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

import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Reminder.AddSleep;
import com.health.myapplication.bubles.sleep_buble;

import java.util.Calendar;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class UnlockReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        Intent intent1 = new Intent(context, AddSleep.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(AddSleep.class);
        taskStackBuilder.addNextIntent(intent1);

        PendingIntent intent2 = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("my_channel_01","hello", NotificationManager.IMPORTANCE_HIGH);
        }

        Notification notification = builder.setContentTitle("Reminder")
                .setContentText(intent.getStringExtra("Message")).setAutoCancel(true)
                .setSound(alarmsound).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(intent2)
                .setChannelId("my_channel_01")
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(1, notification);












        DatabaseHelper db=new DatabaseHelper(context);
        Calendar updateTime=Calendar.getInstance();
        updateTime.setTimeInMillis(Long.parseLong(intent.getStringExtra("sleep")));
        Log.d("Unchanged",""+updateTime.getTime());
        updateTime.set(updateTime.get(Calendar.YEAR), updateTime.get(Calendar.MONTH), updateTime.get(Calendar.DATE)+1);
        Log.d("changed",""+updateTime.getTime());
        db.nextDateAlarm(intent.getStringExtra("id"),"sleep",""+updateTime.getTimeInMillis());
        Log.v("Inside Receiver", "Starting service");
        Intent i1=new Intent(context,FloatingButtonService.class);
        i1.putExtra("wake_up",intent.getStringExtra("wake_up"));
        context.startService(i1);


    }
}