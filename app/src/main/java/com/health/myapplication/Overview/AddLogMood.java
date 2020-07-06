package com.health.myapplication.Overview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.health.myapplication.Database.SymptomDbHelper;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.Reminder;
import com.health.myapplication.utils.AdapterTime;

import java.util.ArrayList;
import java.util.Calendar;


public class AddLogMood extends AppCompatActivity {

    String[] listItems;
    Button btnReminder;
    Button btnSleep;
    TextView txtMoodName;
    EditText txt_descM;
    TextView txt_DateM;
    TextView txt_TimeM;
    Button btn_saveM;
    RecyclerView recyclerView;
    ArrayList times;
    AdapterTime adapter;
    DatePickerDialog picker;
    Calendar check,startDate,endDate;
    SymptomDbHelper db;
    ToggleButton smile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_add);
        db=new SymptomDbHelper(this);
        txt_TimeM = findViewById(R.id.txt_TimeM);
        btn_saveM = findViewById(R.id.btn_saveM);
        times=new ArrayList();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        adapter=new AdapterTime(this,times);
//        Log.d("what",""+db.getReminders().get(db.getReminders().size()-1).getSleep_log().getSleepTime());

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

        btn_saveM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
db.insertSymptomReminder(times);
                Intent intent2 = new Intent(AddLogMood.this,Reminder.class);
                startActivity(intent2);
                finish();
            }
        });


        //go to AddReminder Activity
        btnReminder = findViewById(R.id.btn_medicationM);
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLogMood.this, AddLogMed.class);
                startActivity(intent);
            }
        });

        //go to AddLogSleep Activity
        btnSleep = findViewById(R.id.btn_sleepM);
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLogMood.this, AddLogSleep.class);
                startActivity(intent);
            }
        });

        txt_TimeM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddLogMood.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        check = Calendar.getInstance();
                        String hours=""+hourOfDay,minutess=""+minutes;
                        if(hourOfDay==0)hours="00";
                        if(minutes==0)minutess="00";
                        if(hours.length()==1) hours="0"+hours;
                        if(minutess.length()==1)minutess="0"+minutess;

                        check.set(check.get(Calendar.YEAR),  check.get(Calendar.MONTH),  check.get(Calendar.DATE),hourOfDay,minutes,0);
                        if(check.getTimeInMillis()<=Calendar.getInstance().getTimeInMillis())
                            check.set( check.get(Calendar.YEAR),  check.get(Calendar.MONTH),  check.get(Calendar.DATE)+1,hourOfDay,minutes,0);
                        times.add(""+check.getTimeInMillis());
                        recyclerView.setVisibility( View.VISIBLE);
                        adapter.setData(times);
                    }
                }, 0, 0, false);
                timePickerDialog.show();

            }
        });






        //alert radio button Symptom
        listItems = getResources().getStringArray(R.array.mood_item);
        final TextView txtMood = findViewById(R.id.txt_mood);


      /*  txtMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddLogMood.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setPositiveButton("Add", null);
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txtMood.setText(listItems[i]);
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });*/

    }

}
