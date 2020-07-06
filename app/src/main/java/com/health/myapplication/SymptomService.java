package com.health.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.health.myapplication.bubles.sleep_buble;
import com.health.myapplication.bubles.symptom_buble;

/**
 * Created by graphics on 9/22/2016.
 */
public class SymptomService extends Service {


        @Override public IBinder onBind(Intent intent) {
            // Not used
           Log.d("what",intent.getStringExtra("wake_up"));
            return null;
        }

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

new symptom_buble(getApplicationContext(),"");
        return super.onStartCommand(intent, flags, startId);
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                return true;
            }
        }
}

