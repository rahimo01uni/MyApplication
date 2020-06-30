package com.health.myapplication.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;
import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.MedicationDbHelber;
import com.health.myapplication.Database.ReminderOverviewDbHelper;
import com.health.myapplication.Database.general_model;
import com.health.myapplication.Database.medication_model;
import com.health.myapplication.Database.sleep_model;
import com.health.myapplication.R;
import com.health.myapplication.Reminder.AddReminderActivity;
import com.health.myapplication.Reminder.Reminder;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

    public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
        private Activity context;
        private List<general_model> models;
        private  String fr;
        private ReminderOverviewDbHelper db;
        public Adapter(Activity context, List<general_model> models, ReminderOverviewDbHelper db) {
            this.context = context;
            this.models = models;
            this.db=db;
        }

        @Override
        public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v= LayoutInflater.from(context).inflate(R.layout.list_layout,viewGroup, false);
            return new myViewHolder(v);
        }


        @Override
        public void onBindViewHolder(final myViewHolder holder, final int position) {

   /*     Glide.with(context)
                .load(context.getResources()
                        .getIdentifier(models.get(position).getImg(), "drawable", context.getPackageName())).asBitmap().into(holder.icon);*/

            // we should change here later
holder.txtTitle.setText(models.get(position).getType());

holder.l1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
     if(models.get(position).getType().equals("sleep"))  sleep_lay(holder,models.get(position).getSleep_log());
     else med_lay(holder,models.get(position).getMedication_log());
    }
});
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        class myViewHolder extends RecyclerView.ViewHolder {
            private TextView txtTitle, txtCategory, time,date;
            private LinearLayout l1;

            private ConstraintLayout sleep;
            private ToggleButton ring;
            TextView start_time,end_time;
            Button save;
            //private ImageView pic;

            //med
            Button saveMed;
            EditText txt_medicationname;
            TextView txt_unit;
            TextView txt_Count;
            TextView txt_SDate;
            TextView txt_EDate;
            TextView txt_Period;
            TextView txt_Time;

            RecyclerView    recyclerView;
            ArrayList times;
            AdapterTime adapter;
            DatePickerDialog picker;
            ImageView delete;
            MedicationDbHelber mdb;
            TextView txtperiod;
            TextView desc;
            CardView medView;

            public myViewHolder(View itemView) {
                super(itemView);
                ring=(ToggleButton)itemView.findViewById(R.id.toggle);
                sleep=(ConstraintLayout)itemView.findViewById(R.id.sleep_layout);
                 l1=(LinearLayout)itemView.findViewById(R.id.linearLayout);
              txtTitle=(TextView)itemView.findViewById(R.id.txt_drug);
                start_time=(TextView)itemView.findViewById(R.id.txt_StartTimeS);
                end_time=(TextView)itemView.findViewById(R.id.txt_EndTimeS);
                save=(Button)itemView.findViewById(R.id.btn_saveS);
                //med
                times=new ArrayList();
                delete=itemView.findViewById(R.id.delete);
                txt_medicationname = itemView.findViewById(R.id.txt_medicationname);
                txt_Time=itemView.findViewById(R.id.txt_Time);
                txt_unit=itemView.findViewById(R.id.txt_unit);
                txt_Count = itemView.findViewById(R.id.txt_Count);
                txt_SDate = itemView.findViewById(R.id.txt_SDate);
                txt_EDate = itemView.findViewById(R.id.txt_EDate);
                txt_Period =itemView. findViewById(R.id.txt_Period);
                recyclerView=(RecyclerView)itemView.findViewById(R.id.recyclerView);
                desc=itemView.findViewById(R.id.txt_desc);
                saveMed=itemView.findViewById(R.id.btn_save);
                medView=itemView.findViewById(R.id.cardView);

            }
        }
        public void setData(ArrayList<general_model> food) {
            models = food;
            notifyDataSetChanged();
        }
     void sleep_lay(final myViewHolder holder, final sleep_model item){
            final Calendar timeSleep=Calendar.getInstance();
         final Calendar timeWake=Calendar.getInstance();
            if(holder.ring.isChecked()){
             holder.sleep.setVisibility(View.GONE);
             holder.ring.setChecked(false);
         } else {
             holder.sleep.setVisibility(View.VISIBLE);
             holder.ring.setChecked(true);
           timeSleep.setTimeInMillis(Long.parseLong(item.getSleepTime()));
           timeWake.setTimeInMillis(Long.parseLong(item.getWakeUpTime()));

             holder.start_time.setText(TimeFormat(timeSleep.get(Calendar.HOUR_OF_DAY),timeSleep.get(Calendar.MINUTE)));


             holder.end_time.setText(TimeFormat(timeWake.get(Calendar.HOUR_OF_DAY),timeWake.get(Calendar.MINUTE)));
         }
         holder.start_time.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                         timeSleep.set(timeSleep.get(Calendar.YEAR), timeSleep.get(Calendar.MONTH),
                                 timeSleep.get(Calendar.DATE),hourOfDay,minutes,0);

                         holder.start_time.setText(TimeFormat(hourOfDay,minutes));
                     }
                 }, 0, 0, false);
                 timePickerDialog.show();
             }
         });
         holder.end_time.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                         timeWake.set(timeWake.get(Calendar.YEAR), timeWake.get(Calendar.MONTH),
                                 timeWake.get(Calendar.DATE),hourOfDay,minutes,0);

                         holder.end_time.setText(TimeFormat(hourOfDay,minutes));
                     }
                 }, 0, 0, false);
                 timePickerDialog.show();
             }

         });

         holder.save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.d("Result",""+holder.start_time.getText().toString()+" ");
                 item.setSleepTime(""+timeSleep.getTimeInMillis());
                 item.setWakeUpTime(""+timeWake.getTimeInMillis());
                 if(db.EditSleepReminder(item.getId(),""+timeSleep.getTimeInMillis(),""+timeWake.getTimeInMillis()))
                     sleep_lay(holder,item);

             }
         });
     }
        Calendar start,end,time;
        AdapterTime adapter;
        DatePickerDialog picker;
     void  med_lay(final myViewHolder holder, final medication_model item)
     {
         if(holder.ring.isChecked()){
             holder.medView.setVisibility(View.GONE);
             holder.ring.setChecked(false);
         } else {
             holder.medView.setVisibility(View.VISIBLE);
             holder.ring.setChecked(true);


         }



       start=Calendar.getInstance();
       start.setTimeInMillis(Long.parseLong(item.getStartDate()));
         end=Calendar.getInstance();
         end.setTimeInMillis(Long.parseLong(item.getEndDate()));
        holder. txt_medicationname.setText(item.getMed_name());
         holder.recyclerView.setVisibility( View.VISIBLE);

         holder.txt_EDate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final Calendar cldr = Calendar.getInstance();
                 int day = cldr.get(Calendar.DAY_OF_MONTH);
                 int month = cldr.get(Calendar.MONTH);
                 int year = cldr.get(Calendar.YEAR);
                 // date picker dialog
                 picker = new DatePickerDialog(context,
                         new DatePickerDialog.OnDateSetListener() {
                             @Override
                             public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                 end.set(year,monthOfYear,dayOfMonth);
                                 holder.txt_EDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                             }
                         }, year, month, day);
                 picker.show();
             }
         });
         holder.txt_SDate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final Calendar cldr = Calendar.getInstance();
                 int day = cldr.get(Calendar.DAY_OF_MONTH);
                 int month = cldr.get(Calendar.MONTH);
                 int year = cldr.get(Calendar.YEAR);
                 // date picker dialog
                 picker = new DatePickerDialog(context,
                         new DatePickerDialog.OnDateSetListener() {
                             @Override
                             public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                 start.set(year,monthOfYear,dayOfMonth);
                                 holder.txt_SDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                             }
                         }, year, month, day);
                 picker.show();
             }
         });
        holder.txt_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                { TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        time = Calendar.getInstance();
                        time.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE),hourOfDay,minutes,0);
                       int i=0;
                       while ( time.getTimeInMillis()<=Calendar.getInstance().getTimeInMillis()) {
                           i++;
                           time.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE) + i, hourOfDay, minutes, 0);
                       }
                       item.getTimes().add(""+time.getTimeInMillis());

                        adapter.setData(  item.getTimes());
                    }
                }, 0, 0, false);
                timePickerDialog.show();}

            }
        });
       final   String[] listItems = context.getResources().getStringArray(R.array.unit_item);
         holder.txt_unit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                 mBuilder.setTitle("Choose an item");
                 mBuilder.setPositiveButton("Add", null);
                 mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         holder.txt_unit.setText(listItems[i]);
                         dialogInterface.dismiss();
                     }
                 });

                 AlertDialog mDialog = mBuilder.create();
                 mDialog.show();
             }
         });
         holder.txt_Count.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                 final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(context)
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

                 new AlertDialog.Builder(context)
                         .setTitle("Count")
                         .setView(numberPicker)
                         .setNegativeButton("ADD",
                                 new DialogInterface.OnClickListener() {
                                     public void onClick(DialogInterface dialog, int id) {
                                         dialog.cancel();
                                     }
                                 })
                         .setPositiveButton(context.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {

                                 holder.txt_Count.setText(String.valueOf(numberPicker.getValue()));

                             }
                         })
                         .show();

             }

         });

         holder.txt_Count.setText(item.getDose());
        holder.txt_unit.setText(item.getUnit());
        holder.txt_SDate.setText(start.get(Calendar.DATE)+"/"+start.get(Calendar.MONTH)+"/"+start.get(Calendar.YEAR));
         holder.txt_EDate.setText(end.get(Calendar.DATE)+"/"+end.get(Calendar.MONTH)+"/"+end.get(Calendar.YEAR));
         holder.txt_Period.setText(item.getFrequency());
         holder.desc.setText(item.getDescription());
      holder. saveMed.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              holder.medView.setVisibility(View.GONE);
              item.setMed_name(holder.txt_medicationname.getText().toString());
              item.setUnit(holder.txt_unit.getText().toString());
              item.setDose(holder.txt_Count.getText().toString());
              item.setDescription(holder.desc.getText().toString());
              item.setFrequency(holder.txt_Period.getText().toString());
              item.setStartDate(""+start.getTimeInMillis());
              item.setEndDate(""+end.getTimeInMillis());
              holder.txtTitle.setText(holder.txt_medicationname.getText().toString());
              db.EditMedReminder(item.getId(),holder.txt_medicationname.getText().toString(),holder.txt_unit.getText().toString(),holder.txt_Count.getText().toString(),""+start.getTimeInMillis(),
                      ""+end.getTimeInMillis(),holder.txt_Period.getText().toString(),holder.desc.getText().toString(),item.getTimes());
           Log.d("WTFUCKK",""+item.getTimes().size());
           med_lay(holder,item);
          }
      });
         adapter=new AdapterTime(context,item.getTimes());
//        Log.d("what",""+db.getReminders().get(db.getReminders().size()-1).getSleep_log().getSleepTime());

         holder.recyclerView.setLayoutManager(new GridLayoutManager(context,2));
         holder.recyclerView.setAdapter(adapter);
     }
private  String TimeFormat(int hourOfDay, int minutes){

    String hours=""+hourOfDay,
            minutess=""+minutes;
    if(hours.length()==1) hours="0"+hours;
    if(minutess.length()==1)minutess="0"+minutess;
         return hours+":"+minutess;
}
    }


