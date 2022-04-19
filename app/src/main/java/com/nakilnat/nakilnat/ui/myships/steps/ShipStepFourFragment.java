package com.nakilnat.nakilnat.ui.myships.steps;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.ShipImageList;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.myships.CompletedShipsFragment;
import java.util.List;

public class ShipStepFourFragment extends BaseFragment {

    EditText purchaseAdress, deliveryAdress, drive, contact;
    Button next;
    private List<ShipImageList> shipImageList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_ships_steps_four);
        bottomBarSetup(R.id.bottomMyShipping);
        initTopBarContents("Taşımalarım");
        purchaseAdress = (EditText) findViewById(R.id.my_ships_adress_purchase_edt);
        deliveryAdress = (EditText) findViewById(R.id.my_ships_adress_delivery_edt);
        drive = (EditText) findViewById(R.id.my_ships_drive_edt);
        contact = (EditText) findViewById(R.id.my_ships_contact_edt);

        purchaseAdress.setText("Antalya/Kızılay");
        deliveryAdress.setText("Antalya/Elmalı");
        drive.setText("Hasan Solmaz");
        contact.setText("05121233456");

        next = (Button) findViewById(R.id.ships_approve_button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Taşıma tamamlandı", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShipStepFourFragment.this, CompletedShipsFragment.class);
                startActivity(intent);
            }
        });
    }
}