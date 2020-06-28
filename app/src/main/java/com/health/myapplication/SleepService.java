package com.health.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import android.util.Log;

import com.health.myapplication.bubles.sleep_buble;

import androidx.annotation.RequiresApi;

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

new sleep_buble(getApplicationContext(),"");


        }

        @Override
        public void onDestroy() {
            super.onDestroy();

        }


        class  layout{

            layout(){

            }

        }

        }





