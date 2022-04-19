package com.nakilnat.nakilnat.ui.myships;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.TransportList;
import com.nakilnat.nakilnat.ui.BaseFragment;
import java.util.ArrayList;

public class CompletedShipsFragment extends BaseFragment {

    private ArrayList<TransportList> transportList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_completed_ships);
        bottomBarSetup(R.id.bottomMyShipping);
        initTopBarContents("Taşımalarım");
        pageInit();
    }

    private void pageInit() {
        transportList = new ArrayList<>();
        transportList.add(new TransportList("Kayseri", "Manisa", "500 ₺", "Demir", "Taşıma devam ediyor"));
        transportList.add(new TransportList("Adana", "Rize", "1500 ₺", "Pamuk", "Taşıma tamamlandı"));
        transportList.add(new TransportList("Giresun", "İzmir", "6500 ₺", "Marul", "Taşıma başlamadı"));

        //setting adapter and listview
        CompletedShipsAdapter adapter = new CompletedShipsAdapter(transportList, this);
        RecyclerView listview = findViewById(R.id.my_ships_completed_list);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(this));
    }
}