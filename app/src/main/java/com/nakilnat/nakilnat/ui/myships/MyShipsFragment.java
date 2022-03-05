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
    RelativeLayout accountLayout, offersLayout, burdenLayout;
    ImageView accountArrow, offersArrow, burdenArrow;
    Group accountHiddenGroup, offersHiddenGroup, burdenHiddenGroup;
    TextView navigationBarTitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_ships);

        navigationBarTitle = (TextView)findViewById(R.id.top_bar_title);
        navigationBarTitle.setText("Taşımalarım");

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
        continuePage = (CardView) findViewById(R.id.my_ships_continue);
        successPage = (CardView) findViewById(R.id.my_ships_success);
        cancelPage = (CardView) findViewById(R.id.my_ships_cancel);
        //endregion

        //region set click actions


        //endregion



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