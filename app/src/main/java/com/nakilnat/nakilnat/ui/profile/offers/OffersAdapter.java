package com.nakilnat.nakilnat.ui.profile.offers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.TransportList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {
    private ArrayList<TransportList> list;
    private Context context;

    public OffersAdapter(ArrayList<TransportList> list, Context context) {
        this.list = list;
        this.context = context;
        //this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_offers_list, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_offers_money)
        TextView tvTransportMoney;

        @BindView(R.id.item_offers_source_city)
        TextView tvSourceCity;

        @BindView(R.id.item_offers_destination_city)
        TextView tvDestinationCity;

        @BindView(R.id.item_offers_status_text)
        TextView tvStatusText;

        @BindView(R.id.item_offers_thing)
        TextView tvThingText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
