package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.adress.MyAdressListFragment;
import com.nakilnat.nakilnat.ui.profile.messages.MessagesListFragment;
import com.nakilnat.nakilnat.ui.profile.myads.MyAdsFragment;
import com.nakilnat.nakilnat.ui.profile.myinvoice.MyInvoiceListFragment;
import com.nakilnat.nakilnat.ui.profile.mywallet.MyWalletFragment;
import com.nakilnat.nakilnat.ui.profile.offers.IncomingOffersFragment;
import com.nakilnat.nakilnat.ui.profile.settings.MySettingsFragment;


public class ProfilePageFragment extends BaseFragment {
    CardView accountPage, addressPage, invoicesPage, notificationPage, shippingAdsPage, offersPage,
            shippingPage, walletPage,messagesPage, settingsPage, profilePicture;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile_page);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Profil");
        InitSubContents();
        navigationController();
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
        profilePicture = (CardView) findViewById(R.id.profile_picture);
    }

    private void navigationController() {
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
            Intent intent = new Intent(ProfilePageFragment.this, MyInvoiceListFragment.class);
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
            Intent intent = new Intent(ProfilePageFragment.this, IncomingOffersFragment.class);
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
            Intent intent = new Intent(ProfilePageFragment.this, MessagesListFragment.class);
            startActivity(intent);
        });

        settingsPage.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePageFragment.this, MySettingsFragment.class);
            startActivity(intent);
        });
    }
}