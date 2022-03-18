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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdressListFragment extends AppCompatActivity {

    BottomNavigationView bottomBar;
    CardView bottomFab;
    TextView topBarText, topBarRightText;
    ImageView topBarBack, topBarNotification;
    private ArrayList<MyAdressListResponse> adressList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_adress);
        topBarInit();
        pageInit();
        bottomBarSetup();
    }

    private void pageInit() {
        adressList = new ArrayList<>();
        adressList.add(new MyAdressListResponse("46", "46", "Ev", "Kahramanmaraş", "Onikişubat", "3003", "Akif İnan Mahallesi", "Burak apart", "1", "1", "Burak apartmanı"," 46000 posta kodu"));
        adressList.add(new MyAdressListResponse("46", "46", "Ev", "Kahramanmaraş", "Onikişubat", "3003", "Akif İnan Mahallesi", "Burak apart", "1", "1", "Burak apartmanı"," 46000 posta kodu"));
        adressList.add(new MyAdressListResponse("46", "46", "Ev", "Kahramanmaraş", "Onikişubat", "3003", "Akif İnan Mahallesi", "Burak apart", "1", "1", "Burak apartmanı"," 46000 posta kodu"));

        //setting adapter and listview
        AdressAdapter adapter = new AdressAdapter(adressList, this);
        RecyclerView listview = findViewById(R.id.adress_list);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(this));
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void adressListCallBack(DefaultRequest defaultRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().myAdressList(defaultRequest);

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