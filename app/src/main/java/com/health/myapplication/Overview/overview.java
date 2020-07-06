package com.health.myapplication.Overview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.OverviewDbHelper;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.AddReminderActivity;
import com.health.myapplication.Reminder.Reminder;
import com.health.myapplication.utils.AdapterOverview;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.Calendar;

public class overview extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterOverview adapterOverview;
    OverviewDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        db=new OverviewDbHelper(this);
        recyclerView=findViewById(R.id.mrecycler);
        adapterOverview=new AdapterOverview
                (this,db.get_overview(""+Calendar.getInstance().get(Calendar.DATE)+"/"+Calendar.getInstance().get(Calendar.MONTH)+"/"+Calendar.getInstance().get(Calendar.YEAR)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterOverview);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(overview.this, AddReminderActivity.class);
                startActivity(intent);

                // new med_bubble(Reminder.this,"");
                //sleep_buble b=new sleep_buble(Reminder.this,"sleep" );
            }
        });


        final CollapsibleCalendar collapsibleCalendar = findViewById(R.id.calendarView);

        collapsibleCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Log.d("params",""+collapsibleCalendar.getLayoutParams().height);
            //  if(collapsibleCalendar.getLayoutParams().height==89)  collapsibleCalendar.getLayoutParams().height=89; else collapsibleCalendar.getLayoutParams().height=267;
            }
        });
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = collapsibleCalendar.getSelectedDay();
                Log.i(getClass().getName(), "Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
                adapterOverview.setData(db.get_overview(day.getDay()+"/"+day.getMonth()+"/"+day.getYear()));
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }
        });



    }

}
