package com.nakilnat.nakilnat.ui.profile.myads;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import androidx.cardview.widget.CardView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;

public class MyAdsFragment extends BaseFragment {
    CardView continuePage, successPage, cancelPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_ads);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("İlanlarım");
        InitSubContents();
    }

    private void InitSubContents() {
        //region initialize sub menus
        continuePage = (CardView) findViewById(R.id.my_ads_continue);
        successPage = (CardView) findViewById(R.id.my_ads_success);
        cancelPage = (CardView) findViewById(R.id.my_ads_cancel);

        continuePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAdsFragment.this, ContinueAdsFragment.class);
                startActivity(intent);
            }
        });

        successPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAdsFragment.this, ContinueAdsFragment.class);
                startActivity(intent);
            }
        });

        cancelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAdsFragment.this, ContinueAdsFragment.class);
                startActivity(intent);
            }
        });
    }
}