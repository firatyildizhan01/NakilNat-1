package com.nakilnat.nakilnat.ui.profile.mywallet;

import android.os.Bundle;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.response.MyWalletTransactionsResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyWalletTransactionsFragment extends BaseFragment {

    private List<MyWalletTransactionsResponse> walletTransactionsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_wallet_transactions);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Cüzdan Hareketleri");
        myWalletTransactionsCallBack(createRequest());
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myWalletTransactionsCallBack(DefaultRequest defaultRequest) {
        Call<List<MyWalletTransactionsResponse>> call = ApiClient.getApiClient().myWalletTransactions(defaultRequest);

        call.enqueue(new Callback<List<MyWalletTransactionsResponse>>() {
            @Override
            public void onResponse(Call<List<MyWalletTransactionsResponse>> call, Response<List<MyWalletTransactionsResponse>> response) {
                walletTransactionsList = response.body();
                if (response != null && !walletTransactionsList.isEmpty()) {
                    Toast.makeText(MyWalletTransactionsFragment.this, "Cüzdan hareketlerim bilgisi sağlandı", Toast.LENGTH_LONG).show();
                    WalletTransactionsAdapter adapter = new WalletTransactionsAdapter(walletTransactionsList, MyWalletTransactionsFragment.this);
                    RecyclerView listview = findViewById(R.id.wallet_transaction_list);
                    adapter.getItemCount();
                    listview.setAdapter(adapter);
                    listview.setLayoutManager(new LinearLayoutManager(MyWalletTransactionsFragment.this));
                } else {
                    Toast.makeText(MyWalletTransactionsFragment.this, "Cüzdan hareketlerim bilgisi sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyWalletTransactionsResponse>> call, Throwable t) {
                Toast.makeText(MyWalletTransactionsFragment.this, "Servis hatası!", Toast.LENGTH_LONG).show();
            }
        });
    }
}