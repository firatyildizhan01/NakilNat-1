package com.nakilnat.nakilnat.ui.profile.adress;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.AddAdressRequest;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.UpdateAdressRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAdressFragment extends AppCompatActivity {

    BottomNavigationView bottomBar;
    CardView bottomFab;
    TextView topBarText, topBarRightText;
    ImageView topBarBack, topBarNotification;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_adress);
        topBarInit();
        pageInit();
        bottomBarSetup();
    }

    private void pageInit() {

        //addAdressCallBack(createRequest());

        String[] COUNTRIES = new String[]{
                "Afghanistan", "Albania", "Algeria", "Andorra", "Angola"
        };

        /*AutoCompleteTextView editText = findViewById(R.id.my_inv_nickname_edt);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_list_item, R.id.text_view_list_item, COUNTRIES);
        editText.setAdapter(adapter);
        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                editText.showDropDown();
            }
        });
         */
    }

    public AddAdressRequest createRequest(String baslik, String il, String ilce, String sokak, String mahalle, String bina, String kat, String daire, String adresTarif, String adres) {
        AddAdressRequest addAdressRequest = new AddAdressRequest();
        addAdressRequest.setToken("korayaman");
        addAdressRequest.setBaslik(baslik);
        addAdressRequest.setIl(il);
        addAdressRequest.setIlce(ilce);
        addAdressRequest.setSokak(sokak);
        addAdressRequest.setMahalle(mahalle);
        addAdressRequest.setBina(bina);
        addAdressRequest.setKat(kat);
        addAdressRequest.setDaire(daire);
        addAdressRequest.setAdresTarif(adresTarif);
        addAdressRequest.setAdres(adres);
        return addAdressRequest;
    }

    public void addAdressCallBack(DefaultRequest defaultRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().myAdressList(defaultRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {

                    Intent intent = new Intent(AddAdressFragment.this, ProfilePageFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(AddAdressFragment.this, defaultResponse.getResult(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public UpdateAdressRequest updateAdressRequest(String id, String baslik, String il, String ilce, String sokak, String mahalle, String bina, String kat, String daire, String adresTarif, String adres) {
        UpdateAdressRequest updateAdressRequest = new UpdateAdressRequest();
        updateAdressRequest.setToken("korayaman");
        updateAdressRequest.setId(id);
        updateAdressRequest.setBaslik(baslik);
        updateAdressRequest.setIl(il);
        updateAdressRequest.setIlce(ilce);
        updateAdressRequest.setSokak(sokak);
        updateAdressRequest.setMahalle(mahalle);
        updateAdressRequest.setBina(bina);
        updateAdressRequest.setKat(kat);
        updateAdressRequest.setDaire(daire);
        updateAdressRequest.setAdresTarif(adresTarif);
        updateAdressRequest.setAdres(adres);
        return updateAdressRequest;
    }

    public void updateAdressCallBack(UpdateAdressRequest updateAdressRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().updateAdress(updateAdressRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {

                    Toast.makeText(AddAdressFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(AddAdressFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void topBarInit() {
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Adres ekle");
        topBarBack = findViewById(R.id.top_bar_back);
        topBarBack.setVisibility(View.VISIBLE);
        topBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void bottomBarSetup() {
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomBar.setItemIconTintList(null);
        bottomBar.setSelectedItemId(R.id.bottomProfile);
        bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                GoBottomMenuIntent(item.getItemId());
                return true;
            }
        });

        bottomFab = findViewById(R.id.fab);
        bottomFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomBar.getMenu().setGroupCheckable(0, false, true);
                bottomBar.getMenu().setGroupCheckable(1, false, true);
                bottomBar.getMenu().setGroupCheckable(2, false, true);
                bottomBar.getMenu().setGroupCheckable(3, false, true);
                Intent intent = new Intent(AddAdressFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(AddAdressFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomMyShipping:
                intent = new Intent(AddAdressFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(AddAdressFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                finish();
                break;
        }
    }
}