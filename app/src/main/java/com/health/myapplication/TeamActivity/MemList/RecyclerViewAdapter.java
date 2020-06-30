package com.health.myapplication.TeamActivity.MemList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.health.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Activity context;
    private List<CardModel> models;

   public  RecyclerViewAdapter(Activity context, List<CardModel> models){
       this.context = context;
       this.models = models;
   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(context).inflate(R.layout.teamlist_layout,viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardModel model = models.get(position);
        holder.txtRemainder.setText(model.getRemider());
        holder.txtLeftDays.setText(model.getLeftdays());
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txtRemainder, txtLeftDays;
        private ConstraintLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            txtRemainder = itemView.findViewById(R.id.txt_drug);
            txtLeftDays = itemView.findViewById(R.id.txt_drug_instruction);
        }
    }
}
