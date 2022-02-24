package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.onboarding.LoginFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;
import com.nakilnat.nakilnat.R;


public class ProfilePageFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    CardView accountCardView, offersCardView, burdenCardView;
    RelativeLayout accountLayout, offersLayout, burdenLayout;
    ImageView accountArrow, offersArrow, burdenArrow;
    Group accountHiddenGroup, offersHiddenGroup, burdenHiddenGroup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile_page);
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomBar.setSelectedItemId(R.id.miProfile);
        bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                GoBottomMenuIntent(item.getItemId());
                return true;

            }
        });

        accountCardView = findViewById(R.id.profile_settings_group);
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

    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.miHome:
                intent = new Intent(ProfilePageFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.miSearch:
                intent = new Intent(ProfilePageFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.miSettings:
                intent = new Intent(ProfilePageFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}