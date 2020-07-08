package com.health.myapplication.Log;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.health.myapplication.Database.model_overview;
import com.health.myapplication.Database.selections_model;
import com.health.myapplication.R;

import java.util.ArrayList;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<selections_model> models;
    ArrayList<String>list;

   public SymptomAdapter(Context context, ArrayList<selections_model> models, ArrayList<String> list){
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            set_check(holder);
          //holder.checkBox.setChecked(false);
          holder.txtRemainder.setText( models.get(position).getName());
        Log.d("lengtht",""+list.size());
        holder.checkBox.setTag(position);
          if(models.get(position).isChecked())holder.checkBox.setChecked(true); else holder.checkBox.setChecked(false);

          holder.checkBox.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(holder.checkBox.isChecked())models.get(position).setChecked(true); else models.get(position).setChecked(false);
              }
          });
         /* holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  holder.checkBox.setChecked(isChecked);
                  models.get(position).setChecked(isChecked);
              if (isChecked)list.add( models.get(position).getName()); else list.remove( models.get(position).getName());
              }
          });*/
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
    public void setData(ArrayList<selections_model> list) {
        models = list;
        notifyDataSetChanged();
    }
    void set_check(final ViewHolder holder)
    {
        for (selections_model model:models)
        if(model.isChecked())holder.checkBox.setChecked(true); else holder.checkBox.setChecked(false);
    }
}
