package com.nakilnat.nakilnat.ui.profile.adress;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.RemoveAdressRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.MyAdressListResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.myships.ShipsAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdressListFragment extends BaseFragment implements AdressAdapter.OnAdressListener {

    private List<MyAdressListResponse> adressList;
    AdressAdapter adapter = null;
    private int deleteAdressId = 0;
    TextView topBarRightText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_adress);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Adreslerim");
        initRightTitle("Adres Ekle");
        topBarRightText = findViewById(R.id.top_bar_right_title);
        topBarRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAdressListFragment.this, AddAdressFragment.class);
                startActivity(intent);
            }
        });
        myAdressListCallBack(createRequest());
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myAdressListCallBack(DefaultRequest defaultRequest) {
        Call<List<MyAdressListResponse>> call = ApiClient.getApiClient().myAdressList(defaultRequest);

        call.enqueue(new Callback<List<MyAdressListResponse>>() {
            @Override
            public void onResponse(Call<List<MyAdressListResponse>> call, Response<List<MyAdressListResponse>> response) {
                adressList = response.body();
                if (response != null) {
                    Toast.makeText(MyAdressListFragment.this, "Adres bilgilerim sağlandı", Toast.LENGTH_LONG).show();
                    adapter = new AdressAdapter(adressList, MyAdressListFragment.this, MyAdressListFragment.this);
                    setAdapter();
                } else {
                    Toast.makeText(MyAdressListFragment.this, "Adres bilgilerim sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyAdressListResponse>> call, Throwable t) {
                Toast.makeText(MyAdressListFragment.this, "Servis hatası!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setAdapter() {
        RecyclerView listview = findViewById(R.id.adress_list);
        listview.setHasFixedSize(true);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(MyAdressListFragment.this));
    }


    public RemoveAdressRequest deleteRequest(String id) {
        RemoveAdressRequest removeAdressRequest = new RemoveAdressRequest();
        removeAdressRequest.setToken("korayaman");
        removeAdressRequest.setId(id);
        return removeAdressRequest;
    }

    public void removeAdressCallBack(RemoveAdressRequest removeAdressRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().removeAdress(removeAdressRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {
                    Toast.makeText(MyAdressListFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                    System.out.println(deleteAdressId);
                    adressList.remove(deleteAdressId);
                    adapter.notifyItemRemoved(deleteAdressId);
                } else {
                    Toast.makeText(MyAdressListFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    @Override
    public void onRemoveClick(int position) {
        deleteAdressId = position;
        removeAdressCallBack(deleteRequest(adressList.get(deleteAdressId).getId()));
    }

    @Override
    public void onEditClick(int position) {
        Intent intent = new Intent(MyAdressListFragment.this, AddAdressFragment.class);
        intent.putExtra("id", adressList.get(position).getId());
        intent.putExtra("adressHeader", adressList.get(position).getAdressHeader());
        intent.putExtra("adressCity", adressList.get(position).getAdressCity());
        intent.putExtra("adressDistrict", adressList.get(position).getAdressDistrict());
        intent.putExtra("adressStreet", adressList.get(position).getAdressStreet());
        intent.putExtra("adressNeighborhood", adressList.get(position).getAdressNeighborhood());
        intent.putExtra("adressBuildingNo", adressList.get(position).getAdressBuildingNo());
        intent.putExtra("adressFloor", adressList.get(position).getAdressFloor());
        intent.putExtra("adressApertment", adressList.get(position).getAdressApertment());
        intent.putExtra("adressOfficial", adressList.get(position).getAdressOfficial());
        intent.putExtra("adressPhoneNumber", adressList.get(position).getAdress());
        startActivity(intent);
    }
}