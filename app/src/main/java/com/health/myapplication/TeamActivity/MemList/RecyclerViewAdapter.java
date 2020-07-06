package com.health.myapplication.TeamActivity.MemList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.health.myapplication.Database.selections_model;
import com.health.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<selections_model> models,list;

   public  RecyclerViewAdapter(Activity context, ArrayList<selections_model> models,ArrayList<selections_model> list){
       this.context = context;
       this.models = models;
       this.list=list;
   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(context).inflate(R.layout.teamlist_layout,viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final selections_model model = models.get(position);

          holder.txtRemainder.setText(model.getName());
          if(model.isChecked())holder.checkBox.setChecked(true); else holder.checkBox.setChecked(false);
          holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  holder.checkBox.setChecked(isChecked);
                  model.setChecked(isChecked);
                  if (isChecked)list.add(model); else list.remove(model);
              }
          });
  //      holder.txtLeftDays.setText(model.getLeftdays());
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txtRemainder, txtLeftDays;
        private ConstraintLayout parent;
        CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            txtRemainder = itemView.findViewById(R.id.txt_drug);
            checkBox=itemView.findViewById(R.id.checkBox);
        }
    }
}
