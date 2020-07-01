package com.health.myapplication.Reminder;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.ReminderOverviewDbHelper;
import com.health.myapplication.Database.general_model;
import com.health.myapplication.R;
import com.health.myapplication.bubles.med_bubble;
import com.health.myapplication.bubles.sleep_buble;
import com.health.myapplication.utils.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class Reminder extends AppCompatActivity {
RecyclerView recyclerView;
Adapter adapter;
ReminderOverviewDbHelper db;
ArrayList<general_model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db=new ReminderOverviewDbHelper(this);
        list=db.getReminders();
        recyclerView=(RecyclerView)findViewById(R.id.reminder_recycler);
        adapter=new Adapter(this,list,db);
//        Log.d("what",""+db.getReminders().get(db.getReminders().size()-1).getSleep_log().getSleepTime());
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
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


    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            db.delete(list.get(viewHolder.getAdapterPosition()));
            list.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();

        }
    };

}
