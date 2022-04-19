package com.nakilnat.nakilnat.ui.profile.offers;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import androidx.cardview.widget.CardView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;

public class MyOffersFragment extends BaseFragment {
    CardView sendOffers, incomingOffers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_offers);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Teklifler");
        InitSubContents();
    }

    private void InitSubContents() {
        //region initialize sub menus
        sendOffers = (CardView) findViewById(R.id.my_offers_send);
        incomingOffers = (CardView) findViewById(R.id.my_offers_incoming);

        sendOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyOffersFragment.this, IncomingOffersFragment.class);
                startActivity(intent);
            }
        });

        incomingOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyOffersFragment.this, IncomingOffersFragment.class);
                startActivity(intent);
            }
        });
    }
}