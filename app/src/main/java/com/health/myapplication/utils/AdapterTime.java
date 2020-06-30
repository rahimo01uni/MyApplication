package com.health.myapplication.utils;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.general_model;
import com.health.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterTime extends RecyclerView.Adapter<AdapterTime.myViewHolder> {
    private Activity context;
    private List<String> models;
    private  String fr;
    private DatabaseHelper db;
    public AdapterTime(Activity context, List<String> models) {
        this.context = context;
        this.models = models;
        this.db=db;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.time_view,viewGroup, false);
        return new myViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {

/*     Glide.with(context)
            .load(context.getResources()
                    .getIdentifier(models.get(position).getImg(), "drawable", context.getPackageName())).asBitmap().into(holder.icon);*/

        // we should change here later
       Calendar sleep = Calendar.getInstance();
      // sleep.setTimeInMillis();
        sleep.setTimeInMillis(Long.parseLong(models.get(position)));
holder.txtTitle.setText(TimeFormat(sleep.get(Calendar.HOUR_OF_DAY),sleep.get(Calendar.MINUTE)));
holder.delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        models.remove(position);
        notifyDataSetChanged();
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
        ImageView delete;
        //private ImageView pic;

        public myViewHolder(View itemView) {
            super(itemView);
            txtTitle=(TextView)itemView.findViewById(R.id.time);
            delete=itemView.findViewById(R.id.delete);

        }
    }
    public void setData(ArrayList<String> times) {
        models = times;
        notifyDataSetChanged();
    }
    private  String TimeFormat(int hourOfDay, int minutes){

        String hours=""+hourOfDay,
                minutess=""+minutes;
        if(hours.length()==1) hours="0"+hours;
        if(minutess.length()==1)minutess="0"+minutess;
        return hours+":"+minutess;
    }
}


