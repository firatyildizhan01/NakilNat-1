package com.nakilnat.nakilnat.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.profile.MyInvoicesFragment;
import com.nakilnat.nakilnat.ui.profile.adress.MyAdressListFragment;
import com.nakilnat.nakilnat.ui.profile.map.MapFragment;
import com.nakilnat.nakilnat.ui.profile.mywallet.MyWalletFragment;

public class OnboardingFragment extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_onboarding);

        img = findViewById(R.id.iv_splash);
        img.animate().alpha(1).setDuration(0);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(OnboardingFragment.this, LoginFragment.class);
                startActivity(dsp);
                finish();
            }
        },4000);
    }
}
