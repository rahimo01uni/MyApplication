package com.health.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.UniversalTimeScale;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;




public class AddMood extends AppCompatActivity {

    String[] listItems;
    Button btnReminder;
    Button btnSleep;
    TextView txtMoodName;
    EditText txt_descM;
    TextView txt_DateM;
    TextView txt_TimeM;
    Button btn_saveM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        txtMoodName = findViewById(R.id.txt_mood);
        txt_descM = findViewById(R.id.txt_descM);
        txt_DateM = findViewById(R.id.txt_DateM);
        txt_TimeM = findViewById(R.id.txt_TimeM);
        btn_saveM = findViewById(R.id.btn_saveM);


        //go to AddReminder Activity
        btnReminder = findViewById(R.id.btn_medicationM);
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMood.this, AddReminderActivity.class);
                startActivity(intent);
            }
        });

        //go to AddSleep Activity
        btnSleep = findViewById(R.id.btn_sleepM);
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMood.this, AddSleep.class);
                startActivity(intent);
            }
        });

        //alert radio button Symptom
        listItems = getResources().getStringArray(R.array.mood_item);
        final TextView txtMood = findViewById(R.id.txt_mood);


        txtMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddMood.this);
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
        });

    }

}
