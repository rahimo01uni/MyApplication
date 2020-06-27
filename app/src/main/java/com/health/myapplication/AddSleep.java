package com.health.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddSleep extends AppCompatActivity {
Button btn_saveS;
Button btnReminder;
Button btnMood;
TextView txt_DateS;
TextView txt_StartTimeS;
TextView txt_EndTimeS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sleep);

        txt_DateS = findViewById(R.id.txt_DateS);
        txt_StartTimeS = findViewById(R.id.txt_StartTimeS);
        txt_EndTimeS = findViewById(R.id.txt_EndTimeS);

        //go to AddReminder Activity
        btnReminder = findViewById(R.id.btn_medicationS);
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSleep.this, AddReminderActivity.class);
                startActivity(intent);
            }
        });

        //go to AddMood Activity
        btnMood = findViewById(R.id.btn_symS);
        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSleep.this, AddMood.class);
                startActivity(intent);
            }
        });

        btn_saveS = findViewById(R.id.btn_saveS);
        btn_saveS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSleep.this, ConfirmSleepActivity.class);
                startActivity(intent);
            }
        });
    }
}
