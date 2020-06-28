package com.health.myapplication.utils;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.general_model;
import com.health.myapplication.R;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

    public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
        private Activity context;
        private List<general_model> models;
        private  String fr;
        private DatabaseHelper db;
        public Adapter(Activity context, List<general_model> models, DatabaseHelper db) {
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
       sleep_lay(holder,models.get(position));
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

            public myViewHolder(View itemView) {
                super(itemView);
                ring=(ToggleButton)itemView.findViewById(R.id.toggle);
                sleep=(ConstraintLayout)itemView.findViewById(R.id.sleep_layout);
                 l1=(LinearLayout)itemView.findViewById(R.id.linearLayout);
              txtTitle=(TextView)itemView.findViewById(R.id.txt_drug);
                start_time=(TextView)itemView.findViewById(R.id.txt_StartTimeS);
                end_time=(TextView)itemView.findViewById(R.id.txt_EndTimeS);
                save=(Button)itemView.findViewById(R.id.btn_saveS);

            }
        }
        public void setData(ArrayList<general_model> food) {
            models = food;
            notifyDataSetChanged();
        }
     public  void sleep_lay(final myViewHolder holder, final general_model item){
         if(holder.ring.isChecked()){
             holder.sleep.setVisibility(View.GONE);
             holder.ring.setChecked(false);
         } else {
             holder.sleep.setVisibility(View.VISIBLE);
             holder.ring.setChecked(true);

             holder.start_time.setText(item.getSleep_log().getSleepTime());
             holder.end_time.setText(item.getSleep_log().getWakeUpTime());
         }
         holder.start_time.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                         holder.start_time.setText(hourOfDay+":"+minutes);
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
                         holder.end_time.setText(hourOfDay+":"+minutes);
                     }
                 }, 0, 0, false);
                 timePickerDialog.show();
             }

         });

         holder.save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.d("Result",""+holder.start_time.getText().toString()+" ");
                 item.getSleep_log().setSleepTime(holder.start_time.getText().toString());
                 item.getSleep_log().setWakeUpTime(holder.end_time.getText().toString());
                 if(db.EditSleepReminder(item.getSleep_log().getId(),holder.start_time.getText().toString(),holder.end_time.getText().toString()))
                     sleep_lay(holder,item);

             }
         });
     }
    }


