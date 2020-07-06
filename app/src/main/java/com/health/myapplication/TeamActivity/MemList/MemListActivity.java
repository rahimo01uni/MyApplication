package com.health.myapplication.TeamActivity.MemList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.GroupDbHelper;
import com.health.myapplication.Database.member_model;
import com.health.myapplication.Database.selections_model;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.AddReminderActivity;
import com.health.myapplication.TeamActivity.AddMember;
import com.health.myapplication.TeamActivity.GroupMember;
import com.health.myapplication.TeamActivity.TeamActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MemListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<selections_model> models;
    RecyclerViewAdapter adapter;
    FloatingActionButton btnAddLog;
    ImageView imgshare;
TextView start,end;


    TextView txtsharetype;
    TextView txtshareDate;
    TextView nextDate;
    ImageView auto;
    Button btnSahreSave;
    Button btnSahreSend;
    GroupDbHelper db;
    member_model member;
    ArrayList<selections_model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_list);
db=new GroupDbHelper(this);
list=new ArrayList<>();
        initRecyclerView();
      auto=findViewById(R.id.automated);
      member=db.get_info(i.getStringExtra("MemberID"));
      auto.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openDialog();
          }
      });
        imgshare = findViewById(R.id.img_share);
        imgshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialog();
            }
        });

    }

    Intent i;
    private  void initRecyclerView(){
    i =getIntent();
        models = db.get_selections(i.getStringExtra("MemberID"));

        recyclerView = findViewById(R.id.mem_recycleView);
        adapter = new RecyclerViewAdapter(this, models,list);
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
                intent.putExtra("MemberID",i.getStringExtra("MemberID"));
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
    Calendar date,nextdate;
    public void openDialog(){



        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Add a new element");

        // add a list
        final View customLayout = getLayoutInflater().inflate(R.layout.alert_share, null);
        builder.setView(customLayout);
       date=Calendar.getInstance();
       nextdate=Calendar.getInstance();
        date.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE),0,0);
        txtsharetype = customLayout.findViewById(R.id.txt_sharetype);
        txtshareDate = customLayout.findViewById(R.id.txt_shareDate);
        nextDate=customLayout.findViewById(R.id.nextdate);
        txtshareDate.setText("START DATE: "+date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR));
        btnSahreSave = customLayout.findViewById(R.id.btn_SahreSave);
        if(member.getFrequency().length()>0) txtsharetype.setText(member.getFrequency());
        if(member.getNextdate().length()>0) {
            nextdate.setTimeInMillis(Long.parseLong(member.getNextdate()));
            nextDate.setText("NEXT SEND DATE: " + nextdate.get(Calendar.DATE) + "/" + nextdate.get(Calendar.MONTH) + "/" + nextdate.get(Calendar.YEAR));
        }

        final String[] pickerVals = new String[] { "Daily","Weekly", "Monthly" };
        txtsharetype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NumberPicker picker = new NumberPicker(MemListActivity.this);
                picker.setMinValue(0);
                picker.setMaxValue(2);
                picker.setDisplayedValues( new String[] { "Daily","Weekly", "Monthly" } );

                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        int valuePicker1 = picker.getValue();

                    }
                });

                new AlertDialog.Builder(MemListActivity.this, R.style.AlertDialogTheme)
                        .setTitle("period")
                        .setView(picker)
                        .setNegativeButton("ADD",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 txtsharetype.setText(String.valueOf(pickerVals[picker.getValue()]));
                                 switch (picker.getValue()) {
                                     case 0:
                                         date=Calendar.getInstance();
                                         nextdate=Calendar.getInstance();
                                         txtshareDate.setText("START DATE: "+date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR));
                                         nextdate.set(nextdate.get(Calendar.YEAR), nextdate.get(Calendar.MONTH), nextdate.get(Calendar.DATE) + 1, 0, 0);
                                         nextDate.setText("NEXT SEND DATE: "+nextdate.get(Calendar.DATE)+"/"+nextdate.get(Calendar.MONTH)+"/"+nextdate.get(Calendar.YEAR));
                                         break;
                                     case 1:
                                         date=Calendar.getInstance();
                                         txtshareDate.setText("START DATE: "+date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR));
                                         nextdate=Calendar.getInstance();
                                         nextdate.set(nextdate.get(Calendar.YEAR), nextdate.get(Calendar.MONTH), nextdate.get(Calendar.DATE) + 7, 0, 0);
                                         nextDate.setText("NEXT SEND DATE: "+nextdate.get(Calendar.DATE)+"/"+nextdate.get(Calendar.MONTH)+"/"+nextdate.get(Calendar.YEAR));
                                         break;
                                     case 2:
                                         date=Calendar.getInstance();
                                         txtshareDate.setText("START DATE: "+date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR));
                                         nextdate=Calendar.getInstance();
                                         nextdate.set(nextdate.get(Calendar.YEAR),nextdate.get(Calendar.MONTH), nextdate.get(Calendar.DATE) + 30, 0, 0);
                                         nextDate.setText("NEXT SEND DATE: "+nextdate.get(Calendar.DATE)+"/"+nextdate.get(Calendar.MONTH)+"/"+nextdate.get(Calendar.YEAR));
                                        break;
                                 }


                            }
                        })

                        .show();


            }
        });


        builder.setNegativeButton(" ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        // create and show the alert dialog
       final AlertDialog dialog = builder.create();
        dialog.show();
        btnSahreSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.update_frequency(i.getStringExtra("MemberID"),txtsharetype.getText().toString(),""+date.getTimeInMillis(),""+nextdate.getTimeInMillis());
                dialog.dismiss();
            }
        });

    }
    void  dialog()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Add a new element");

        // add a list
        final View customLayout = getLayoutInflater().inflate(R.layout.alert_share, null);
        builder.setView(customLayout);
        date=Calendar.getInstance();
        nextdate=Calendar.getInstance();
        date.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE),0,0);
        if(member.getStartDate().length()>0)date.setTimeInMillis(Long.parseLong(member.getStartDate()));
        Log.d("time",""+date.getTime());
        start = customLayout.findViewById(R.id.start_date);
        end = customLayout.findViewById(R.id.end_date);

        start.setText("FROM: "+date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR));
        end.setText("TILL: "+date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR));
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.setTimeInMillis(calendar(start));
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextdate.setTimeInMillis(calendar(end));
            }
        });
        btnSahreSave = customLayout.findViewById(R.id.btn_SahreSave);

        builder.setNegativeButton(" ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        // create and show the alert dialog
        final AlertDialog dialog = builder.create();
        dialog.show();
        btnSahreSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

db.sender_db(""+date,""+nextDate,list);

                dialog.dismiss();
            }
        });
    }
    Calendar cldr;
    Long calendar(final  TextView start)
    {
       cldr = Calendar.getInstance();
        final int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        DatePickerDialog   picker = new DatePickerDialog(MemListActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        cldr=Calendar.getInstance();
                        cldr.set(year,monthOfYear,dayOfMonth);
                        start.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
        return cldr.getTimeInMillis();
    }

}
