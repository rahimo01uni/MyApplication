package com.health.myapplication.Reminder;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.ReminderOverviewDbHelper;
import com.health.myapplication.R;
import com.health.myapplication.bubles.med_bubble;
import com.health.myapplication.bubles.sleep_buble;
import com.health.myapplication.utils.Adapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class Reminder extends AppCompatActivity {
RecyclerView recyclerView;
Adapter adapter;
ReminderOverviewDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db=new ReminderOverviewDbHelper(this);

        recyclerView=(RecyclerView)findViewById(R.id.reminder_recycler);
        adapter=new Adapter(this,db.getReminders(),db);
//        Log.d("what",""+db.getReminders().get(db.getReminders().size()-1).getSleep_log().getSleepTime());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                new med_bubble(Reminder.this,"");
              //sleep_buble b=new sleep_buble(Reminder.this,"sleep" );
            }
        });
    }

}
