package com.nakilnat.nakilnat.ui.myships;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.core.view.WindowCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.onboarding.LoginFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;
import com.nakilnat.nakilnat.R;


public class MyShipsFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    CardView bottomFab;
    CardView continuePage, successPage, cancelPage;
    TextView navigationBarTitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_ships);

        navigationBarTitle = (TextView)findViewById(R.id.top_bar_title);
        navigationBarTitle.setText("Taşımalarım");

        bottomBarSetup();
        InitSubContents();


        continuePage = findViewById(R.id.my_ships_continue);
        successPage = findViewById(R.id.my_ships_success);
        cancelPage = findViewById(R.id.my_ships_cancel);
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(0);

        continuePage.setOnClickListener(view -> {
            Intent intent = new Intent(MyShipsFragment.this, ContinueShipsFragment.class);
            startActivity(intent);
        });

        successPage.setOnClickListener(view -> {
            Intent intent = new Intent(MyShipsFragment.this, ContinueShipsFragment.class);
            startActivity(intent);
        });

        cancelPage.setOnClickListener(view -> {
            Intent intent = new Intent(MyShipsFragment.this, ContinueShipsFragment.class);
            startActivity(intent);
        });
    }

    private void InitSubContents() {
        //region initialize sub menus
        continuePage = (CardView) findViewById(R.id.my_ships_continue);
        successPage = (CardView) findViewById(R.id.my_ships_success);
        cancelPage = (CardView) findViewById(R.id.my_ships_cancel);

        continuePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyShipsFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
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
                Intent intent = new Intent(MyShipsFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(MyShipsFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MyShipsFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(MyShipsFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;

        }
    }

}