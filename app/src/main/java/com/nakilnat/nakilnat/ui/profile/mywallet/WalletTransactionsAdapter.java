package com.nakilnat.nakilnat.ui.profile.mywallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.MyWalletTransactionsResponse;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletTransactionsAdapter extends RecyclerView.Adapter<WalletTransactionsAdapter.ViewHolder> {
    private List<MyWalletTransactionsResponse> list;
    private Context context;

    public WalletTransactionsAdapter(List<MyWalletTransactionsResponse> list, Context context) {
        this.list = list;
        this.context = context;
        //this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_wallet_transactions_list, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyWalletTransactionsResponse walletTransactions = list.get(position);

        holder.tvWalletTransactionDescription.setText(walletTransactions.getMyTransactionsState());
        holder.tvWalletTransactionAmount.setText(walletTransactions.getMyTransactionsEarn());
        holder.tvWalletTransactionTime.setText(walletTransactions.getMyTransactionsTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.wallet_transaction_description)
        TextView tvWalletTransactionDescription;

        @BindView(R.id.wallet_transaction_amount)
        TextView tvWalletTransactionAmount;

        @BindView(R.id.wallet_transaction_time)
        TextView tvWalletTransactionTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


