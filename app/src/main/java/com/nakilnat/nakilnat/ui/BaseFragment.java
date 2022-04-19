package com.nakilnat.nakilnat.ui;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.MyNotificationsFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;
import com.nakilnat.nakilnat.ui.profile.adress.MyAdressListFragment;

public abstract class BaseFragment extends AppCompatActivity {

    TextView topBarText, topBarRightText;
    ImageView topBarBack, notification;
    BottomNavigationView bottomBar;
    CardView bottomFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_base);
        initTopBarContents("Ayarlar");
        bottomBarSetup(R.id.bottomProfile);
    }

    public void initTopBarContents(String navigationTitle) {
        topBarText = (TextView) findViewById(R.id.top_bar_title);
        topBarText.setText(navigationTitle);
        topBarBack = findViewById(R.id.top_bar_back);
        topBarBack.setVisibility(VISIBLE);
        topBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        notification = (ImageView) findViewById(R.id.top_bar_notifications);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseFragment.this, MyNotificationsFragment.class);
                startActivity(intent);
            }
        });
    }

    public void initRightTitle(String title) {
        topBarRightText = findViewById(R.id.top_bar_right_title);
        topBarRightText.setVisibility(VISIBLE);
        notification.setVisibility(GONE);
        topBarRightText.setText(title);
    }

    public void bottomBarSetup(int bottomItemView) {
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomBar.setItemIconTintList(null);
        bottomBar.setSelectedItemId(bottomItemView);
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
                Intent intent = new Intent(BaseFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    public void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomMyShipping:
                intent = new Intent(BaseFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(BaseFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(BaseFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomHome:
                intent = new Intent(BaseFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}
