package com.nakilnat.nakilnat.ui.profile.adress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private OnAdressListener mOnAdressListener;

    public AdressAdapter(List<MyAdressListResponse> list, Context context, OnAdressListener onAdressListener) {
        this.list = list;
        this.context = context;
        this.mOnAdressListener = onAdressListener;
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
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyAdressListResponse adressListResponse = list.get(position);

        holder.tvAdressHeader.setText(adressListResponse.getAdressHeader());
        holder.tvAdressShort.setText(adressListResponse.getAdressDistrict() + adressListResponse.getAdressCity());
        holder.tvAdressFull.setText(adressListResponse.getAdressStreet() + adressListResponse.getAdressApertment() + adressListResponse.getAdressDescription());
        holder.tvNameSurnamePhone.setText(adressListResponse.getAdressOfficial() + adressListResponse.getAdressPhoneNumber());
        holder.addressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdressListener.onRemoveClick(position);
            }
        });
        //TODO: burayı toplu click listener a ata
        holder.addressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdressListener.onEditClick(position);
            }
        });

        holder.tvAdressHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdressListener.onEditClick(position);
            }
        });

        holder.tvAdressShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAdressListener.onEditClick(position);
            }
        });
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

        @BindView(R.id.address_delete_icon)
        ImageView addressDelete;

        @BindView(R.id.address_edit_icon)
        ImageView addressEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnAdressListener {
        void onRemoveClick(int position);
        void onEditClick(int position);
    }

}
