package com.nakilnat.nakilnat.ui.profile.mywallet;

import android.os.Bundle;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.response.TotalAmountResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyWalletFragment extends BaseFragment {
    CardView addMoneyCardView, transactionsCardView;
    TextView totalAmount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_wallet);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Cüzdanım");
        myTotalAmount(createRequest());
        totalAmount = findViewById(R.id.my_wallet_total_money);
        addMoneyCardView = findViewById(R.id.my_wallet_add_money);
        transactionsCardView = findViewById(R.id.my_wallet_transactions);
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(0);

        addMoneyCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MyWalletFragment.this, AddMoneyFragment.class);
            startActivity(intent);
        });

        transactionsCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MyWalletFragment.this, MyWalletTransactionsFragment.class);
            startActivity(intent);
        });
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myTotalAmount(DefaultRequest defaultRequest) {
        Call<TotalAmountResponse> call = ApiClient.getApiClient().totalAmount(defaultRequest);

        call.enqueue(new Callback<TotalAmountResponse>() {
            @Override
            public void onResponse(Call<TotalAmountResponse> call, Response<TotalAmountResponse> response) {
                TotalAmountResponse totalAmountResponse = response.body();
                if (totalAmountResponse != null && !totalAmountResponse.getAmount().isEmpty()) {
                    Toast.makeText(MyWalletFragment.this, "Tutar bilgisi sağlandı", Toast.LENGTH_LONG).show();
                    totalAmount.setText(totalAmountResponse.getAmount() + " TL");
                } else {
                    Toast.makeText(MyWalletFragment.this, "Hesap bilgileri sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TotalAmountResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}