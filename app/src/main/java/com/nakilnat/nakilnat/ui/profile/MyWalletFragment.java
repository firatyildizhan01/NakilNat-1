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
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.onboarding.LoginFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;
import com.nakilnat.nakilnat.R;


public class MyWalletFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    CardView addMoneyCardView, offersCardView, burdenCardView;
    ImageView accountArrow, offersArrow, burdenArrow;
    TextView topBarText;
    ImageView topBarBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_wallet);

        bottomBarSetup();
        InitSubContents();

        addMoneyCardView = findViewById(R.id.my_wallet_add_money);
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(0);

        addMoneyCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MyWalletFragment.this, AddMoneyFragment.class);
            startActivity(intent);
        });

    }

    private void InitSubContents() {
        //region initialize sub menus
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Cüzdanım");
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
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomMyShipping:
                intent = new Intent(MyWalletFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MyWalletFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(MyWalletFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomHome:
                intent = new Intent(MyWalletFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}