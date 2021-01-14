package com.example.sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList event_id,event_name,event_date,event_location,event_des;


    CustomAdapter(Activity activity,Context context, ArrayList event_id,ArrayList event_name,ArrayList event_date,ArrayList event_location,ArrayList event_des){

        this.activity = activity;
        this.context = context;
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_location= event_location;
        this.event_des = event_des;




    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {




        holder.eve_id_txt.setText(String.valueOf(event_id.get(position)));
        holder.eve_name_txt.setText(String.valueOf(event_name.get(position)));
        holder.eve_date_txt.setText(String.valueOf(event_date.get(position)));
        holder.eve_loc_txt.setText(String.valueOf(event_location.get(position)));
        holder.eve_des_txt.setText(String.valueOf(event_des.get(position)));

        holder.mainLayout.setOnClickListener((view) -> {


                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(event_id.get(position)));
                intent.putExtra("name",String.valueOf(event_name.get(position)));
                intent.putExtra("date",String.valueOf(event_date.get(position)));
                intent.putExtra("location",String.valueOf(event_location.get(position)));
                intent.putExtra("description",String.valueOf(event_des.get(position)));
                activity.startActivityForResult(intent,1);


        });

    }

    @Override
    public int getItemCount() {
        return event_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eve_id_txt,eve_name_txt,eve_date_txt,eve_loc_txt,eve_des_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eve_id_txt = itemView.findViewById(R.id.eve_id_txt);
            eve_name_txt = itemView.findViewById(R.id.eve_name_txt);
            eve_date_txt = itemView.findViewById(R.id.eve_date_txt);
            eve_loc_txt = itemView.findViewById(R.id.eve_loc_txt);
            eve_des_txt = itemView.findViewById(R.id.eve_des_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }


}
