package com.health.myapplication.Overview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.MedicationDbHelber;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.Reminder;
import com.health.myapplication.utils.AdapterTime;

import java.util.ArrayList;
import java.util.Calendar;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;

public class AddLogMed extends AppCompatActivity {
    String[] listItems;
    Button btnSym;
    Button btnSleep;
    Button save;
    EditText txt_medicationname;
    TextView txt_unit;
    TextView txt_Count;
    TextView txt_SDate;
    TextView txt_EDate;
    TextView txt_Period;
    TextView txt_Time;
    TextView txtunit;
    RecyclerView    recyclerView;
   ArrayList times;
    AdapterTime adapter;
    DatePickerDialog picker;
    Calendar sleep,startDate,endDate;
    ImageView delete;
    MedicationDbHelber mdb;
    TextView txtperiod;
    TextView desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        mdb=new MedicationDbHelber(this);
        times=new ArrayList();
        delete=findViewById(R.id.delete);
        txt_medicationname = findViewById(R.id.txt_mood);
        txt_Time=findViewById(R.id.txt_Time);
        txt_Count = findViewById(R.id.txt_Count);
        txt_SDate = findViewById(R.id.txt_SDate);
        txt_EDate = findViewById(R.id.txt_EDate);
        txt_Period = findViewById(R.id.txt_Period);
        desc=findViewById(R.id.txt_desc);
        save=findViewById(R.id.btn_savelogM);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              mdb.insertMedReminder(txt_medicationname.getText().toString(),txtunit.getText().toString(),txt_Count.getText().toString(),""+startDate.getTimeInMillis(),
                      ""+endDate.getTimeInMillis(),txt_Period.getText().toString(),desc.getText().toString(),times);
                Intent intent2 = new Intent(AddLogMed.this,Reminder.class);
                startActivity(intent2);
                finish();

                Log.d("timesLength",""+times.size());
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        adapter=new AdapterTime(this,times);
//        Log.d("what",""+db.getReminders().get(db.getReminders().size()-1).getSleep_log().getSleepTime());

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        startDate=Calendar.getInstance();
txt_SDate.setText(startDate.get(Calendar.DATE)+"/"+startDate.get(Calendar.MONTH)+"/"+startDate.get(Calendar.YEAR));
        txt_SDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddLogMed.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDate=Calendar.getInstance();
                                startDate.set(year,monthOfYear,dayOfMonth);
                                txt_SDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();

            }
        });
        endDate=Calendar.getInstance();
        endDate.set(endDate.get(Calendar.DATE),endDate.get(Calendar.MONTH),endDate.get(Calendar.YEAR)+1);
        txt_EDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddLogMed.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDate=Calendar.getInstance();
                                endDate.set(year,monthOfYear,dayOfMonth);
                                txt_EDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
         txt_Time.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 TimePickerDialog timePickerDialog = new TimePickerDialog(AddLogMed.this, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                         sleep = Calendar.getInstance();
                         String hours=""+hourOfDay,minutess=""+minutes;
                         if(hourOfDay==0)hours="00";
                         if(minutes==0)minutess="00";
                         if(hours.length()==1) hours="0"+hours;
                         if(minutess.length()==1)minutess="0"+minutess;

                         sleep.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE),hourOfDay,minutes,0);
                         int i=0;
                         while (sleep.getTimeInMillis()<=Calendar.getInstance().getTimeInMillis()) {
                             i++;
                             sleep.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE) + i, hourOfDay, minutes, 0);
                         }
                         times.add(""+sleep.getTimeInMillis());
                         recyclerView.setVisibility( View.VISIBLE);
                         adapter.setData(times);
                     }
                 }, 0, 0, false);
                 timePickerDialog.show();

             }
         });



        //go to AddLogMood Activity
        btnSym = findViewById(R.id.btn_sym);
        btnSym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //go to AddLogSleep Activity
        btnSleep = findViewById(R.id.btn_sleep);
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLogMed.this, AddLogSleep.class);
                startActivity(intent);
            }
        });

        //alert radio button Unit
//        listItems = getResources().getStringArray(R.array.unit_item);
//
//
//
//        txtunit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddLogMed.this);
//                mBuilder.setTitle("Choose an item");
//                mBuilder.setPositiveButton("Add", null);
//                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        txtunit.setText(listItems[i]);
//                        dialogInterface.dismiss();
//                    }
//                });
//
//                AlertDialog mDialog = mBuilder.create();
//                mDialog.show();
//            }
//        });

       //count
       final TextView txtCount = findViewById(R.id.txt_Count);
        txtCount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final   MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(AddLogMed.this)
                        .minValue(1)
                        .maxValue(10)
                        .defaultValue(1)
                        .backgroundColor(Color.WHITE)
                        .separatorColor(Color.TRANSPARENT)
                        .textColor(Color.BLACK)
                        .textSize(20)
                        .enableFocusability(false)
                        .wrapSelectorWheel(true)
                        .build();

                new AlertDialog.Builder(AddLogMed.this, R.style.AlertDialogTheme)
                        .setTitle("Count")
                        .setView(numberPicker)
                        .setNegativeButton("ADD",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Snackbar.make(findViewById(R.id.txt_Count), "You picked : " + numberPicker.getValue(), Snackbar.LENGTH_LONG).show();
                                txtCount.setText(String.valueOf(numberPicker.getValue()));

                            }
                        })
                     .show();

            }

        });


        //period
        txtperiod = findViewById(R.id.txt_Period);
        final String[] pickerVals = new String[] { "Daily","Weekly", "Monthly" };

        txtperiod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               final NumberPicker picker = new NumberPicker(AddLogMed.this);
                picker.setMinValue(0);
                picker.setMaxValue(2);
                picker.setDisplayedValues( new String[] { "Daily","Weekly", "Monthly" } );

                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        int valuePicker1 = picker.getValue();

                    }
                });

                new AlertDialog.Builder(AddLogMed.this, R.style.AlertDialogTheme)
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
                                                Snackbar.make(findViewById(R.id.txt_Period), "You picked : " + pickerVals[picker.getValue()], Snackbar.LENGTH_LONG).show();
                                                txtperiod.setText(String.valueOf(pickerVals[picker.getValue()]));
                                            }
                        })

                        .show();

            }

        });



        //Unit
        txtunit = findViewById(R.id.txt_unit);

        final String[] pickerValsUnit = new String[] { "Pill","A", "B" };

        txtunit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final NumberPicker picker = new NumberPicker(AddLogMed.this);
                picker.setMinValue(0);
                picker.setMaxValue(2);
                picker.setDisplayedValues( new String[] { "Pill","A", "B" } );

                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        int valuePicker1 = picker.getValue();

                    }
                });

                new AlertDialog.Builder(AddLogMed.this,R.style.AlertDialogTheme)
                        .setTitle("Unit")
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
                                Snackbar.make(findViewById(R.id.txt_unit), "You picked : " + pickerValsUnit[picker.getValue()], Snackbar.LENGTH_LONG).show();
                                txtunit.setText(String.valueOf(pickerValsUnit[picker.getValue()]));
                            }
                        })
                        .show();

            }

        });

        //Repeat
//        final TextView txtrepeat = findViewById(R.id.txt_Repeat);
//        final String[] pickerValsRepeat = new String[] { "Every hour","Every two hours",
//                                                         "Every three hours","Every 4 hours",
//                                                         "Every 5 hours", "Every 6 hours","Every 8 hours","Every 12 hours"};
//
//        txtrepeat.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                final NumberPicker picker = new NumberPicker(AddLogMed.this);
//                picker.setMinValue(0);
//                picker.setMaxValue(7);
//                picker.setDisplayedValues( new String[] { "Every hour","Every two hours",
//                                                           "Every three hours","Every 4 hours",
//                                                           "Every 5 hours", "Every 6 hours","Every 8 hours","Every 12 hours" } );
//
//                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//                    @Override
//                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
//                        int valuePicker1 = picker.getValue();
//
//                    }
//                });
//
//                new AlertDialog.Builder(AddLogMed.this)
//                        .setTitle("Repeat")
//                        .setView(picker)
//                        .setNegativeButton("ADD",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                })
//                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Snackbar.make(findViewById(R.id.txt_Repeat), "You picked : " + pickerValsRepeat[picker.getValue()], Snackbar.LENGTH_LONG).show();
//                                txtrepeat.setText(String.valueOf(pickerValsRepeat[picker.getValue()]));
//                            }
//                        })
//                        .show();
//
//            }
//
//        });
    }
}
