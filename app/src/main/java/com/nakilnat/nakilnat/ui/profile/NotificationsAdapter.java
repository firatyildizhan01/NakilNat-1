package com.nakilnat.nakilnat.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.MyNotificationsResponse;
import com.nakilnat.nakilnat.models.response.Notification;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<MyNotificationsResponse> list;
    private Context context;

    public NotificationsAdapter(List<MyNotificationsResponse> list, Context context) {
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
        MyNotificationsResponse notification = list.get(position);

        holder.tvNotificationDescription.setText(notification.getNotificationDescription());
        holder.tvNotificationSubject.setText(notification.getNotificationHeader());
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

    public void removeNotificationItem(int position) {
        list.remove(position);
        this.notifyItemRemoved(position);
    }
}

