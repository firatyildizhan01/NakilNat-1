package com.nakilnat.nakilnat.ui.addad;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.profile.myads.ContinueAdsFragment;

public class AddAdLastStepFragment extends BaseFragment {

    Button next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_ad_last_step);
        bottomBarSetup(R.id.bottomAddAds);
        initTopBarContents("Yük İlanı Oluştur");
        initScreen();
    }

    private void initScreen() {
        next = (Button) findViewById(R.id.add_ad_last_step_button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "İlan onaya gönderildi.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddAdLastStepFragment.this, ContinueAdsFragment.class);
                startActivity(intent);

            }
        });
    }
}