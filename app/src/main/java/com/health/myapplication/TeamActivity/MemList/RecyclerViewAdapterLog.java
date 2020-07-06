package com.health.myapplication.TeamActivity.MemList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.health.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewAdapterLog extends RecyclerView.Adapter<RecyclerViewAdapterLog.ViewHolder> {

    private Activity context;
    private ArrayList<add_logMem_model> models;

    public  RecyclerViewAdapterLog(Activity context, ArrayList<add_logMem_model> models){
        this.context = context;
        this.models = models;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(context).inflate(R.layout.loglist_layout,viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
  holder.txtRemainder.setText(models.get(position).getName());
  holder.checkBox.setChecked(models.get(position).isChecked());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked) models.get(position).setChecked(true);else models.get(position).setChecked(false);
            }
        });
    }




    @Override
    public int getItemCount() {
        return models.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtRemainder, txtLeftDays;
        private ConstraintLayout parent;
        private CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            txtRemainder = itemView.findViewById(R.id.txt_drug);
             checkBox=itemView.findViewById(R.id.checkBox2);
        }
    }
}
