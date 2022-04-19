package com.nakilnat.nakilnat.ui.profile.myads;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.TransportList;
import com.nakilnat.nakilnat.ui.BaseFragment;
import java.util.ArrayList;

public class ContinueAdsFragment extends BaseFragment implements AdsAdapter.OnAdsListener {

    private ArrayList<TransportList> transportList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_continue_ads);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Onay Bekleyen İlanlarım");
        pageInit();
    }

    private void pageInit() {
        transportList = new ArrayList<>();
        transportList.add(new TransportList("Kayseri", "Manisa", "500 ₺", "Demir", "Taşıma devam ediyor"));
        transportList.add(new TransportList("Adana", "Rize", "1500 ₺", "Pamuk", "Taşıma tamamlandı"));
        transportList.add(new TransportList("Giresun", "İzmir", "6500 ₺", "Ceviz", "Taşıma başlamadı"));

        //setting adapter and listview
        AdsAdapter adapter = new AdsAdapter(transportList, this, this);
        RecyclerView listview = findViewById(R.id.my_continue_ads_list);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onAdClick(int position) {
        /*
        transportList.get(position);
        Intent intent = new Intent(ContinueAdsFragment.this, MyAdsFragment.class);
        startActivity(intent);

         */
        System.out.println(transportList.get(position));
    }
}