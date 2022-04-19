package com.nakilnat.nakilnat.ui.myships.steps;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.WebViewFragment;

public class ShipStepOneFragment extends BaseFragment {

    private boolean nextable = false;
    Button next, navigateInsurance;
    private String iyzicoUrl = "https://www.iyzico.com/isim-icin/kurumsal-hesap-olustur?gclid=CjwKCAjw3cSSBhBGEiwAVII0Zz_iol_DNxJCSnmARrVJ0M-yPNIyryKzc0_Hp4ZteJ1OEUlMz8gcpxoCtTUQAvD_BwE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_ships_steps_one);
        bottomBarSetup(R.id.bottomMyShipping);
        initTopBarContents("Taşımalarım");
        initScreen();
    }

    private void initScreen() {
        next = findViewById(R.id.ship_start_ship);
        navigateInsurance = findViewById(R.id.ship_start_with_insurance);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "2.adıma geçiliyor.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShipStepOneFragment.this, ShipStepTwoFragment.class);
                startActivity(intent);
            }
        });

        navigateInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "2.adıma geçiliyor.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShipStepOneFragment.this, WebViewFragment.class);
                intent.putExtra("url", iyzicoUrl);
                intent.putExtra("title", "Sigorta Ödeme");
                startActivity(intent);
            }
        });
    }
}