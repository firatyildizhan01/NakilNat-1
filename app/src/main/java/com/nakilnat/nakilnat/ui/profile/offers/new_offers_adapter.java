package com.nakilnat.nakilnat.ui.profile.offers;

import android.app.AlertDialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;

import java.util.List;

public class new_offers_adapter extends RecyclerView.Adapter<new_offers_adapter.MyViewHolder> {

    private static final String TAG = "new_offers_adapter";

    boolean isRunning = false;
    private List<String> list;
    private Context context;
    private boolean isNewTask;
    int size = 0;
    int lastlist = 0;

    public new_offers_adapter(List<String> list, Context con, boolean isNewTask) {
        this.list = list;
        lastlist = list.size();
        this.context = con;
        this.isNewTask = isNewTask;
    /*    mPlayer = MediaPlayer.create(context, R.raw.alert_tone);
        mPlayer.isLooping();*/
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_offers_fragment, parent, false);
        return new MyViewHolder(itemView);
    }





    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
       /* final TodayTask obj = list.get(position);
        String user = obj.getPatientDetails().getFullname();
        if (user != null && !user.isEmpty() && !user.equals("null")) {
            holder.name.setText(user);
            //   holder.age.setText(obj.getA);
            holder.time.setText(obj.getTestTime());
            holder.address.setText(obj.getPatientDetails().getAddress());
            holder.gender.setText(obj.getPatientDetails().getGender());
            int age = obj.getPatientDetails().getAge();
            if (obj.getPatientDetails().getAge() != null)
                holder.age.setText(obj.getPatientDetails().getAge().toString() + ",");
        }*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout acceptRejectLayout;
        Button acceptBtn, rejectBtn;
        private CardView taskCard;
        private TextView name, age, gender, time,address;
        private ImageView shopAvatar;

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


