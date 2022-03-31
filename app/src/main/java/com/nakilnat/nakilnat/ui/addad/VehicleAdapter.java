package com.nakilnat.nakilnat.ui.addad;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.VehiclesResponse;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    private ArrayList<VehiclesResponse> list;
    private Context context;
    int selectedPosition=-1;

    public VehicleAdapter(ArrayList<VehiclesResponse> list, Context context) {
        this.list = list;
        this.context = context;
        //this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_list, viewGroup, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        VehiclesResponse vehiclesResponse = list.get(position);
        holder.tvVehicleName.setText(vehiclesResponse.getName());

        if(selectedPosition==position){
            holder.tvVehicleName.setBackgroundDrawable( context.getResources().getDrawable(R.drawable.vehicle_item_bg) );
            holder.tvVehicleName.setTextColor(context.getResources().getColor(R.color.purple_500));
        }

        else{
            holder.tvVehicleName.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tvVehicleName.setTextColor(context.getResources().getColor(R.color.gray_700));
        }





        holder.tvVehicleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getSelectedItem() {
        return list.get(selectedPosition).getName();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvVehicleName;

        public ViewHolder(View view) {
            super(view);
            tvVehicleName = (TextView) view.findViewById(R.id.vehicleName);
        }

        public TextView getTextView() {
            return tvVehicleName;
        }
        

    }
}
