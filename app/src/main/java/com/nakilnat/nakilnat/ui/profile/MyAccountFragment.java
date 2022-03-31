package com.nakilnat.nakilnat.ui.profile;

import android.content.Context;
import android.os.Bundle;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.GetDistrictRequest;
import com.nakilnat.nakilnat.models.request.UpdateAccountRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.GetDistrictResponse;
import com.nakilnat.nakilnat.models.response.GetProvinceResponse;
import com.nakilnat.nakilnat.models.response.MyAccountResponse;
import com.nakilnat.nakilnat.models.response.TransportList;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAccountFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    TextView topBarText;
    ImageView topBarBack;
    EditText nameSurname, phoneNumber, email, adress, city, district, tckn;

    Button updateAccountButton;
    private String[] provincesStartFirst = {"İl"};
    private String[] districtsStartFirst = {"İlçe"};
    private String[] districtsEndFirst = {"İlçe"};

    private List<GetProvinceResponse> provincesRes;
    private List<GetDistrictResponse> firstGetDistrictsRes;

    private Spinner firstGetProvince, firstGetDistrict;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_account);

        bottomBarSetup(R.id.bottomAddAds);
        InitSubContents();
        myAccountCallBack(createRequest());

        updateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameSurname.length() != 0 && phoneNumber.length() != 0 && email.length() != 0 &&
                        adress.length() != 0 && city.length() != 0 && district.length() != 0 && tckn.length() != 0) {
                    updateAccountCallBack(updateAccountRequest(email.getText().toString(), "parola", nameSurname.getText().toString(), phoneNumber.getText().toString(),
                            adress.getText().toString(), city.getText().toString(), district.getText().toString(), "companyVn",
                            "companyVd", "web", "about", "1"));
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen alanları doldurunuz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        initProvince();
        initProvincesAndDistricts();
        initDistrictsComboboxFirst(true);
        initDistrictsComboboxEndFirst(true);
    }

    public void initProvince() {
        provincesStartFirst = new String[1];
        provincesStartFirst[0] = "İl";

        Call<List<GetProvinceResponse>> call = ApiClient.getApiClient().getProvince();

        call.enqueue(new Callback<List<GetProvinceResponse>>() {
            @Override
            public void onResponse(Call<List<GetProvinceResponse>> call, Response<List<GetProvinceResponse>> response) {
                provincesRes = response.body();
                provincesStartFirst = new String[provincesRes.size()+1];
                provincesStartFirst[0] = "İl";
                if (provincesRes != null && provincesRes.size() > 0) {
                    for (int i = 0; i< provincesRes.size(); i++){
                        provincesStartFirst[i+1] = provincesRes.get(i).getAd();
                    }
                }
                initProvinceCombobox();
            }

            @Override
            public void onFailure(Call<List<GetProvinceResponse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void initProvinceCombobox(){
        ArrayAdapter firstGetProvinceAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, provincesStartFirst);
        firstGetProvinceAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        firstGetProvince.setAdapter(firstGetProvinceAdpt);
    }

    private void initProvincesAndDistricts() {
        firstGetDistrict = findViewById(R.id.account_district);
        firstGetProvince = findViewById(R.id.account_city);
        firstGetProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //0 seçiniz
                if(i != 0 ){
                    Call<List<GetDistrictResponse>> call = ApiClient.getApiClient().getDistrict(new GetDistrictRequest(provincesRes.get(i-1).getId()));

                    call.enqueue(new Callback<List<GetDistrictResponse>>() {
                        @Override
                        public void onResponse(Call<List<GetDistrictResponse>> call, Response<List<GetDistrictResponse>> response) {
                            firstGetDistrictsRes = response.body();
                            districtsStartFirst = new String[firstGetDistrictsRes.size()+1];
                            districtsStartFirst[0] = "İlçe";
                            if (firstGetDistrictsRes != null && firstGetDistrictsRes.size() > 0) {
                                for (int i = 0; i< firstGetDistrictsRes.size(); i++){
                                    districtsStartFirst[i+1] = firstGetDistrictsRes.get(i).getAd();
                                }
                            }
                            initDistrictsComboboxFirst(false);
                        }

                        @Override
                        public void onFailure(Call<List<GetDistrictResponse>> call, Throwable t) {
                            initDistrictsComboboxFirst(true);
                        }
                    });
                }
                else{
                    initDistrictsComboboxFirst(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void initDistrictsComboboxFirst(Boolean setDefault) {
        if(setDefault){
            districtsStartFirst = new String[1];
            districtsStartFirst[0] = "İlçe";
        }
        ArrayAdapter districtAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, districtsStartFirst);
        districtAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        firstGetDistrict.setAdapter(districtAdpt);
    }

    private void initDistrictsComboboxEndFirst(Boolean setDefault) {
        if(setDefault){
            districtsEndFirst = new String[1];
            districtsEndFirst[0] = "İlçe";
        }
        ArrayAdapter districtAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, districtsEndFirst);
        districtAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

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

        tckn = (EditText) findViewById(R.id.account_tckn_edt);
        updateAccountButton = (Button) findViewById(R.id.update_account_button);
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
                MyAccountResponse myAccountResponse = response.body();
                if (myAccountResponse != null && !myAccountResponse.getCepTel().isEmpty()) {
                    Toast.makeText(MyAccountFragment.this, "Hesap bilgileri sağlandı", Toast.LENGTH_LONG).show();
                    nameSurname.setText(myAccountResponse.getFirmaAdi());
                    phoneNumber.setText(myAccountResponse.getCepTel());
                    email.setText(myAccountResponse.getUn());
                    adress.setText(myAccountResponse.getAcikAdres());
                    //city.setText(myAccountResponse.getIl());
                    //district.setText(myAccountResponse.getIlce());
                    tckn.setText(myAccountResponse.getTc());
                } else {
                    Toast.makeText(MyAccountFragment.this, "Hesap bilgileri sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MyAccountResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public UpdateAccountRequest updateAccountRequest(String email, String password, String companyName, String phoneNumber, String adress, String city,
                                                     String district, String companyVn, String company_vd, String web, String about, String accountType) {
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
        updateAccountRequest.setEmail(email);
        updateAccountRequest.setSifre(password);
        updateAccountRequest.setFirma_adi(companyName);
        updateAccountRequest.setSabit_tel(phoneNumber);
        updateAccountRequest.setAcik_adres(adress);
        updateAccountRequest.setIl(city);
        updateAccountRequest.setIlce(district);
        updateAccountRequest.setFirma_vn(companyVn);
        updateAccountRequest.setFirma_vd(company_vd);
        updateAccountRequest.setWeb(web);
        updateAccountRequest.setHakkimda(about);
        updateAccountRequest.setHesap_turu(about);
        return updateAccountRequest;
    }

    public void updateAccountCallBack(UpdateAccountRequest updateAccountRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().updateAccount(updateAccountRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {
                    Toast.makeText(MyAccountFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MyAccountFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
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
