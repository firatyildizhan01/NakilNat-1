package com.nakilnat.nakilnat.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.MyReviewsResponse;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private List<MyReviewsResponse> list;
    private Context context;

    public ReviewsAdapter(List<MyReviewsResponse> list, Context context) {
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
        MyReviewsResponse reviews = list.get(position);

        holder.tvReviewDescription.setText(reviews.getReviewDescription());
        holder.tvReviewTime.setText(reviews.getReviewTime());
        holder.tvReviewNameSurname.setText(reviews.getReviewUser());
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
    }
}

