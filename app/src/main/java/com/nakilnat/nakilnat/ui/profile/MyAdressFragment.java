package com.nakilnat.nakilnat.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
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
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.LoginRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdressFragment extends AppCompatActivity {

    BottomNavigationView bottomBar;
    CardView bottomFab;
    TextView topBarText;
    ImageView topBarBack;
    EditText nickName, address, tax, taxNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_adress);
        topBarInit();
        pageInit();
        bottomBarSetup();
    }

    private void pageInit() {
        nickName = findViewById(R.id.my_inv_nickname_edt);
        address = findViewById(R.id.my_inv_address_edt);
        tax = findViewById(R.id.my_inv_tax_edt);
        taxNo = findViewById(R.id.my_inv_tax_no_edt);

        adressListCallBack(createRequest());

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

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void adressListCallBack(DefaultRequest defaultRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().myAdressList(defaultRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {

                    Intent intent = new Intent(MyAdressFragment.this, ProfilePageFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MyAdressFragment.this, defaultResponse.getResult(), Toast.LENGTH_LONG).show();
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
        topBarText.setText("Adres Ekle");
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
                Intent intent = new Intent(MyAdressFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(MyAdressFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomMyShipping:
                intent = new Intent(MyAdressFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MyAdressFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                finish();
                break;
        }
    }
}