package com.health.myapplication.TeamActivity.MemList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.health.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TeamLogActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<CardModel> models;
    RecyclerViewAdapterLog adapter;
    Button Addlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_log);

        Addlog= findViewById(R.id.btn_AddLog);


        initRecyclerView();
    }
    private  void initRecyclerView(){

        models = new ArrayList<>();
        models.add(new CardModel("BloodPressure", "Daily- 8:00"));
        models.add(new CardModel("BloodPressure", "Daily- 8:00"));
        models.add(new CardModel("BloodPressure", "Daily- 8:00"));
        models.add(new CardModel("BloodPressure", "Daily- 8:00"));
        models.add(new CardModel("BloodPressure", "Daily- 8:00"));
        models.add(new CardModel("BloodPressure", "Daily- 8:00"));
        models.add(new CardModel("BloodPressure", "Daily- 8:00"));
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

