package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.onboarding.OnboardingFragment;


public class MySettingsFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    CardView permissionsCardView, permissionSubCardView, passwordTransactionsCardView, helpCardView, helpSubCardView, deleteAccountCardView, signOutCardView;
    ImageView permissionsArrow, helpArrow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_settings);
        bottomBarSetup();
        InitSubContents();
        navigationController();
    }

    private void InitSubContents() {
        permissionsCardView = (CardView) findViewById(R.id.settings_permission);
        permissionSubCardView = (CardView) findViewById(R.id.settings_permission_subview);
        passwordTransactionsCardView = (CardView) findViewById(R.id.settings_password);
        helpCardView = (CardView) findViewById(R.id.settings_help);
        helpSubCardView = (CardView) findViewById(R.id.settings_help_subview);
        deleteAccountCardView = (CardView) findViewById(R.id.settings_delete_account);
        signOutCardView = (CardView) findViewById(R.id.settings_signout);
        permissionsArrow = (ImageView) findViewById(R.id.settings_permission_arrow);
        helpArrow = (ImageView) findViewById(R.id.settings_help_arrow);
    }

    private void navigationController() {
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(0);

        permissionsCardView.setOnClickListener(view -> {
            if (permissionSubCardView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(permissionsCardView, autoTransition);
                permissionSubCardView.setVisibility(View.GONE);
                permissionsArrow.setImageResource(R.drawable.ic_next);
            } else {
                TransitionManager.beginDelayedTransition(permissionsCardView, new AutoTransition());
                permissionSubCardView.setVisibility(View.VISIBLE);
                permissionsArrow.setImageResource(R.drawable.ic_profile_arrow_down);
            }
        });

        passwordTransactionsCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MySettingsFragment.this, UpdatePasswordFragment.class);
            startActivity(intent);
        });

        helpCardView.setOnClickListener(view -> {
            if (helpSubCardView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(helpCardView, autoTransition);
                helpSubCardView.setVisibility(View.GONE);
                helpArrow.setImageResource(R.drawable.ic_next);
            } else {
                TransitionManager.beginDelayedTransition(helpCardView, new AutoTransition());
                helpSubCardView.setVisibility(View.VISIBLE);
                helpArrow.setImageResource(R.drawable.ic_profile_arrow_down);
            }
        });

        deleteAccountCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MySettingsFragment.this, OnboardingFragment.class);
            startActivity(intent);
        });

        signOutCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MySettingsFragment.this, OnboardingFragment.class);
            startActivity(intent);
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

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomMyShipping:
                intent = new Intent(MySettingsFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MySettingsFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(MySettingsFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomHome:
                intent = new Intent(MySettingsFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}