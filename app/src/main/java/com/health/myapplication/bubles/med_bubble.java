package com.health.myapplication.bubles;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.MedicationDbHelber;
import com.health.myapplication.Database.sleep_model;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.AddReminderActivity;

import java.util.Calendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;

public class med_bubble {
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
 MedicationDbHelber db;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public med_bubble(Context context, Intent intent){
        getmView(context,intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    View getmView(final Context context,Intent intent) {
        db=new MedicationDbHelber(context);
        gestureDetector = new GestureDetector( new sleep_buble.SingleTapConfirm());
        windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.x=-558;
        params.y=300;
  mRelativeLayout=check_symptom_wakeUp(context,params,intent);

        windowManager.addView(mRelativeLayout, params);
        return mRelativeLayout;
    }
   RelativeLayout check_symptom_wakeUp(Context context,final WindowManager.LayoutParams params,Intent intent){
        mRelativeLayout = new RelativeLayout(context);
//
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.setMargins(16, 16, 16, 16);

        mRelativeLayout.setLayoutParams(params);
        //  mRelativeLayout=inflater.inflate(R.layout.floating, null);

        chatHead = new ImageView(context);
        chatHead.setImageResource(R.drawable.ic_medicinecolor);
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
        mView = inflater.inflate(R.layout.activity_confirm_medication, null);
       final TextView title=mView.findViewById(R.id.title);
       final TextView time=mView.findViewById(R.id.txt_time);
       final TextView doze=mView.findViewById(R.id.txt_doze);
       final TextView unit=mView.findViewById(R.id.txt_unit);
       EditText regular = (EditText)   mView.findViewById(R.id.txt_regular);
       Calendar t=Calendar.getInstance();
       t.setTimeInMillis(Long.parseLong(intent.getStringExtra("time")));
       time.setText(t.get(Calendar.HOUR_OF_DAY)+":"+t.get(Calendar.MINUTE));
       doze.setText(intent.getStringExtra("doze"));
       title.setText(intent.getStringExtra("name"));
       unit.setText(intent.getStringExtra("unit"));
       seton(intent,time,doze,unit,title);
       Button btn_SyConfirm;
       Button btn_SyCancel;
       btn_SyConfirm = mView.findViewById(R.id.btn_MConfirm);
       btn_SyCancel = mView.findViewById(R.id.btn_SyCancel);





      btn_SyConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sleep information comes and inserts into database
                db.insertMedLog(title.getText().toString(),unit.getText().toString(),doze.getText().toString(),"");
                windowManager.removeView( mRelativeLayout);

            }
        });
        innerRelativeLayout.addView(mView,params);


        innerRelativeLayout.setElevation(5.0f);
        RelativeLayout.LayoutParams irlp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      //  irlp.setMargins(16, 16, 16, 16);
        irlp.addRule(RelativeLayout.BELOW, chatHead.getId());
        Log.d("cht he", "" + chatHead.getId());
       innerRelativeLayout.setVisibility(View.GONE);
        mRelativeLayout.addView(innerRelativeLayout, irlp);

        mRelativeLayout.setLayoutParams(params);
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
                        params.x=-558;
                        params.y=250;
                        windowManager.updateViewLayout(mRelativeLayout, params);
                        //   mPointer.setVisibility(View.GONE);
                        isLayoutVisible = false;
                    } else {
                        innerRelativeLayout.setVisibility(View.VISIBLE);
                        //      mPointer.setVisibility(View.VISIBLE);
                        params.x=0;
                        params.y=0;
                        windowManager.updateViewLayout(mRelativeLayout, params);
                        isLayoutVisible = true;
                    }} else {


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
                            Log.d("Motion",params.x+ " "+ params.y);
                            windowManager.updateViewLayout(mRelativeLayout, params);
                            return true;
                    }
                }
                return false;
            }
        });
        return mRelativeLayout;
    }


     /*  Long x1=Long.parseLong("1593261077883");
      Long x=sleep.getTimeInMillis()-x1;
      Long hour=x/3600000;
      Long minutes=(x/60000)%60;
       Log.d("time",""+hour+" :"+minutes );*/



void seton(Intent intent, final TextView time, final TextView doze, final TextView unit,final TextView title)
{


}
}