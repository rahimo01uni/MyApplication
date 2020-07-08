package com.health.myapplication.bubles;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
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
import android.widget.TextView;
import android.widget.Toast;

import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.SymptomDbHelper;
import com.health.myapplication.Database.selections_model;
import com.health.myapplication.Database.sleep_model;
import com.health.myapplication.Log.LogMoodActivity;
import com.health.myapplication.Log.SymptomAdapter;
import com.health.myapplication.R;
import com.hsalf.smileyrating.SmileyRating;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    SmileyRating smileyRating;
    ArrayList<selections_model> selected;
    ArrayList<String>selections;
    TextView add_symptoms;
    EditText notes;
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
        selected=new ArrayList<>();
        selected=db.getSymptoms();
        selections=new ArrayList<>();
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
   RelativeLayout check_symptom_wakeUp(final Context context, final WindowManager.LayoutParams params){
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
       add_symptoms=mView.findViewById(R.id.sypmtoms);
       notes = (EditText)   mView.findViewById(R.id.notes);
       add_symptoms.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
dialog(context);
           }
       });

       Button btn_SyConfirm;
       Button btn_SyCancel;
       final SmileyRating smileyRating;
       btn_SyConfirm = mView.findViewById(R.id.btn_SyConfirm);
       btn_SyCancel = mView.findViewById(R.id.btn_SyCancel);
        smileyRating=mView.findViewById(R.id.smile_rating);

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
                Log.d("selectedSmiley",smileyRating.getSelectedSmiley().toString());
            if(smileyRating.getSelectedSmiley().toString().equals("NONE"))
            {
                Toast.makeText(context,"Please, Select your mood!",Toast.LENGTH_LONG).show();
            }
                else{db.insertSymptomLog(smileyRating.getSelectedSmiley().toString(),notes.getText().toString(),add_symptoms.getText().toString());
                windowManager.removeView( mRelativeLayout);}

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
                        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE,
                                PixelFormat.TRANSLUCENT);
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
    void  dialog(Context context)
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.AlertDialogTheme);
        //builder.setTitle("Add a new element");

        // add a list
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE );
        final View customLayout =  inflater.inflate(R.layout.symptoms, null);
        builder.setView(customLayout);
        final SymptomAdapter adapter=new SymptomAdapter(context,selected,selections);
        RecyclerView recyclerView=customLayout.findViewById(R.id.log_recycleview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        final EditText search=customLayout.findViewById(R.id.searchView);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<selections_model> temp=new ArrayList<>();
                if(s.length()>0){for(selections_model sel:selected){
                    if(sel.getName().contains(s))temp.add(sel);
                }  }else temp=selected;
                for(selections_model s1:temp){
                    if(s1.isChecked())Log.d("tempda",s1.getName());
                }
                adapter.setData(temp);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final AlertDialog dialog = builder.create();
        Button save=customLayout.findViewById(R.id.btn_AddLog);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp="";

                for (int i=0; i<selected.size(); i++)
                {
                   if(selected.get(i).isChecked()) temp+=", "+selected.get(i).getName();
                }

                dialog.dismiss();
                add_symptoms.setText(temp.substring(1));
            }
        });
        // create and show the alert dialog

        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        dialog.show();

    }
}