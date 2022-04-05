package com.nakilnat.nakilnat.ui.profile.myads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.TransportList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.ViewHolder> {
    private ArrayList<TransportList> list;
    private Context context;
    private OnAdsListener mOnAdsListener;

    public AdsAdapter(ArrayList<TransportList> list, Context context, OnAdsListener onAdsListener) {
        this.list = list;
        this.context = context;
        this.mOnAdsListener = onAdsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_ships_list, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransportList transportList = list.get(position);

        holder.tvTransportMoney.setText(transportList.getMoney());
        holder.tvSourceCity.setText(transportList.getSourceCity());
        holder.tvDestinationCity.setText(transportList.getDestinyCity());
        holder.tvThingText.setText(transportList.getThing());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.listLayout.getVisibility() == View.VISIBLE) {
                    holder.listLayout.setVisibility(View.GONE);
                    holder.detailLayout.setVisibility(View.VISIBLE);
                } else {
                    holder.listLayout.setVisibility(View.VISIBLE);
                    holder.detailLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_ships_money)
        TextView tvTransportMoney;

        @BindView(R.id.item_ships_source_city)
        TextView tvSourceCity;

        @BindView(R.id.item_ships_destination_city)
        TextView tvDestinationCity;

        @BindView(R.id.item_ships_thing)
        TextView tvThingText;

        @BindView(R.id.item_ships_detail_layout)
        LinearLayout detailLayout;

        @BindView(R.id.item_ships_list_layout)
        LinearLayout listLayout;

        OnAdsListener onAdsListener;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onAdsListener = onAdsListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        onAdsListener.onAdClick(getAdapterPosition());
        }
    }

    public interface  OnAdsListener {
        void onAdClick(int position);
    }
}
