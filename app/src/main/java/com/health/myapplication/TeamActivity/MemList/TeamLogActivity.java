package com.health.myapplication.TeamActivity.MemList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.health.myapplication.Database.GroupDbHelper;
import com.health.myapplication.Database.selections_model;
import com.health.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TeamLogActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<add_logMem_model> models;
    RecyclerViewAdapterLog adapter;
    Button Addlog;
    GroupDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_log);
db=new GroupDbHelper(this);
        Addlog= findViewById(R.id.btn_AddLog);
        final   Intent i=getIntent();

        Addlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insert_LogSelection(i.getStringExtra("MemberID"),models);
                finish();
            }
        });

        initRecyclerView();
    }
    private  void initRecyclerView(){

        models = db.get_LogNames();

        recyclerView = findViewById(R.id.log_recycleview);
        adapter = new RecyclerViewAdapterLog(this, models);
        RecyclerView.LayoutManager LManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(LManager);
        recyclerView.setItemAnimator((animator));
        recyclerView.setAdapter(adapter);



    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            models.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();

        }
    };
}

