package com.health.myapplication.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.health.myapplication.Database.SymptomDbHelper;
import com.health.myapplication.Database.selections_model;
import com.health.myapplication.R;
import com.health.myapplication.TeamActivity.MemList.MemListActivity;
import com.health.myapplication.TeamActivity.MemList.RecyclerViewAdapter;
import com.hsalf.smilerating.SmileRating;
import com.hsalf.smileyrating.SmileyRating;

import java.util.ArrayList;
import java.util.Calendar;

public class LogMoodActivity extends AppCompatActivity {

    TextView txt_logmood;
    TextView txt_TimeLogMood;
    TextView txt_descLogMood;
    Button Save;
    SmileyRating smileyRating;
    ArrayList<selections_model> selected;
    ArrayList<String>selections;

    SymptomDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_mood);
         db=new SymptomDbHelper(this);
        db.insertSym(new ArrayList<String>());
        selected=new ArrayList<>();
        selected=db.getSymptoms();
        selections=new ArrayList<>();
        txt_logmood = findViewById(R.id.txt_logmood);
        txt_TimeLogMood = findViewById(R.id.txt_TimeLogMood);
        txt_descLogMood = findViewById(R.id.txt_descLogMood);
        Save = findViewById(R.id.btn_saveMoodLog);
        smileyRating=findViewById(R.id.smile_rating);
        smileyRating.setSmileySelectedListener(new SmileyRating.OnSmileySelectedListener() {
            @Override
            public void onSmileySelected(SmileyRating.Type type) {
                // You can compare it with rating Type
                 Log.d("type",""+type);
                // You can get the user rating too
                // rating will between 1 to 5
                int rating = type.getRating();
            }
        });
        //go to LogMedication Activity
        Button btnLogmedication = findViewById(R.id.btn_LogmedicationM);
        btnLogmedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogMoodActivity.this, LogMedicationActivity.class);
                startActivity(intent);
            }
        });
          txt_logmood.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  dialog();
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
    void  dialog()
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Add a new element");

        // add a list

        final View customLayout = getLayoutInflater().inflate(R.layout.symptoms, null);
        builder.setView(customLayout);
        final SymptomAdapter adapter=new SymptomAdapter(this,selected,selections);
        RecyclerView recyclerView=customLayout.findViewById(R.id.log_recycleview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                   if(selected.get(i).isChecked())temp+=", "+selected.get(i).getName();
               }
                txt_logmood.setText(temp);
                dialog.dismiss();
            }
        });
        // create and show the alert dialog

        dialog.show();

    }


}
