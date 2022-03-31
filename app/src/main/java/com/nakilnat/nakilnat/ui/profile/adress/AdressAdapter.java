package com.nakilnat.nakilnat.ui.profile.adress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.MyAdressListResponse;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdressAdapter extends RecyclerView.Adapter<AdressAdapter.ViewHolder> {
    private List<MyAdressListResponse> list;
    private Context context;

    public AdressAdapter(List<MyAdressListResponse> list, Context context) {
        this.list = list;
        this.context = context;
        //this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_address_list, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyAdressListResponse adressListResponse = list.get(position);

        holder.tvAdressHeader.setText(adressListResponse.getAdressHeader());
        holder.tvAdressShort.setText(adressListResponse.getAdressDistrict() + adressListResponse.getAdressCity());
        holder.tvAdressFull.setText(adressListResponse.getAdressStreet() + adressListResponse.getAdressApertment() + adressListResponse.getAdressDescription());
        holder.tvNameSurnamePhone.setText(adressListResponse.getAdressOfficial() + adressListResponse.getAdressPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adress_header)
        TextView tvAdressHeader;

        @BindView(R.id.adress_short_adress)
        TextView tvAdressShort;

        @BindView(R.id.adress_full_adress)
        TextView tvAdressFull;

        @BindView(R.id.adress_name_surname_phone)
        TextView tvNameSurnamePhone;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
