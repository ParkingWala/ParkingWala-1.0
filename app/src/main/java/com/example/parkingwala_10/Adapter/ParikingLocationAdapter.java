package com.example.parkingwala_10.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkingwala_10.R;
import com.example.parkingwala_10.model.Parking;

import java.util.ArrayList;

public class ParikingLocationAdapter extends RecyclerView.Adapter<ParikingLocationAdapter.MyViewHolder> {


    Context context;
    ArrayList<Parking> list;

    public ParikingLocationAdapter(Context context, ArrayList<Parking> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.park_location_list_item,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Parking p=list.get(position);
        holder.location_name.setText(p.getParking_name());
        holder.location_address.setText(p.getAddress());
        holder.amount.setText("Rs. "+p.getAmount());
        String complete_from_time = p.getFrom_Hr()+":"+p.getFrom_Min();
        String complete_to_time = p.getTo_Hr()+":"+p.getTo_Min();
        String total_time= complete_from_time +" to "+complete_to_time;
        holder.time.setText("Time - "+total_time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView location_name,location_address,amount,time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            location_name=itemView.findViewById(R.id.parking_name);
            location_address=itemView.findViewById(R.id.parking_location_address);
            amount=itemView.findViewById(R.id.parking_charges);
            time=itemView.findViewById(R.id.parking_time);
        }
    }
}
