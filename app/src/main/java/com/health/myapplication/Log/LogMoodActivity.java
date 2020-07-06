package com.health.myapplication.Log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.health.myapplication.R;

public class LogMoodActivity extends AppCompatActivity {

    TextView txt_logmood;
    TextView txt_TimeLogMood;
    TextView txt_descLogMood;
    Button Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_mood);

        txt_logmood = findViewById(R.id.txt_logmood);
        txt_TimeLogMood = findViewById(R.id.txt_TimeLogMood);
        txt_descLogMood = findViewById(R.id.txt_descLogMood);
        Save = findViewById(R.id.btn_saveMoodLog);


        //go to LogMedication Activity
        Button btnLogmedication = findViewById(R.id.btn_LogmedicationM);
        btnLogmedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogMoodActivity.this, LogMedicationActivity.class);
                startActivity(intent);
            }
        });

        //go to LogSleep Activity
        Button btnLogsleepM = findViewById(R.id.btn_LogsleepM);
        btnLogsleepM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogMoodActivity.this, LogSleepActivity.class);
                startActivity(intent);
            }
        });
    }
}
