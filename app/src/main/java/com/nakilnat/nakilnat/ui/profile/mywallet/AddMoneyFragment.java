package com.nakilnat.nakilnat.ui.profile.mywallet;

import android.os.Bundle;
import android.widget.EditText;
import androidx.cardview.widget.CardView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;

public class AddMoneyFragment extends BaseFragment {

    CardView fiftyTL, oneHundredTL, twoHundredFiftyTL, fiveHundredTL;
    EditText amountText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_money);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Para Yükle");
        pageInit();
    }

    private void pageInit() {
        amountText = findViewById(R.id.add_money_amount);
        fiftyTL = findViewById(R.id.add_money_50_TL);
        oneHundredTL = findViewById(R.id.add_money_100_TL);
        twoHundredFiftyTL = findViewById(R.id.add_money_250_TL);
        fiveHundredTL = findViewById(R.id.add_money_500_TL);

        fiftyTL.setOnClickListener(view -> {
            amountText.setText("50 TL");
        });

        oneHundredTL.setOnClickListener(view -> {
            amountText.setText("100 TL");
        });

        twoHundredFiftyTL.setOnClickListener(view -> {
            amountText.setText("250 TL");
        });

        fiveHundredTL.setOnClickListener(view -> {
            amountText.setText("500 TL");
        });
    }
}