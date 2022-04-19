package com.nakilnat.nakilnat.ui.myships;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.TransportList;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.myships.steps.ShipStepOneFragment;
import java.util.ArrayList;

public class ContinueShipsFragment extends BaseFragment {

    TextView continueShips;
    private ArrayList<TransportList> transportList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_continue_ships);
        bottomBarSetup(R.id.bottomMyShipping);
        initTopBarContents("Taşımalarım");
        pageInit();

        continueShips = (TextView) findViewById(R.id.my_continue_ships_text);
        continueShips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinueShipsFragment.this, ShipStepOneFragment.class);
                startActivity(intent);
            }
        });
    }

    private void pageInit() {
        transportList = new ArrayList<>();
        transportList.add(new TransportList("Kayseri", "Manisa", "500 ₺", "Demir", "Taşıma devam ediyor"));
        transportList.add(new TransportList("Adana", "Rize", "1500 ₺", "Pamuk", "Taşıma tamamlandı"));
        transportList.add(new TransportList("Giresun", "İzmir", "6500 ₺", "Marul", "Taşıma başlamadı"));

        //setting adapter and listview
        ShipsAdapter adapter = new ShipsAdapter(transportList, this);
        RecyclerView listview = findViewById(R.id.my_ships_continue_list);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(this));
    }
}