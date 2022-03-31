package com.nakilnat.nakilnat.ui.myships;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.TransportList;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.profile.myads.MyAdsFragment;

import java.util.ArrayList;


public class ContinueShipsFragment extends AppCompatActivity {

    BottomNavigationView bottomBar;
    CardView bottomFab;
    TextView topBarText, continueShips;
    ImageView topBarBack;
    private ArrayList<TransportList> transportList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_continue_ships);
        topBarInit();
        pageInit();
        bottomBarSetup();

        continueShips = (TextView) findViewById(R.id.my_continue_ships_text);
        continueShips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinueShipsFragment.this, ShipsStepsFragment.class);
                startActivity(intent);
            }
        });
    }

    private void pageInit() {
        transportList = new ArrayList<>();
        transportList.add(new TransportList("Kayseri", "Manisa", "500 ₺", "Demir", "Taşıma devam ediyor"));
        transportList.add(new TransportList("Adana", "Rize", "1500 ₺", "Pamuk", "Taşıma tamamlandı"));
        transportList.add(new TransportList("Giresun", "İzmir", "6500 ₺", "Marul", "Taşıma başlamadı"));

        //setting adapter and listview
        ShipsAdapter adapter = new ShipsAdapter(transportList, this);
        RecyclerView listview = findViewById(R.id.my_ships_continue_list);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void topBarInit() {
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Devam eden taşımalarım");

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
                Intent intent = new Intent(ContinueShipsFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(ContinueShipsFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomMyShipping:
                intent = new Intent(ContinueShipsFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(ContinueShipsFragment.this, MyAdsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                finish();
                break;
        }
    }

}