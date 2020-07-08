package com.health.myapplication.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.MedicationDbHelber;
import com.health.myapplication.Database.OverviewDbHelper;
import com.health.myapplication.Overview.overview;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.AddMood;
import com.health.myapplication.Reminder.AddReminderActivity;
import com.health.myapplication.Reminder.AddSleep;

import java.util.Calendar;

public class LogMedicationActivity extends AppCompatActivity {

    TextView txt_LogNameM;
   EditText txt_LogDozeM;
    TextView txt_LogunitM;
    TextView txt_LogTimeM;
    TextView txt_LogdesM;
    Button btn_savelogM;
    MedicationDbHelber db;

    Calendar time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_medication);
      db=new MedicationDbHelber(this);
        time=Calendar.getInstance();
        txt_LogNameM = findViewById(R.id.txt_LogNameM);
        txt_LogDozeM = findViewById(R.id.txt_LogDozeM);
        txt_LogunitM = findViewById(R.id.txt_LogunitM);
        txt_LogTimeM = findViewById(R.id.txt_LogTimeM);
        txt_LogdesM = findViewById(R.id.txt_LogdesM);
        btn_savelogM = findViewById(R.id.btn_savelogM);
        final String date=getIntent().getStringExtra("date");
        time.setTimeInMillis(Long.parseLong(date));
        Log.d("date",""+time.getTime());
        btn_savelogM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertMedLog(txt_LogNameM.getText().toString(),txt_LogunitM.getText().toString(),txt_LogDozeM.getText().toString(),txt_LogdesM.getText().toString(),""+time.getTimeInMillis());
                overview.s1.sendEmptyMessage(1);
                 finish();
            }
        });

        //go to LogMode Activity
        Button btnLogsym = findViewById(R.id.btn_Logsym);
        btnLogsym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogMedicationActivity.this, LogMoodActivity.class);
                startActivity(intent);
            }
        });

        //go to LogSleep Activity
        Button btnLogsleep = findViewById(R.id.btn_Logsleep);
        btnLogsleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogMedicationActivity.this, LogSleepActivity.class);
                startActivity(intent);
            }
        });

//Doze

        //Unit


        final String[] pickerValsUnit = new String[] { "Pill","A", "B" };

        txt_LogunitM.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final NumberPicker picker = new NumberPicker(LogMedicationActivity.this);
                picker.setMinValue(0);
                picker.setMaxValue(2);
                picker.setDisplayedValues( new String[] { "Pill","A", "B" } );

                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        int valuePicker1 = picker.getValue();

                    }
                });

                new AlertDialog.Builder(LogMedicationActivity.this,R.style.AlertDialogTheme)
                        .setTitle("Unit")
                        .setView(picker)
                        .setNegativeButton("ADD",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Snackbar.make(findViewById(R.id.txt_LogunitM), "You picked : " + pickerValsUnit[picker.getValue()], Snackbar.LENGTH_LONG).show();
                                txt_LogunitM.setText(String.valueOf(pickerValsUnit[picker.getValue()]));
                            }
                        })
                        .show();

            }

        });
        txt_LogTimeM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(LogMedicationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        txt_LogTimeM.setText(TimeFormat(hourOfDay,minutes));
                        time.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DATE),hourOfDay,minutes,0);


                    }
                }, 0, 0, false);
                timePickerDialog.show();

            }
        });
            }
    private  String TimeFormat(int hourOfDay, int minutes){

        String hours=""+hourOfDay,
                minutess=""+minutes;
        if(hours.length()==1) hours="0"+hours;
        if(minutess.length()==1)minutess="0"+minutess;
        return hours+":"+minutess;
    }

}
