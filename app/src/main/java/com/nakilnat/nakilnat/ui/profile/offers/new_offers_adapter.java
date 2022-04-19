package com.nakilnat.nakilnat.ui.profile.offers;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import java.util.List;

public class new_offers_adapter extends RecyclerView.Adapter<new_offers_adapter.MyViewHolder> {

    private List<String> list;
    private Context context;

    public new_offers_adapter(List<String> list, Context con) {
        this.list = list;
        this.context = con;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_offers_fragment, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView taskCard;

        private MyViewHolder(View view) {
            super(view);
            taskCard = view.findViewById(R.id.root);
            taskCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (v.getId() == taskCard.getId()) {
                alertMessage(position);
            }
        }

        public void alertMessage(int p) {
            final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(context);
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View convertView = inflater.inflate(R.layout.pop_up_message, null);
            alertDialog.setView(convertView);
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
    }
}