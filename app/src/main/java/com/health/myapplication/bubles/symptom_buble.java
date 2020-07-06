package com.health.myapplication.bubles;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
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
import android.widget.RelativeLayout;

import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.SymptomDbHelper;
import com.health.myapplication.Database.sleep_model;
import com.health.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.RequiresApi;

public class symptom_buble {
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
 SymptomDbHelper db;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public symptom_buble(Context context, String type){
        getmView(context,type);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    View getmView(final Context context,String type) {
        db=new SymptomDbHelper(context);
        gestureDetector = new GestureDetector( new sleep_buble.SingleTapConfirm());
        windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        if(type.equals("night"))check_symptom_wakeUp(context,params);
        params.x=-558;
        params.y=100;
  mRelativeLayout=check_symptom_wakeUp(context,params);

        windowManager.addView(mRelativeLayout, params);
        return mRelativeLayout;
    }
   RelativeLayout check_symptom_wakeUp(Context context,final WindowManager.LayoutParams params){
        mRelativeLayout = new RelativeLayout(context);
//
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.setMargins(16, 16, 16, 16);

        mRelativeLayout.setLayoutParams(params);
        //  mRelativeLayout=inflater.inflate(R.layout.floating, null);

        chatHead = new ImageView(context);
        chatHead.setImageResource(R.drawable.ic_smile);
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
        mView = inflater.inflate(R.layout.activity_confirm_sympthom, null);
       EditText regular = (EditText)   mView.findViewById(R.id.txt_regular);
       Button btn_SyConfirm;
       Button btn_SyCancel;
       btn_SyConfirm = mView.findViewById(R.id.btn_SyConfirm);
       btn_SyCancel = mView.findViewById(R.id.btn_SyCancel);
       InputMethodManager inputMethodManager =
               (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
       inputMethodManager.toggleSoftInputFromWindow(
               mRelativeLayout.getApplicationWindowToken(),
               InputMethodManager.SHOW_FORCED, 0);
     //   mClose = (Button) mView.findViewById(R.id.buttonClose);
        /*con=(Button)mView.findViewById(R.id.btn_gosleep);
        con.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
             sleeping=true;
                con.setText("Sleeping");
                self_log("sleep");
                chatHead.setImageResource(R.drawable.moon);
                return false;
            }
        });*/



       btn_SyConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sleep information comes and inserts into database
             db.insertSymptomLog("good",new ArrayList<String>());
                windowManager.removeView( mRelativeLayout);

            }
        });
        innerRelativeLayout.addView(mView,params);
       innerRelativeLayout.setVisibility(View.GONE);

        innerRelativeLayout.setElevation(5.0f);
        RelativeLayout.LayoutParams irlp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        irlp.setMargins(16, 16, 16, 16);
        irlp.addRule(RelativeLayout.BELOW, chatHead.getId());
        Log.d("cht he", "" + chatHead.getId());
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
                        params.y=100;
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
                            windowManager.updateViewLayout(mRelativeLayout, params);
                            return true;
                    }
                }
                return false;
            }
        });
        return mRelativeLayout;
    }

}