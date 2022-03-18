package com.nakilnat.nakilnat.ui.profile;

import android.content.Context;
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
import com.nakilnat.nakilnat.models.response.Reviews;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private ArrayList<Reviews> list;
    private Context context;

    public ReviewsAdapter(ArrayList<Reviews> list, Context context) {
        this.list = list;
        this.context = context;
        //this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_reviews_list, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reviews reviews = list.get(position);

        holder.tvReviewDescription.setText(reviews.getDescription());
        holder.tvReviewTime.setText(reviews.getDate());
        holder.tvReviewNameSurname.setText(reviews.getNameSurname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_description)
        TextView tvReviewDescription;

        @BindView(R.id.review_time)
        TextView tvReviewTime;

        @BindView(R.id.review_name_surname)
        TextView tvReviewNameSurname;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(final Reviews appMerchant) {
            tvReviewDescription.setText(appMerchant.getDescription());
        }
    }
}

