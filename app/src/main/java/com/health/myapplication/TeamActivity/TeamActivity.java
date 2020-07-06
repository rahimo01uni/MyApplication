package com.health.myapplication.TeamActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.health.myapplication.Database.GroupDbHelper;
import com.health.myapplication.Database.member_model;
import com.health.myapplication.R;
import com.health.myapplication.TeamActivity.MemList.MemListActivity;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity implements MemberRecycleviewAdapter.OnEditLisener{

    RecyclerView recyclerView;
   ArrayList <member_model> models;
    MemberRecycleviewAdapter adapter;
    FloatingActionButton btnAddmem;
    SearchView searchView;
    GroupDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
   db=new GroupDbHelper(this);
        searchView = findViewById(R.id.searchView);
        btnAddmem = findViewById(R.id.btn_addmember);

        models = db.get_members();

        recyclerView = findViewById(R.id.mem_recycleView);
        adapter = new MemberRecycleviewAdapter(this, models, this);
        RecyclerView.LayoutManager LManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();

        recyclerView.setLayoutManager(LManager);
        recyclerView.setItemAnimator((animator));
        recyclerView.setAdapter(adapter);


        btnAddmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamActivity.this,AddMember.class);
                startActivity(intent);

            }
        });

    }

  @Override
    public void onMemberClick(int position) {
        Intent intent = new Intent(this, MemListActivity.class);
      intent.putExtra("MemberID",models.get(position).getId());
        startActivity(intent);

  }
}
