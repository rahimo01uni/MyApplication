package com.health.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;

public class AddReminderActivity extends AppCompatActivity {
    String[] listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        //alert radio button
        listItems = getResources().getStringArray(R.array.unit_item);
        final TextView txtunit = findViewById(R.id.txt_unit);


        txtunit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddReminderActivity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txtunit.setText(listItems[i]);
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

       //count
       final TextView txtCount = findViewById(R.id.txt_Count);
        txtCount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final   MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(AddReminderActivity.this)
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

                new AlertDialog.Builder(AddReminderActivity.this)
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
        final TextView txtperiod = findViewById(R.id.txt_Period);
        final String[] pickerVals = new String[] { "Daily","Weekly", "Monthly" };

        txtperiod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               final NumberPicker picker = new NumberPicker(AddReminderActivity.this);
                picker.setMinValue(0);
                picker.setMaxValue(2);
                picker.setDisplayedValues( new String[] { "Daily","Weekly", "Monthly" } );

                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        int valuePicker1 = picker.getValue();

                    }
                });

                new AlertDialog.Builder(AddReminderActivity.this)
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


        //Repeat
        final TextView txtrepeat = findViewById(R.id.txt_Repeat);
        final String[] pickerValsRepeat = new String[] { "Every hour","Every two hours",
                                                         "Every three hours","Every 4 hours",
                                                         "Every 5 hours", "Every 6 hours","Every 8 hours","Every 12 hours"};

        txtrepeat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final NumberPicker picker = new NumberPicker(AddReminderActivity.this);
                picker.setMinValue(0);
                picker.setMaxValue(7);
                picker.setDisplayedValues( new String[] { "Every hour","Every two hours",
                                                           "Every three hours","Every 4 hours",
                                                           "Every 5 hours", "Every 6 hours","Every 8 hours","Every 12 hours" } );

                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        int valuePicker1 = picker.getValue();

                    }
                });

                new AlertDialog.Builder(AddReminderActivity.this)
                        .setTitle("Repeat")
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
                                Snackbar.make(findViewById(R.id.txt_Repeat), "You picked : " + pickerValsRepeat[picker.getValue()], Snackbar.LENGTH_LONG).show();
                                txtrepeat.setText(String.valueOf(pickerValsRepeat[picker.getValue()]));
                            }
                        })
                        .show();

            }

        });
    }
}