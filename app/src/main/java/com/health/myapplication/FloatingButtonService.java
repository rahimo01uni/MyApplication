package com.health.myapplication;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.health.myapplication.bubles.sleep_buble;

/**
 * Created by graphics on 9/22/2016.
 */
public class FloatingButtonService extends Service {


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

        sleep_buble b=new sleep_buble(getApplicationContext(),intent.getStringExtra("wake_up"));
        return super.onStartCommand(intent, flags, startId);
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                return true;
            }
        }
}

