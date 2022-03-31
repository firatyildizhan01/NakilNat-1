package com.nakilnat.nakilnat.ui.addad;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.InsurancesResponse;
import com.nakilnat.nakilnat.models.response.VehiclesResponse;

import java.util.ArrayList;

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.ViewHolder> {
    private ArrayList<InsurancesResponse> list;
    private Context context;
    int selectedPosition=-1;

    public InsuranceAdapter(ArrayList<InsurancesResponse> list, Context context) {
        this.list = list;
        this.context = context;
        //this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_insurance_list, viewGroup, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        InsurancesResponse insurancesResponse = list.get(position);
        holder.tvName.setText(insurancesResponse.getPrice());

        if(insurancesResponse.getName().equals("ray")){
            holder.tvImageView.setBackgroundResource(R.drawable.ic_insurance_ray);
        }
        if(insurancesResponse.getName().equals("allianze")){
            holder.tvImageView.setBackgroundResource(R.drawable.ic_insurance_allianz);
        }
        if(insurancesResponse.getName().equals("axa")){
            holder.tvImageView.setBackgroundResource(R.drawable.ic_insurance_axa);
        }
        if(insurancesResponse.getName().equals("neova")){
            holder.tvImageView.setBackgroundResource(R.drawable.ic_insurance_neova);
        }
        if(selectedPosition==position){
            holder.tvLayout.setBackgroundDrawable( context.getResources().getDrawable(R.drawable.vehicle_item_bg) );
            holder.tvName.setTextColor(context.getResources().getColor(R.color.purple_500));
        }

        else{
            holder.tvLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tvName.setTextColor(context.getResources().getColor(R.color.gray_700));
        }





        holder.tvLayout.setOnClickListener(new View.OnClickListener() {
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

        private final TextView tvName;
        private final ImageView tvImageView;
        private final LinearLayout tvLayout;

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.insurancePrice);
            tvImageView = (ImageView) view.findViewById(R.id.imageInsurance);
            tvLayout = (LinearLayout) view.findViewById(R.id.insuranceLayout);
        }

        public TextView getTextView() {
            return tvName;
        }
        public ImageView getImageView() {
            return tvImageView;
        }
        public LinearLayout getLinearLayout() {
            return tvLayout;
        }
    }
}
