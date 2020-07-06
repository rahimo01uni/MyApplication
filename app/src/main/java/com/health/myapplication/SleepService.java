package com.health.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import android.util.Log;

import com.health.myapplication.bubles.sleep_buble;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

/**
 * Created by graphics on 9/22/2016.
 */
public class SleepService extends Service {



        @Override public IBinder onBind(Intent intent) {
            // Not used
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override public void onCreate() {
            super.onCreate();
            Log.v("Service Created","Service Created");




        }

        @Override
        public void onDestroy() {
            super.onDestroy();

        }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

         Long off=  Long.parseLong(intent.getStringExtra("wake_up"))-Calendar.getInstance().getTimeInMillis();

        new sleep_buble(getApplicationContext(),intent.getStringExtra("wake_up"));
        return super.onStartCommand(intent, flags, startId);
    }
}





