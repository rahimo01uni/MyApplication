package com.health.myapplication.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.R;

public class LogSleepActivity extends AppCompatActivity {

    TextView txt_LogStartTimeS;
    TextView txt_LogEndTimeS;
    TextView txt_LogDurationS;
    TextView txt_LogQualityS;
    TextView txt_descLog;
    Button Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sleep);

        txt_LogStartTimeS = findViewById(R.id.txt_LogStartTimeS);
        txt_LogEndTimeS = findViewById(R.id.txt_LogEndTimeS);
        txt_LogDurationS = findViewById(R.id.txt_LogDurationS);
        txt_LogQualityS = findViewById(R.id.txt_LogQualityS);
        txt_descLog = findViewById(R.id.txt_LogQualityS);
        Save = findViewById(R.id.btn_saveSleepLog);

        //go to LogMedication Activity
        Button btnmedicationS = findViewById(R.id.btn_medicationS);
        btnmedicationS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogSleepActivity.this, LogMedicationActivity.class);
                startActivity(intent);
            }
        });

        //go to LogMood Activity
        Button btnsymS = findViewById(R.id.btn_symS);
        btnsymS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogSleepActivity.this, LogMoodActivity.class);
                startActivity(intent);
            }
        });



        //Quqlity
        final String[] pickerValsUnit = new String[] { "Good","Bad", "So So" };

        txt_LogQualityS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final NumberPicker picker = new NumberPicker(LogSleepActivity.this);
                picker.setMinValue(0);
                picker.setMaxValue(2);
                picker.setDisplayedValues( new String[] { "Good","Bad", "So So" } );

                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        int valuePicker1 = picker.getValue();

                    }
                });

                new AlertDialog.Builder(LogSleepActivity.this,R.style.AlertDialogTheme)
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
                                Snackbar.make(findViewById(R.id.txt_LogQualityS), "You picked : " + pickerValsUnit[picker.getValue()], Snackbar.LENGTH_LONG).show();
                                txt_LogQualityS.setText(String.valueOf(pickerValsUnit[picker.getValue()]));
                            }
                        })
                        .show();

            }

        });
    }
}
