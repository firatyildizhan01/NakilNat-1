package com.nakilnat.nakilnat.ui.myships.steps;

import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;


public class ShipStepOneFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    CardView bottomFab;
    TextView navigationBarTitle;
    private AppCompatButton btninsuranceNo, btninsuranceYes;
    private boolean nextable = false;
    Button next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_ships_steps_one);
        navigationBarTitle = (TextView)findViewById(R.id.top_bar_title);
        navigationBarTitle.setText("Taşımalarım");
        bottomBarSetup();
        initScreen();


    }

    private void initScreen() {
        btninsuranceNo = findViewById(R.id.ship_insuranceNo);
        btninsuranceYes = findViewById(R.id.ship_insurance_money);
        next = findViewById(R.id.ship_start_ship);

        btninsuranceNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btninsuranceNo.setBackgroundColor(getResources().getColor(R.color.purple_500));
                btninsuranceNo.setTextColor(getResources().getColor(R.color.white));
                btninsuranceYes.setBackgroundColor(getResources().getColor(R.color.white));
                btninsuranceYes.setTextColor(getResources().getColor(R.color.purple_500));
                nextable = true;
            }
        });

        btninsuranceYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btninsuranceYes.setBackgroundColor(getResources().getColor(R.color.purple_500));
                btninsuranceYes.setTextColor(getResources().getColor(R.color.white));
                btninsuranceNo.setBackgroundColor(getResources().getColor(R.color.white));
                btninsuranceNo.setTextColor(getResources().getColor(R.color.purple_500));
                nextable = true;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nextable){
                    Toast.makeText(getApplicationContext(), "Lütfen seçim yapınız.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "2.adıma geçiliyor.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ShipStepOneFragment.this, ShipStepTwoFragment.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void bottomBarSetup() {
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomBar.setItemIconTintList(null);
        bottomBar.setSelectedItemId(R.id.bottomMyShipping);
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
                Intent intent = new Intent(ShipStepOneFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(ShipStepOneFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(ShipStepOneFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(ShipStepOneFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;

        }
    }

}