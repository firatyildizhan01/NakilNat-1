package com.nakilnat.nakilnat.ui.profile.mywallet;

import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.response.TotalAmountResponse;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyWalletFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    CardView addMoneyCardView, transactionsCardView;
    TextView topBarText, totalAmount;
    ImageView topBarBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_wallet);
        totalAmount = findViewById(R.id.my_wallet_total_money);

        bottomBarSetup();
        InitSubContents();
        myTotalAmount(createRequest());

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

    private void InitSubContents() {
        //region initialize sub menus
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Cüzdanım");
        topBarBack = findViewById(R.id.top_bar_back);
        topBarBack.setVisibility(View.VISIBLE);
        topBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void bottomBarSetup() {
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomBar.setItemIconTintList(null);
        bottomBar.setSelectedItemId(R.id.bottomProfile);
        bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                GoBottomMenuIntent(item.getItemId());
                return true;

            }
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

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomMyShipping:
                intent = new Intent(MyWalletFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MyWalletFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(MyWalletFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomHome:
                intent = new Intent(MyWalletFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}