package com.nakilnat.nakilnat.ui.profile.settings;

import android.os.Bundle;

import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.WebViewFragment;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;


public class AggreementsFragment extends BaseFragment {
    BottomNavigationView bottomBar;
    CardView bottomFab;
    CardView agreementCookie, agreementPrivaryPolicy, agreementKvkk, agreementUserPolicy;
    TextView topBarText;
    ImageView topBarBack;
    AHBottomNavigation  bottomNavigation;
    private static final String agreementKvkkUrl = "https://nakilnat.com/sayfa?link=Ki%C5%9Fisel%20Verilerin%20Korunmas%C4%B1";
    private static final String agreementCookieUrl = "https://nakilnat.com/sayfa?link=cerezpolitikasi";
    private static final String agreementTermsOfUseUrl = "https://nakilnat.com/sayfa?link=kullanimKosullari";
    private static final String agreementPrivaryPolicyUrl = "https://nakilnat.com/sayfa?link=gizlilikpolitikasi";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agreements);
        bottomBarSetup2(R.id.bottomProfile);
        initTopBarContents("Sözleşmeler");
        InitSubContents();
    }

    private void InitSubContents() {
        //region initialize sub menus
        agreementCookie = (CardView) findViewById(R.id.agreement_cookie);
        agreementKvkk = (CardView) findViewById(R.id.agreement_kvkk);
        agreementUserPolicy = (CardView) findViewById(R.id.agreement_user_policy);
        agreementPrivaryPolicy = (CardView) findViewById(R.id.agrement_privary_policy);

        agreementCookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AggreementsFragment.this, WebViewFragment.class);
                intent.putExtra("url", agreementCookieUrl);
                intent.putExtra("title", "Çerez Politikası");
                startActivity(intent);
            }
        });

        agreementKvkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AggreementsFragment.this, WebViewFragment.class);
                intent.putExtra("url", agreementKvkkUrl);
                intent.putExtra("title", "KVKK");
                startActivity(intent);
            }
        });

        agreementUserPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AggreementsFragment.this, WebViewFragment.class);
                intent.putExtra("url", agreementTermsOfUseUrl);
                intent.putExtra("title", "Kullanım Koşulları");
                startActivity(intent);
            }
        });

        agreementPrivaryPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AggreementsFragment.this, WebViewFragment.class);
                intent.putExtra("url", agreementPrivaryPolicyUrl);
                intent.putExtra("title", "Gizlilik Politikası");
                startActivity(intent);
            }
        });
    }

    public void bottomBarSetup2(int bottomProfile) {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Anasayfa", R.drawable.home_stuff, R.color.grey);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Taşımalarım", R.drawable.location_icon, R.color.grey);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(" ",  R.color.grey);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("İlan Ekle", R.drawable.plus_icon, R.color.grey);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Profilim", R.drawable.user_stuff, R.color.grey);
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.colorTransparent));
        bottomNavigation.setSoundEffectsEnabled(true);

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
// Change colors
        bottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.bottomSheetInActiveColor));


        // Customize notification (title, background, typeface) bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        // Add or remove notification for each item bottomNavigation.setNotification("1", 3);
        // OR




        // Set current item programmatically

        // Set listeners
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(AggreementsFragment.this, HomePageFragment.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(AggreementsFragment.this, MyShipsFragment.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(AggreementsFragment.this, ApplicationPageFragment.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(AggreementsFragment.this, AddAdFragment.class);
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(AggreementsFragment.this, ProfilePageFragment.class);
                    startActivity(intent);
                    break;
            }

            return true;
        });
    }

    private void removeItemsUnderline(BottomNavigationView bottomNavigationView) {
        for (int i = 0; i <  bottomNavigationView.getMenu().size(); i++) {
            MenuItem item = bottomNavigationView.getMenu().getItem(i);
            item.setTitle(item.getTitle().toString());
        }
    }

    private void underlineMenuItem(MenuItem item) {
        SpannableString content = new SpannableString(item.getTitle());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        item.setTitle(content);

    }
}