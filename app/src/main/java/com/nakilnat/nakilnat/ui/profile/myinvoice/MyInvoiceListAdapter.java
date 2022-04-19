package com.nakilnat.nakilnat.ui.profile.myinvoice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;

import com.nakilnat.nakilnat.models.response.MyInvoiceResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInvoiceListAdapter extends RecyclerView.Adapter<MyInvoiceListAdapter.ViewHolder> {
    private List<MyInvoiceResponse> list;
    private Context context;
    private OnInvoiceListener mOnInvoiceListener;

    public MyInvoiceListAdapter(List<MyInvoiceResponse> list, Context context, OnInvoiceListener onAdressListener) {
        this.list = list;
        this.context = context;
        this.mOnInvoiceListener = onAdressListener;
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
        MyInvoiceResponse myInvoiceResponse = list.get(position);

        holder.tvAdressHeader.setText(myInvoiceResponse.getAdres());
        holder.tvAdressShort.setText(myInvoiceResponse.getVergiDaire());
        holder.tvAdressFull.setText(myInvoiceResponse.getVergiNo());
        holder.tvNameSurnamePhone.setText(myInvoiceResponse.getUnvan());
        holder.addressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnInvoiceListener.onRemoveClick(position);
            }
        });
        //TODO: burayı toplu click listener a ata
        holder.addressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnInvoiceListener.onEditClick(position);
            }
        });

        holder.tvAdressHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnInvoiceListener.onEditClick(position);
            }
        });

        holder.tvAdressShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnInvoiceListener.onEditClick(position);
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

    public interface OnInvoiceListener {
        void onRemoveClick(int position);
        void onEditClick(int position);
    }

}
