package com.nakilnat.nakilnat.ui.profile;

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
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.onboarding.LoginFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;
import com.nakilnat.nakilnat.R;


public class MySettingsFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    CardView accountPage, addressPage, invoicesPage, notificationPage, shippingAdsPage, offersPage,
            shippingPage, walletPage,messagesPage, settingsPage;
    CardView accountCardView, offersCardView, burdenCardView;
    RelativeLayout accountLayout, offersLayout, burdenLayout;
    ImageView accountArrow, offersArrow, burdenArrow;
    Group accountHiddenGroup, offersHiddenGroup, burdenHiddenGroup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_settings);

        bottomBarSetup();
        InitSubContents();


       /* accountCardView = findViewById(R.id.profile_settings_group);
        accountLayout = findViewById(R.id.profile_settings);
        accountArrow = findViewById(R.id.iconSettingsArrow);
        accountHiddenGroup = findViewById(R.id.settings_card_group);

        offersCardView = findViewById(R.id.profile_offers_group);
        offersLayout = findViewById(R.id.profile_offers);
        offersArrow = findViewById(R.id.iconOffersArrow);
        offersHiddenGroup = findViewById(R.id.offers_card_group);

        burdenCardView = findViewById(R.id.profile_burdens_group);
        burdenLayout = findViewById(R.id.profile_burdens);
        burdenArrow = findViewById(R.id.iconburdensArrow);
        burdenHiddenGroup = findViewById(R.id.burdens_card_group);

        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(0);

        accountLayout.setOnClickListener(view -> {
            if(accountHiddenGroup.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(accountCardView, autoTransition);
                accountHiddenGroup.setVisibility(View.GONE);
                accountArrow.setImageResource(R.drawable.ic_profile_arrow_down);
            }
            else {
                TransitionManager.beginDelayedTransition(accountCardView, new AutoTransition());
                accountHiddenGroup.setVisibility(View.VISIBLE);
                accountArrow.setImageResource(R.drawable.ic_profile_arrow_up);
            }
        });

        offersLayout.setOnClickListener(view -> {
            if(offersHiddenGroup.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(offersCardView, autoTransition);
                offersHiddenGroup.setVisibility(View.GONE);
                offersArrow.setImageResource(R.drawable.ic_profile_arrow_down);
            }
            else {
                TransitionManager.beginDelayedTransition(offersCardView, new AutoTransition());
                offersHiddenGroup.setVisibility(View.VISIBLE);
                offersArrow.setImageResource(R.drawable.ic_profile_arrow_up);
            }
        });

        burdenLayout.setOnClickListener(view -> {
            if(burdenHiddenGroup.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(burdenCardView, autoTransition);
                burdenHiddenGroup.setVisibility(View.GONE);
                burdenArrow.setImageResource(R.drawable.ic_profile_arrow_down);
            }
            else {
                TransitionManager.beginDelayedTransition(burdenCardView, new AutoTransition());
                burdenHiddenGroup.setVisibility(View.VISIBLE);
                burdenArrow.setImageResource(R.drawable.ic_profile_arrow_up);
            }
        });
*/
    }

    private void InitSubContents() {
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
        //endregion

        //region set click actions


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