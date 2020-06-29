package com.health.myapplication.TeamActivity.MemList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.health.myapplication.R;
import com.health.myapplication.TeamActivity.AddMember;
import com.health.myapplication.TeamActivity.GroupMember;
import com.health.myapplication.TeamActivity.TeamActivity;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MemListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<CardModel> models;
    RecyclerViewAdapter adapter;
    FloatingActionButton btnAddLog;
    ImageView imgshare;

    TextView txtsharetype;
    TextView txtshareDate;
    Button btnSahreSave;
    Button btnSahreSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_list);

        initRecyclerView();

        imgshare = findViewById(R.id.img_share);
        imgshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

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
        recyclerView = findViewById(R.id.mem_recycleView);
        adapter = new RecyclerViewAdapter(this, models);
        RecyclerView.LayoutManager LManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(LManager);
        recyclerView.setItemAnimator((animator));
        recyclerView.setAdapter(adapter);


        btnAddLog = findViewById(R.id.btn_addlog);
        btnAddLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemListActivity.this, TeamLogActivity.class);
                startActivity(intent);

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
        models.remove(viewHolder.getAdapterPosition());
           adapter.notifyDataSetChanged();

        }
    };

    public void openDialog(){


        txtsharetype = findViewById(R.id.txt_sharetype);
        txtshareDate = findViewById(R.id.txt_shareDate);
        btnSahreSave = findViewById(R.id.btn_SahreSave);
        btnSahreSend = findViewById(R.id.btn_SahreSend);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Add a new element");

        // add a list
        final View customLayout = getLayoutInflater().inflate(R.layout.alert_sharelog, null);
        builder.setView(customLayout);


        builder.setNegativeButton(" ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
