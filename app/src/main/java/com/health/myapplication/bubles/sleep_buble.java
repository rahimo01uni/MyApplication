package com.health.myapplication.bubles;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
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
import android.widget.RelativeLayout;

import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.SleepDbHelper;
import com.health.myapplication.Database.sleep_model;
import com.health.myapplication.MainActivity;
import com.health.myapplication.R;
import com.health.myapplication.SleepService;
import com.health.myapplication.splashActivity;

import java.sql.Date;
import java.util.Calendar;

import androidx.annotation.RequiresApi;

public class sleep_buble {
    private WindowManager windowManager;
    private ImageView chatHead;
    private RelativeLayout mRelativeLayout;
    //  private View mRelativeLayout;
    private RelativeLayout mInnerLayout;
    private boolean isLayoutVisible = false;
    private GestureDetector gestureDetector;
    View mView;
    View mView1;
    View mPointer;
    Button mClose,con;
    Boolean sleeping=false;
SleepDbHelper db;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public sleep_buble(Context context, String wake_up_time){
        getmView(context,wake_up_time);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    View getmView(final Context context,String wake_up_time) {
        db=new SleepDbHelper(context);
        gestureDetector = new GestureDetector(new SingleTapConfirm());
        windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

  mRelativeLayout=start_sleep(context,params,wake_up_time);

        windowManager.addView(mRelativeLayout, params);
        return mRelativeLayout;
    }
   RelativeLayout start_sleep(final Context context, final WindowManager.LayoutParams params, final String wake_up_time){
        mRelativeLayout = new RelativeLayout(context);
//
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.setMargins(16, 16, 16, 16);

        mRelativeLayout.setLayoutParams(params);
        //  mRelativeLayout=inflater.inflate(R.layout.floating, null);

        chatHead = new ImageView(context);
        chatHead.setImageResource(R.drawable.launcher);
        chatHead.setId(View.generateViewId());
        chatHead.setElevation(5.0f);
        chatHead.setPadding(20, 16, 16, 16);
        chatHead.setForegroundGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        chatHead.setBackground(context.getResources().getDrawable(R.drawable.shape_circular));

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                150,
                150);
       buttonParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
       buttonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        buttonParams.setMargins(0, 0, 0, 0);

        mRelativeLayout.addView(chatHead, buttonParams);

        final RelativeLayout innerRelativeLayout = new RelativeLayout(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE );
        mView = inflater.inflate(R.layout.sleep, null);

     //   mClose = (Button) mView.findViewById(R.id.buttonClose);
        con=(Button)mView.findViewById(R.id.btn_gosleep);
        con.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!sleeping){
                 Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            windowManager.removeView(mRelativeLayout);
                        }
                    },Long.parseLong(wake_up_time)-Calendar.getInstance().getTimeInMillis());

                    sleeping=true;
                    con.setText("Sleeping");
                    self_log("sleep");
                    chatHead.setImageResource(R.drawable.moon);}
                else {
                   if(Long.parseLong(wake_up_time)>Calendar.getInstance().getTimeInMillis()) new wakeUp_buble(context,"Early wake up");
                   else  new wakeUp_buble(context,"Normal wake up");
                   windowManager.removeView(mRelativeLayout);

                }
                return false;
            }
        });



      /*  mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeView( mRelativeLayout);

            }
        });*/
        innerRelativeLayout.addView(mView,params);


        innerRelativeLayout.setElevation(5.0f);
        innerRelativeLayout.setVisibility(View.GONE);
        RelativeLayout.LayoutParams irlp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        irlp.setMargins(16, 16, 16, 16);
        irlp.addRule(RelativeLayout.BELOW, chatHead.getId());
        Log.d("cht he", "" + chatHead.getId());
        mRelativeLayout.addView(innerRelativeLayout, irlp);

        mRelativeLayout.setLayoutParams(params);
       chatHead.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               Log.d("what","why");

               return false;
           }
       });
        chatHead.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    if(!sleeping){

                    if (isLayoutVisible) {
                        innerRelativeLayout.setVisibility(View.GONE);
                        //   mPointer.setVisibility(View.GONE);
                        isLayoutVisible = false;
                    } else {
                        innerRelativeLayout.setVisibility(View.VISIBLE);
                        //      mPointer.setVisibility(View.VISIBLE);
                        isLayoutVisible = true;
                    }} else {

                        symptom_buble b=new symptom_buble(context,"night");
                        self_log("nightWokeUp");

                    }
                    return true;
                } else {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:
                            initialX = params.x;
                            initialY = params.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            return true;
                        case MotionEvent.ACTION_UP:
                            return true;
                        case MotionEvent.ACTION_MOVE:
                            params.x = initialX + (int) (event.getRawX() - initialTouchX);
                            params.y = initialY + (int) (event.getRawY() - initialTouchY);
                            windowManager.updateViewLayout(mRelativeLayout, params);
                            return true;
                    }
                }
                return false;
            }
        });
        return mRelativeLayout;
    }
   void self_log(String command)
   {

       Calendar sleep,wake;
       sleep_model sleep_time=new sleep_model();
       switch (command){
           case "sleep":
               sleep_time.setSleepTime(""+Calendar.getInstance().getTimeInMillis());
          Log.d("IDSLEEP",""+db.insertSleepLog(sleep_time));
               break;
           case "nightWokeUp":
              db.updateNightWakeUp();
               break;
           case "wake_up":

               break;


       }

     /*  Long x1=Long.parseLong("1593261077883");
      Long x=sleep.getTimeInMillis()-x1;
      Long hour=x/3600000;
      Long minutes=(x/60000)%60;
       Log.d("time",""+hour+" :"+minutes );*/



   }
    static class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }
}