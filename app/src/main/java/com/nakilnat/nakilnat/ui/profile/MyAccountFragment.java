package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.MyAccountResponse;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAccountFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    TextView topBarText;
    ImageView topBarBack;
    TextView navigationBarTitle;
    EditText nameSurname, phoneNumber, email, adress, city, district, taxAdministration,
            taxNumber, aboutText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_account);

        bottomBarSetup(R.id.bottomAddAds);
        InitSubContents();
        observeAccount();
        myAccountCallBack(createRequest());
    }



    private void InitSubContents() {
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Hesabım");
        topBarBack = findViewById(R.id.top_bar_back);
        topBarBack.setVisibility(View.VISIBLE);
        topBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nameSurname = (EditText) findViewById(R.id.account_name_surname_edt);
        phoneNumber = (EditText) findViewById(R.id.account_phone_number_edt);
        email = (EditText) findViewById(R.id.account_email_edt);
        adress = (EditText) findViewById(R.id.account_adress_edt);
        city = (EditText) findViewById(R.id.account_city_edt);
        district = (EditText) findViewById(R.id.account_district_edt);
        taxAdministration = (EditText) findViewById(R.id.account_tax_administration_edt);
        taxNumber = (EditText) findViewById(R.id.account_tax_number_edt);
        aboutText = (EditText) findViewById(R.id.account_about_edt);
    }

    private void observeAccount() {
        nameSurname.setText("Abdullah Kırmızı");
        phoneNumber.setText("5322103385");
        email.setText("kirmiziabdullah193@gmail.com");
        adress.setText("Bağdat Mah. Bağdat Cad. 20/11");
        city.setText("Adana");
        district.setText("Afyon");
        taxAdministration.setText("4333333333");
        taxNumber.setText("123456789");
        aboutText.setText("Hakkımda");
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myAccountCallBack(DefaultRequest defaultRequest) {
        Call<MyAccountResponse> call = ApiClient.getApiClient().myAccount(defaultRequest);

        call.enqueue(new Callback<MyAccountResponse>() {
            @Override
            public void onResponse(Call<MyAccountResponse> call, Response<MyAccountResponse> response) {
                MyAccountResponse defaultResponse = response.body();
                if (defaultResponse != null) {
                    Toast.makeText(MyAccountFragment.this, "Success", Toast.LENGTH_LONG).show();

                } else {
                    //Toast.makeText(MyAccountFragment.this, defaultResponse.getResult(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MyAccountResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void bottomBarSetup(int bottomItemId) {
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomBar.setItemIconTintList(null);
        bottomBar.setSelectedItemId(bottomItemId);
        bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                GoBottomMenuIntent(item.getItemId());
                return true;

            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomMyShipping:
                intent = new Intent(MyAccountFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MyAccountFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(MyAccountFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomHome:
                intent = new Intent(MyAccountFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}
