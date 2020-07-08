package com.health.myapplication.utils;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.health.myapplication.Database.DatabaseHelper;
import com.health.myapplication.Database.OverviewDbHelper;
import com.health.myapplication.Database.medication_model;
import com.health.myapplication.Database.model_overview;
import com.health.myapplication.Database.sleep_model;
import com.health.myapplication.Database.symptom_model;
import com.health.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterOverview extends RecyclerView.Adapter<AdapterOverview.myViewHolder> {
    private Activity context;
    private List<model_overview> models;
    private  String fr;
    private OverviewDbHelper db;
    Calendar sleep;
    public AdapterOverview(Activity context, List<model_overview> models) {
        this.context = context;
        this.models = models;
        this.db=new OverviewDbHelper(context);
         sleep= Calendar.getInstance();
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_overview,viewGroup, false);
        return new myViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {



       sleep.setTimeInMillis(Long.parseLong(models.get(position).getTime()));
        holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if(models.get(position).getName().equals("Sleep")){
                  sleepLay(holder,models.get(position).getId());
                 } else  if(models.get(position).getName().equals("Mood")) symptomLay(holder,models.get(position).getId());
                     else medLay(holder,models.get(position).getId());
            }
        });
holder.title.setText(models.get(position).getName());
holder.time.setText(TimeFormat(sleep.get(Calendar.HOUR_OF_DAY),sleep.get(Calendar.MINUTE)));
        Glide.with(context).load(R.drawable.gg2).into( holder.img);

switch (models.get(position).getName()){
    case "Sleep":
        Glide.with(context).load(R.drawable.sleep).into( holder.ava);
        break;
    case "Mood":
        Glide.with(context).load(R.drawable.ic_mood_24px).into( holder.ava);
        break;
        default:
            Glide.with(context).load(R.drawable.ic_medicinecolor).into( holder.ava);
            break;
}

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        private TextView title, time;
        private LinearLayout l;
        CardView sleepC,medC,symptomC;
        private ConstraintLayout sleep;
        private ToggleButton ring;
        TextView sleep_time,wake_time,quality,duration,nightWoke,description;
        Button save;
        ImageView ava;
        TextView med_dose,med_unit,med_note;
        TextView mood,symptoms,notes;
        ImageView img;
        //private ImageView pic;

        public myViewHolder(View itemView) {
            super(itemView);
            l=itemView.findViewById(R.id.linearLayout3);
            title=(TextView)itemView.findViewById(R.id.title);
            time=itemView.findViewById(R.id.time);
            ava=itemView.findViewById(R.id.img_drug);
             ring=itemView.findViewById(R.id.button);
             img=itemView.findViewById(R.id.img_guide);
            //sleep
            sleepC=itemView.findViewById(R.id.cardViewSleep);
            sleep_time=itemView.findViewById(R.id.txt_SleepTime);
            wake_time=itemView.findViewById(R.id.txt_WakeUpTime);
            quality=itemView.findViewById(R.id.txt_QualityOfSleep);
            duration=itemView.findViewById(R.id.txt_Duration);
            nightWoke=itemView.findViewById(R.id.nightWake);
            description=itemView.findViewById(R.id.txt_SleepNote);

             //med
            medC=itemView.findViewById(R.id.cardViewMedication);
            med_dose=itemView.findViewById(R.id.txt_Count);
            med_unit=itemView.findViewById(R.id.txt_unit);
            med_note=itemView.findViewById(R.id.txt_desc);

            //symptom
            symptomC=itemView.findViewById(R.id.cardViewSympthom);
            mood=itemView.findViewById(R.id.txt_mood);
            symptoms=itemView.findViewById(R.id.txt_Sympthom);
            notes=itemView.findViewById(R.id.txt_descSym);
        }
    }
    public void setData(ArrayList<model_overview> times) {
        models = times;
        notifyDataSetChanged();
    }
    void sleepLay(final myViewHolder holder,String id){
        sleep_model item=db.get_sleep(id);
        Calendar date=Calendar.getInstance();
if(!item.getSleepTime().equals("?"))
{
    date.setTimeInMillis(Long.parseLong(item.getSleepTime()));
        holder.sleep_time.setText(TimeFormat(date.get(Calendar.HOUR_OF_DAY),date.get(Calendar.MINUTE)));}

        if(holder.ring.isChecked()){holder.sleepC.setVisibility(View.GONE);holder.ring.setChecked(false);} else { holder.ring.setChecked(true);holder.sleepC.setVisibility(View.VISIBLE);}
       date.setTimeInMillis(Long.parseLong(item.getWakeUpTime()));
        holder.wake_time.setText(TimeFormat(date.get(Calendar.HOUR_OF_DAY),date.get(Calendar.MINUTE)));
        holder.quality.setText(item.getQualityOfSleep());
        holder.duration.setText(item.getDuration());
        holder.nightWoke.setText(item.getNightWokeUp());
        holder.description.setText(item.getNote());


    }

 void medLay(final myViewHolder holder,String id)
 {
     if(holder.ring.isChecked()){holder.medC.setVisibility(View.GONE);holder.ring.setChecked(false);} else { holder.ring.setChecked(true);holder.medC.setVisibility(View.VISIBLE);}
 Log.d("id1",id);
     medication_model item=db.get_medic(id);
     holder.med_dose.setText(item.getDose());
     holder.med_unit.setText(item.getUnit());
     holder.med_note.setText(item.getDescription());
 }
void symptomLay(final myViewHolder holder,String id)
{
    if(holder.ring.isChecked()){holder.symptomC.setVisibility(View.GONE);holder.ring.setChecked(false);} else { holder.ring.setChecked(true);holder.symptomC.setVisibility(View.VISIBLE);}
    symptom_model item=db.get_symptom(id);
    holder.mood.setText(item.getMood());
    holder.symptoms.setText(item.getSymptom());
    holder.notes.setText(item.getNote());


}
    private  String TimeFormat(int hourOfDay, int minutes){

        String hours=""+hourOfDay,
                minutess=""+minutes;
        if(hours.length()==1) hours="0"+hours;
        if(minutess.length()==1)minutess="0"+minutess;
        return hours+":"+minutess;
    }
}


