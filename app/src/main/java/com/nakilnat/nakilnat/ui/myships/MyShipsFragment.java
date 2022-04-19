package com.nakilnat.nakilnat.ui.myships;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;

public class MyShipsFragment extends BaseFragment {

    CardView continuePage, successPage, cancelPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_ships);
        bottomBarSetup(R.id.bottomMyShipping);
        initTopBarContents("Taşımalarım");
        InitSubContents();

        continuePage = findViewById(R.id.my_ships_continue);
        successPage = findViewById(R.id.my_ships_success);
        cancelPage = findViewById(R.id.my_ships_cancel);
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(0);

        continuePage.setOnClickListener(view -> {
            Intent intent = new Intent(MyShipsFragment.this, ContinueShipsFragment.class);
            startActivity(intent);
        });

        successPage.setOnClickListener(view -> {
            Intent intent = new Intent(MyShipsFragment.this, ContinueShipsFragment.class);
            startActivity(intent);
        });

        cancelPage.setOnClickListener(view -> {
            Intent intent = new Intent(MyShipsFragment.this, ContinueShipsFragment.class);
            startActivity(intent);
        });
    }

    private void InitSubContents() {
        //region initialize sub menus
        continuePage = (CardView) findViewById(R.id.my_ships_continue);
        successPage = (CardView) findViewById(R.id.my_ships_success);
        cancelPage = (CardView) findViewById(R.id.my_ships_cancel);

        continuePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyShipsFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
            }
        });
    }
}