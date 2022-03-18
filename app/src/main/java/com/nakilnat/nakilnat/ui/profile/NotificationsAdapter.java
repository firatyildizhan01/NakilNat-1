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
import com.nakilnat.nakilnat.models.response.Notification;
import com.nakilnat.nakilnat.models.response.Reviews;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private ArrayList<Notification> list;
    private Context context;

    public NotificationsAdapter(ArrayList<Notification> list, Context context) {
        this.list = list;
        this.context = context;
        //this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_notifications_list, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Notification notification = list.get(position);

        holder.tvNotificationDescription.setText(notification.getNotificationDescription());
        holder.tvNotificationSubject.setText(notification.getNotificationSubject());
        holder.tvNotificationTime.setText(notification.getNotificationTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.notification_description)
        TextView tvNotificationDescription;

        @BindView(R.id.notification_time)
        TextView tvNotificationTime;

        @BindView(R.id.notification_subject)
        TextView tvNotificationSubject;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Notification notification) {
            tvNotificationDescription.setText(notification.getNotificationDescription());
            tvNotificationSubject.setText(notification.getNotificationSubject());
            tvNotificationTime.setText(notification.getNotificationTime());
        }
    }
}

