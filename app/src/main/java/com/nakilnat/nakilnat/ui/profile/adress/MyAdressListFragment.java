package com.nakilnat.nakilnat.ui.profile.adress;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.RemoveAdressRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.MyAdressListResponse;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdressListFragment extends AppCompatActivity {

    BottomNavigationView bottomBar;
    CardView bottomFab;
    TextView topBarText, topBarRightText;
    ImageView topBarBack, topBarNotification;
    private List<MyAdressListResponse> adressList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_adress);
        topBarInit();
        bottomBarSetup();
        myAdressListCallBack(createRequest() );
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myAdressListCallBack(DefaultRequest defaultRequest) {
        Call<List<MyAdressListResponse>> call = ApiClient.getApiClient().myAdressList(defaultRequest);

        call.enqueue(new Callback<List<MyAdressListResponse>>() {
            @Override
            public void onResponse(Call<List<MyAdressListResponse>> call, Response<List<MyAdressListResponse>> response) {
                adressList = response.body();
                if (response != null && !adressList.isEmpty()) {
                    Toast.makeText(MyAdressListFragment.this, "Adres bilgilerim sağlandı", Toast.LENGTH_LONG).show();
                    //setting adapter and listview
                    AdressAdapter adapter = new AdressAdapter(adressList, MyAdressListFragment.this);
                    RecyclerView listview = findViewById(R.id.adress_list);
                    adapter.getItemCount();
                    listview.setAdapter(adapter);
                    listview.setLayoutManager(new LinearLayoutManager(MyAdressListFragment.this));
                } else {
                    Toast.makeText(MyAdressListFragment.this, "Adres bilgilerim sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyAdressListResponse>> call, Throwable t) {
                Toast.makeText(MyAdressListFragment.this, "Servis hatası!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public RemoveAdressRequest deleteRequest(String id) {
        RemoveAdressRequest removeAdressRequest = new RemoveAdressRequest();
        removeAdressRequest.setToken("korayaman");
        removeAdressRequest.setId(id);
        return removeAdressRequest;
    }

    public void removeAdressCallBack(RemoveAdressRequest removeAdressRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().removeAdress(removeAdressRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {
                    Toast.makeText(MyAdressListFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MyAdressListFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void topBarInit() {
        topBarText = findViewById(R.id.top_bar_title);
        topBarRightText = findViewById(R.id.top_bar_right_title);
        topBarRightText.setVisibility(View.VISIBLE);
        topBarRightText.setText("Adres ekle");
        topBarRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAdressListFragment.this, AddAdressFragment.class);
                startActivity(intent);
            }
        });
        topBarText.setText("Adreslerim");
        topBarBack = findViewById(R.id.top_bar_back);
        topBarNotification = findViewById(R.id.top_bar_notifications);
        topBarNotification.setVisibility(View.GONE);
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

        bottomFab = findViewById(R.id.fab);
        bottomFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomBar.getMenu().setGroupCheckable(0, false, true);
                bottomBar.getMenu().setGroupCheckable(1, false, true);
                bottomBar.getMenu().setGroupCheckable(2, false, true);
                bottomBar.getMenu().setGroupCheckable(3, false, true);
                Intent intent = new Intent(MyAdressListFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(MyAdressListFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomMyShipping:
                intent = new Intent(MyAdressListFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MyAdressListFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                finish();
                break;
        }
    }
}