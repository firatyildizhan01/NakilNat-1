package com.nakilnat.nakilnat.ui.profile.offers;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.TransportList;
import com.nakilnat.nakilnat.ui.BaseFragment;
import java.util.ArrayList;


public class IncomingOffersFragment extends BaseFragment implements OffersAdapter.OnOffersListener {

    private ArrayList<TransportList> transportList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_offers_incoming);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Gelen teklifler");
        pageInit();
    }

    private void pageInit() {
        transportList = new ArrayList<>();
        transportList.add(new TransportList("Kayseri", "Manisa", "500 ₺", "Demir", "Taşıma devam ediyor"));
        transportList.add(new TransportList("Adana", "Rize", "1500 ₺", "Pamuk", "Taşıma tamamlandı"));
        transportList.add(new TransportList("Giresun", "İzmir", "6500 ₺", "Marul", "Taşıma başlamadı"));

        //setting adapter and listview
        OffersAdapter adapter = new OffersAdapter(transportList, this, this);
        RecyclerView listview = findViewById(R.id.my_offers_incoming_list);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onOfferClick(int position) {
        Intent intent = new Intent(IncomingOffersFragment.this, OffersLayoutFragment.class);
        startActivity(intent);
    }
}