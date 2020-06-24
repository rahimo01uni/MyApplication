package com.health.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity implements MemberRecycleviewAdapter.OnEditLisener{

    RecyclerView recyclerView;
    List<GroupMember> models;
    MemberRecycleviewAdapter adapter;
    FloatingActionButton btnAddmem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);


        models = new ArrayList<>();
        models.add(new GroupMember("Dentist"));
        models.add(new GroupMember("Ali"));
        models.add(new GroupMember("Zahra"));


        recyclerView = findViewById(R.id.recycleView_member);
        adapter = new MemberRecycleviewAdapter(this, models, this);
        RecyclerView.LayoutManager LManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();

        recyclerView.setLayoutManager(LManager);
        recyclerView.setItemAnimator((animator));
        recyclerView.setAdapter(adapter);

        btnAddmem = findViewById(R.id.btn_addmember);
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
        Intent intent = new Intent(this, EditMemList.class);
        //intent.putExtra("denti","ddd");
        startActivity(intent);
    }
}
