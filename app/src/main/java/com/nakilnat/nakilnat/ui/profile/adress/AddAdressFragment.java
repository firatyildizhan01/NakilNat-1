package com.nakilnat.nakilnat.ui.profile.adress;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.AddAdressRequest;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.GetDistrictRequest;
import com.nakilnat.nakilnat.models.request.UpdateAdressRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.GetDistrictResponse;
import com.nakilnat.nakilnat.models.response.GetProvinceResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.profile.map.MapFragment;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAdressFragment extends BaseFragment {

    TextView addAdressText;
    ImageView addAdressIcon;
    Button addAddressButton;
    EditText name, surname, phoneNumber, addressHeader, neighborhood,
            street, buildingNumber, apartmentNumber, adressDescription;

    boolean isPermissionGranter;

    private String[] provincesStartFirst = {"İl"};
    private String[] districtsStartFirst = {"İlçe"};
    private String[] districtsEndFirst = {"İlçe"};

    private List<GetProvinceResponse> provincesRes;
    private List<GetDistrictResponse> firstGetDistrictsRes;

    private Spinner firstGetProvince, firstGetDistrict;

    private String id, adressCity, adressDistrict = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_adress);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Adres Ekle");
        initScreen();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                id = extras.getString("id");
                name.setText(extras.getString("adressOfficial"));
                surname.setText(extras.getString("adressOfficial"));
                addressHeader.setText(extras.getString("adressHeader"));
                adressCity = extras.getString("adressCity");
                adressDistrict = extras.getString("adressDistrict");
                street.setText(extras.getString("adressStreet"));
                neighborhood.setText(extras.getString("adressNeighborhood"));
                buildingNumber.setText(extras.getString("adressBuildingNo"));
                apartmentNumber.setText(extras.getString("adressApertment"));
                adressDescription.setText(extras.getString("adress"));
                phoneNumber.setText(extras.getString("adressPhoneNumber"));
            }
        }

        initProvince();
        initProvincesAndDistricts();
        initDistrictsComboboxFirst(true);
        initDistrictsComboboxEndFirst(true);

    }

    private void initScreen() {
        addAdressIcon = findViewById(R.id.add_adress_with_map);
        addAdressText = findViewById(R.id.add_adress_with_map_text);
        name = findViewById(R.id.add_address_name);
        surname = findViewById(R.id.add_address_surname);
        addressHeader = findViewById(R.id.add_adress_header);
        street = findViewById(R.id.add_adress_street);
        neighborhood = findViewById(R.id.add_adress_neighborhood);
        buildingNumber = findViewById(R.id.add_adress_building_number);
        apartmentNumber = findViewById(R.id.add_adress_apertment_number);
        adressDescription = findViewById(R.id.add_adress_description);
        phoneNumber = (EditText) findViewById(R.id.add_adress_phone_number);
        firstGetDistrict = findViewById(R.id.add_address_district);
        firstGetProvince = findViewById(R.id.add_address_city);


        addAdressIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        addAdressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        addAddressButton = (Button) findViewById(R.id.add_address_button);

        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adressCity != null && adressCity.equals("")) {
                    updateAdressCallBack(updateAdressRequest(id, addressHeader.getText().toString(), adressCity, adressDistrict, street.getText().toString(),
                            neighborhood.getText().toString(), buildingNumber.getText().toString(), "", apartmentNumber.getText().toString(), "", adressDescription.getText().toString()));
                }
                else {
                    addAdressCallBack(createRequest(addressHeader.getText().toString(), adressCity, adressDistrict, street.getText().toString(),
                            neighborhood.getText().toString(), buildingNumber.getText().toString(), "", apartmentNumber.getText().toString(), "", adressDescription.getText().toString()));
                }

            }
        });
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

    private void getLocation() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                isPermissionGranter = true;
                Toast.makeText(AddAdressFragment.this, "Lokasyon izni alındı", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddAdressFragment.this, MapFragment.class);
                startActivity(intent);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(AddAdressFragment.this, "Lokasyon izni reddedildi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();


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

    public void addAdressCallBack(AddAdressRequest addAdressRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().addAdress(addAdressRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {
                    Toast.makeText(AddAdressFragment.this, "Adres eklendi", Toast.LENGTH_LONG).show();
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
}