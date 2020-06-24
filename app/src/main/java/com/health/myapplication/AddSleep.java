package com.health.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddSleep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sleep);

        //go to AddReminder Activity
        Button btnReminder = findViewById(R.id.btn_medicationS);
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSleep.this, AddReminderActivity.class);
                startActivity(intent);
            }
        });

        //go to AddMood Activity
        Button btnMood = findViewById(R.id.btn_symS);
        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSleep.this, AddMood.class);
                startActivity(intent);
            }
        });
    }
}
