package com.health.myapplication.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.AddMood;
import com.health.myapplication.Reminder.AddReminderActivity;
import com.health.myapplication.Reminder.AddSleep;

public class LogMedicationActivity extends AppCompatActivity {

    TextView txt_LogNameM;
    TextView txt_LogDozeM;
    TextView txt_LogunitM;
    TextView txt_LogTimeM;
    TextView txt_LogdesM;
    Button btn_savelogM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_medication);


        txt_LogNameM = findViewById(R.id.txt_LogNameM);
        txt_LogDozeM = findViewById(R.id.txt_LogDozeM);
        txt_LogunitM = findViewById(R.id.txt_LogunitM);
        txt_LogTimeM = findViewById(R.id.txt_LogTimeM);
        txt_LogdesM = findViewById(R.id.txt_LogdesM);
        btn_savelogM = findViewById(R.id.btn_savelogM);


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
        txt_LogDozeM.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(LogMedicationActivity.this)
                        .minValue(1)
                        .maxValue(10)
                        .defaultValue(1)
                        .backgroundColor(Color.WHITE)
                        .separatorColor(Color.TRANSPARENT)
                        .textColor(Color.BLACK)
                        .textSize(20)
                        .enableFocusability(false)
                        .wrapSelectorWheel(true)
                        .build();

                new AlertDialog.Builder(LogMedicationActivity.this, R.style.AlertDialogTheme)
                        .setTitle("Count")
                        .setView(numberPicker)
                        .setNegativeButton("ADD",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Snackbar.make(findViewById(R.id.txt_LogDozeM), "You picked : " + numberPicker.getValue(), Snackbar.LENGTH_LONG).show();
                                txt_LogDozeM.setText(String.valueOf(numberPicker.getValue()));

                            }
                        })
                        .show();

            }

        });


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

    }
}
