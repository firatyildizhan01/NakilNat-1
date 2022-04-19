package com.nakilnat.nakilnat.ui.profile.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.MessagesList;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    private ArrayList<MessagesList> list;
    private Context context;

    public MessagesAdapter(ArrayList<MessagesList> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_message_list, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessagesList messagesList = list.get(position);

        holder.tvUserName.setText(messagesList.getName());
        holder.tvMessageDetail.setText(messagesList.getLastMessages());
        holder.tvMessageLastTime.setText(messagesList.getMessageTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_message_user_name)
        TextView tvUserName;

        @BindView(R.id.item_message_detail)
        TextView tvMessageDetail;

        @BindView(R.id.item_message_time)
        TextView tvMessageLastTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void filterList(List<MessagesList> messagesLists) {
        list = (ArrayList<MessagesList>) messagesLists;
        notifyDataSetChanged();

    }
}