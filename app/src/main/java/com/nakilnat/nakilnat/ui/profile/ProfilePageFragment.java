package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.adress.MyAdressListFragment;
import com.nakilnat.nakilnat.ui.profile.myads.MyAdsFragment;
import com.nakilnat.nakilnat.ui.profile.mywallet.MyWalletFragment;
import com.nakilnat.nakilnat.ui.profile.offers.MyOffersFragment;


public class ProfilePageFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    CardView bottomFab;
    TextView topBarText;
    CardView accountPage, addressPage, invoicesPage, notificationPage, shippingAdsPage, offersPage,
            shippingPage, walletPage,messagesPage, settingsPage, profilePicture;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile_page);

        bottomBarSetup();
        InitSubContents();
        navigationController();

    }

    private void InitSubContents() {
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Profil");
        //region initialize sub menus
        accountPage = (CardView) findViewById(R.id.profile_my_account);
        addressPage = (CardView) findViewById(R.id.profile_my_address);
        invoicesPage = (CardView) findViewById(R.id.profile_my_invoices);
        notificationPage = (CardView) findViewById(R.id.profile_notifications);
        shippingAdsPage = (CardView) findViewById(R.id.profile_my_shipping_ads);
        offersPage = (CardView) findViewById(R.id.profile_Offers);
        shippingPage = (CardView) findViewById(R.id.profile_my_shippings);
        walletPage = (CardView) findViewById(R.id.profile_my_wallet);
        messagesPage = (CardView) findViewById(R.id.profile_messsages);
        settingsPage = (CardView) findViewById(R.id.profile_my_settings);
        profilePicture = (CardView) findViewById(R.id.profile_picture);
        //endregion

        //region set click actions
        invoicesPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilePageFragment.this, MyInvoicesFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //endregion
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
                Intent intent = new Intent(ProfilePageFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void navigationController() {
        accountPage = findViewById(R.id.profile_my_account);
        addressPage = findViewById(R.id.profile_my_address);
        invoicesPage = findViewById(R.id.profile_my_invoices);
        notificationPage = findViewById(R.id.profile_notifications);
        shippingAdsPage = findViewById(R.id.profile_my_shipping_ads);
        offersPage = findViewById(R.id.profile_Offers);
        shippingPage = findViewById(R.id.profile_my_shippings);
        walletPage = findViewById(R.id.profile_my_wallet);
        messagesPage = findViewById(R.id.profile_messsages);
        settingsPage = findViewById(R.id.profile_my_settings);

        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(0);

        profilePicture.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, ReviewFragment.class);
            startActivity(intent);
        });

        accountPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyAccountFragment.class);
            startActivity(intent);
        });

        addressPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyAdressListFragment.class);
            startActivity(intent);
        });

        invoicesPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyInvoicesFragment.class);
            startActivity(intent);
        });

        notificationPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyNotificationsFragment.class);
            startActivity(intent);
        });

        shippingAdsPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyAdsFragment.class);
            startActivity(intent);
        });

        offersPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyOffersFragment.class);
            startActivity(intent);
        });

        shippingPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyShipsFragment.class);
            startActivity(intent);
        });

        walletPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyWalletFragment.class);
            startActivity(intent);
        });

        messagesPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MyInvoicesFragment.class);
            startActivity(intent);
        });

        settingsPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MySettingsFragment.class);
            startActivity(intent);
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(ProfilePageFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomMyShipping:
                intent = new Intent(ProfilePageFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(ProfilePageFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}