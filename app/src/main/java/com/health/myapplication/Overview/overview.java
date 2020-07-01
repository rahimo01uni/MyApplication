package com.health.myapplication.Overview;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.OverviewDbHelper;
import com.health.myapplication.R;
import com.health.myapplication.utils.AdapterOverview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterOverview=new AdapterOverview(this,db.get_overview(""+Calendar.getInstance().getTimeInMillis()));
        recyclerView.setAdapter(adapterOverview);



    }

}
