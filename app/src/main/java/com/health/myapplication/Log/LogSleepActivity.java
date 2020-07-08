package com.health.myapplication.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Overview.AddLogSleep;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.AddSleep;

import java.util.Calendar;

public class LogSleepActivity extends AppCompatActivity {

    TextView txt_LogStartTimeS;
    TextView txt_LogEndTimeS;
    TextView txt_LogDurationS;
    TextView txt_LogQualityS;
    TextView txt_descLog;
    Button Save;
    Calendar sleep,wake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sleep);

        txt_LogStartTimeS = findViewById(R.id.txt_LogStartTimeS);
        txt_LogEndTimeS = findViewById(R.id.txt_LogEndTimeS);

        Save = findViewById(R.id.btn_saveSleepLog);
        sleep=Calendar.getInstance();
        wake=Calendar.getInstance();
        final String date=getIntent().getStringExtra("date");
        sleep.setTimeInMillis(Long.parseLong(date));
        wake.setTimeInMillis(Long.parseLong(date));

        //go to LogMedication Activity
        Button btnmedicationS = findViewById(R.id.btn_medicationS);
        btnmedicationS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogSleepActivity.this, LogMedicationActivity.class);
                intent.putExtra("date",date);

                startActivity(intent);
                finish();
            }
        });

        //go to LogMood Activity
        Button btnsymS = findViewById(R.id.btn_symS);
        btnsymS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogSleepActivity.this, LogMoodActivity.class);
                intent.putExtra("date",date);

                startActivity(intent);

                finish();
            }
        });


        txt_LogStartTimeS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(LogSleepActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String hours=""+hourOfDay,minutess=""+minutes;
                        if(hourOfDay==0)hours="00";
                        if(minutes==0)minutess="00";
                        if(hours.length()==1) hours="0"+hours;
                        if(minutess.length()==1)minutess="0"+minutess;
                        txt_LogStartTimeS.setText(hours+":"+minutess);
                        sleep.set(sleep.get(Calendar.YEAR), sleep.get(Calendar.MONTH), sleep.get(Calendar.DATE),hourOfDay,minutes,0);

                    }
                }, 0, 0, false);
                timePickerDialog.show();

            }
        });
        txt_LogEndTimeS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(LogSleepActivity.this, R.style.MyDatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String hours=""+hourOfDay,minutess=""+minutes;
                        if(hourOfDay==0)hours="00";
                        if(minutes==0)minutess="00";
                        if(hours.length()==1) hours="0"+hours;
                        if(minutess.length()==1)minutess="0"+minutess;
                        txt_LogEndTimeS.setText(hours+":"+minutess);
                        wake.set(wake.get(Calendar.YEAR), wake.get(Calendar.MONTH), wake.get(Calendar.DATE),hourOfDay,minutes,0);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }

        });





    }
}
