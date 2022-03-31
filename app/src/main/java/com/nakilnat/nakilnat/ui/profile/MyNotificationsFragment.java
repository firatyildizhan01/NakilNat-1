package com.nakilnat.nakilnat.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import com.nakilnat.nakilnat.models.response.MyNotificationsResponse;
import com.nakilnat.nakilnat.models.response.Notification;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyNotificationsFragment extends AppCompatActivity {

    BottomNavigationView bottomBar;
    CardView bottomFab;
    TextView topBarText;
    ImageView topBarBack;
    private List<MyNotificationsResponse> notificationList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_notifications);
        topBarInit();
        bottomBarSetup();
        myNotificationsCallBack(createRequest());
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myNotificationsCallBack(DefaultRequest defaultRequest) {
        Call<List<MyNotificationsResponse>> call = ApiClient.getApiClient().myNotifications(defaultRequest);

        call.enqueue(new Callback<List<MyNotificationsResponse>>() {
            @Override
            public void onResponse(Call<List<MyNotificationsResponse>> call, Response<List<MyNotificationsResponse>> response) {
                notificationList = response.body();
                if (response != null && !notificationList.isEmpty()) {
                    Toast.makeText(MyNotificationsFragment.this, "Bilgilendirmelerim bilgisi sağlandı", Toast.LENGTH_LONG).show();
                    NotificationsAdapter adapter = new NotificationsAdapter(notificationList, MyNotificationsFragment.this);
                    RecyclerView listview = findViewById(R.id.notification_list);
                    adapter.getItemCount();
                    listview.setAdapter(adapter);
                    listview.setLayoutManager(new LinearLayoutManager(MyNotificationsFragment.this));
                } else {
                    Toast.makeText(MyNotificationsFragment.this, "Değerlendirmelerim bilgisi sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyNotificationsResponse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void topBarInit() {
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Bildirimlerim");

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

        bottomFab = findViewById(R.id.fab);
        bottomFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomBar.getMenu().setGroupCheckable(0, false, true);
                bottomBar.getMenu().setGroupCheckable(1, false, true);
                bottomBar.getMenu().setGroupCheckable(2, false, true);
                bottomBar.getMenu().setGroupCheckable(3, false, true);
                Intent intent = new Intent(MyNotificationsFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(MyNotificationsFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomMyShipping:
                intent = new Intent(MyNotificationsFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MyNotificationsFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                finish();
                break;
        }
    }

}