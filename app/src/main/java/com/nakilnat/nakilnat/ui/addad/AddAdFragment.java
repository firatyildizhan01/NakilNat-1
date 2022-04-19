package com.nakilnat.nakilnat.ui.addad;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.GetDistrictRequest;
import com.nakilnat.nakilnat.models.response.GetDistrictResponse;
import com.nakilnat.nakilnat.models.response.GetProvinceResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddAdFragment extends BaseFragment {
    private String[] provincesStartFirst = {"Teslim Yeri"};
    private String[] provincesStartSecond = {"Teslim Yeri"};
    private String[] provincesEndFirst = {"Teslim Yeri"};
    private String[] provincesEndSecond = {"Teslim Yeri"};

    private String[] districtsStartFirst = {"Teslim İlçesi"};
    private String[] districtsStartSecond = {"Teslim İlçesi"};
    private String[] districtsEndFirst = {"Teslim İlçesi"};
    private String[] districtsEndSecond = {"Teslim İlçesi"};

    private List<GetProvinceResponse> provincesRes;
    private List<GetDistrictResponse> firstGetDistrictsRes, secondGetDistrictsRes, firstEndDistrictsRes, secondEndDistrictsRes;

    private Spinner firstGetProvince, firstGetDistrict;
    private Spinner secondGetProvince, secondGetDistrict;
    private Spinner firstEndProvince, firstEndDistrict;
    private Spinner secondEndProvince, secondEndDistrict;


    private LinearLayout showSecondAddress, showSecondDeliveryAddress;
    private LinearLayout secondAddress, secondDeliveryAddress;
    private ImageView secondAddressImg, secondDeliveryAddressImg;
    private DatePickerDialog datePickerDialog;
    private EditText step1FirstGetDate, step1FirstDeliveryDate;

    private Button next;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_ad);
        bottomBarSetup(R.id.bottomAddAds);
        initTopBarContents("Yük İlanı Oluştur");
        initScreen();
        initProvincesAndDistricts();
        loadMethods();
    }

    private void initProvincesAndDistricts() {
        firstGetDistrict = findViewById(R.id.add_ad_start_district_spinner_step1);
        secondGetDistrict = findViewById(R.id.add_ad_second_start_district_spinner_step1);
        firstEndDistrict = findViewById(R.id.add_ad_start_district_spinner_delivery_step1);
        secondEndDistrict = findViewById(R.id.add_ad_second_delivery_district_spinner_step1);

        firstGetProvince = findViewById(R.id.add_ad_start_province_spinner_step1);
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
                            districtsStartFirst[0] = "Teslim İlçesi";
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

        secondGetProvince = findViewById(R.id.add_ad_second_start_province_spinner_step1);
        secondGetProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //0 seçiniz
                if(i != 0 ){
                    Call<List<GetDistrictResponse>> call = ApiClient.getApiClient().getDistrict(new GetDistrictRequest(provincesRes.get(i-1).getId()));

                    call.enqueue(new Callback<List<GetDistrictResponse>>() {
                        @Override
                        public void onResponse(Call<List<GetDistrictResponse>> call, Response<List<GetDistrictResponse>> response) {
                            secondGetDistrictsRes = response.body();
                            districtsStartSecond = new String[secondGetDistrictsRes.size()+1];
                            districtsStartSecond[0] = "Teslim İlçesi";
                            if (secondGetDistrictsRes != null && secondGetDistrictsRes.size() > 0) {
                                for (int i = 0; i< secondGetDistrictsRes.size(); i++){
                                    districtsStartSecond[i+1] = secondGetDistrictsRes.get(i).getAd();
                                }
                            }
                            initDistrictsComboboxSecond(false);
                        }

                        @Override
                        public void onFailure(Call<List<GetDistrictResponse>> call, Throwable t) {
                            initDistrictsComboboxSecond(true);
                        }
                    });
                }
                else{
                    initDistrictsComboboxSecond(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        firstEndProvince = findViewById(R.id.add_ad_start_province_spinner_delivery_step1);
        firstEndProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //0 seçiniz
                if(i != 0 ){
                    Call<List<GetDistrictResponse>> call = ApiClient.getApiClient().getDistrict(new GetDistrictRequest(provincesRes.get(i-1).getId()));

                    call.enqueue(new Callback<List<GetDistrictResponse>>() {
                        @Override
                        public void onResponse(Call<List<GetDistrictResponse>> call, Response<List<GetDistrictResponse>> response) {
                            firstEndDistrictsRes = response.body();
                            districtsEndFirst = new String[firstEndDistrictsRes.size()+1];
                            districtsEndFirst[0] = "Teslim İlçesi";
                            if (firstEndDistrictsRes != null && firstEndDistrictsRes.size() > 0) {
                                for (int i = 0; i< firstEndDistrictsRes.size(); i++){
                                    districtsEndFirst[i+1] = firstEndDistrictsRes.get(i).getAd();
                                }
                            }
                            initDistrictsComboboxEndFirst(false);
                        }

                        @Override
                        public void onFailure(Call<List<GetDistrictResponse>> call, Throwable t) {
                            initDistrictsComboboxEndFirst(true);
                        }
                    });
                }
                else{
                    initDistrictsComboboxEndFirst(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        secondEndProvince = findViewById(R.id.add_ad_second_delivery_province_spinner_step1);
        secondEndProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //0 seçiniz
                if(i != 0 ){
                    Call<List<GetDistrictResponse>> call = ApiClient.getApiClient().getDistrict(new GetDistrictRequest(provincesRes.get(i-1).getId()));

                    call.enqueue(new Callback<List<GetDistrictResponse>>() {
                        @Override
                        public void onResponse(Call<List<GetDistrictResponse>> call, Response<List<GetDistrictResponse>> response) {
                            secondEndDistrictsRes = response.body();
                            districtsEndSecond = new String[secondEndDistrictsRes.size()+1];
                            districtsEndSecond[0] = "Teslim İlçesi";
                            if (secondEndDistrictsRes != null && secondEndDistrictsRes.size() > 0) {
                                for (int i = 0; i< secondEndDistrictsRes.size(); i++){
                                    districtsEndSecond[i+1] = secondEndDistrictsRes.get(i).getAd();
                                }
                            }
                            initDistrictsComboboxEndSecond(false);
                        }

                        @Override
                        public void onFailure(Call<List<GetDistrictResponse>> call, Throwable t) {
                            initDistrictsComboboxEndSecond(true);
                        }
                    });
                }
                else{
                    initDistrictsComboboxEndSecond(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void initScreen() {
        step1FirstGetDate = findViewById(R.id.add_ad_date_edt_step1);
        step1FirstDeliveryDate = findViewById(R.id.add_ad_date_edt_delivery_step1);

        showSecondAddress = findViewById(R.id.add_ad_show_second_address_layout);
        secondAddress = findViewById(R.id.add_ad_second_address_layout);
        secondAddressImg = findViewById(R.id.add_ad_show_second_address_img);

        showSecondDeliveryAddress = findViewById(R.id.add_ad_show_second_delivery_address_layout);
        secondDeliveryAddress = findViewById(R.id.add_ad_second_delivery_address_layout);
        secondDeliveryAddressImg = findViewById(R.id.add_ad_show_second_delivery_address_layout_img);

        next = findViewById(R.id.next_step1);
    }

    private void loadMethods() {
        step1FirstGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDatePickerFirst();
            }
        });

        step1FirstDeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(step1FirstGetDate.getText().toString().isEmpty() || step1FirstGetDate.getText().toString().length() < 1){
                    Toast.makeText(getApplicationContext(), "Lütfen ilk olarak alım tarihini seçiniz.", Toast.LENGTH_LONG).show();
                }
                else{
                    initDatePickerSecond();
                }

            }
        });

        showSecondAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(secondAddress.getVisibility() == View.GONE){
                    secondAddress.setVisibility(View.VISIBLE);
                    secondAddressImg.setImageResource(R.drawable.ic_rectangle_fill);
                }
                else{
                    secondAddress.setVisibility(View.GONE);
                    secondAddressImg.setImageResource(R.drawable.ic_rectangle_outline);
                }

            }
        });

        showSecondDeliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(secondDeliveryAddress.getVisibility() == View.GONE){
                    secondDeliveryAddress.setVisibility(View.VISIBLE);
                    secondDeliveryAddressImg.setImageResource(R.drawable.ic_rectangle_fill);
                }
                else{
                    secondDeliveryAddress.setVisibility(View.GONE);
                    secondDeliveryAddressImg.setImageResource(R.drawable.ic_rectangle_outline);
                }

            }
        });

        initProvince();
        initDistrictsComboboxFirst(true);
        initDistrictsComboboxSecond(true);
        initDistrictsComboboxEndFirst(true);
        initDistrictsComboboxEndSecond(true);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAdFragment.this, AddAdStepCargoDetail.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void initDistrictsComboboxFirst(Boolean setDefault) {
        if(setDefault){
            districtsStartFirst = new String[1];
            districtsStartFirst[0] = "Teslim İlçesi";
        }
        ArrayAdapter districtAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, districtsStartFirst);
        districtAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        firstGetDistrict.setAdapter(districtAdpt);
    }
    private void initDistrictsComboboxSecond(Boolean setDefault) {
        if(setDefault){
            districtsStartSecond = new String[1];
            districtsStartSecond[0] = "Teslim İlçesi";
        }
        ArrayAdapter districtAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, districtsStartSecond);
        districtAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        secondGetDistrict.setAdapter(districtAdpt);
    }
    private void initDistrictsComboboxEndFirst(Boolean setDefault) {
        if(setDefault){
            districtsEndFirst = new String[1];
            districtsEndFirst[0] = "Teslim İlçesi";
        }
        ArrayAdapter districtAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, districtsEndFirst);
        districtAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        firstEndDistrict.setAdapter(districtAdpt);
    }
    private void initDistrictsComboboxEndSecond(Boolean setDefault) {
        if(setDefault){
            districtsEndSecond = new String[1];
            districtsEndSecond[0] = "Teslim İlçesi";
        }
        ArrayAdapter districtAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, districtsEndSecond);
        districtAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        secondEndDistrict.setAdapter(districtAdpt);
    }

    private void initProvinceCombobox(){
        ArrayAdapter firstGetProvinceAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, provincesStartFirst);
        firstGetProvinceAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        ArrayAdapter secondGetProvinceAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, provincesStartSecond);
        secondGetProvinceAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        ArrayAdapter firstEndProvinceAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, provincesEndFirst);
        firstEndProvinceAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        ArrayAdapter secondEndProvinceAdpt = new ArrayAdapter(this, R.layout.custom_spinner_item, provincesEndSecond);
        secondEndProvinceAdpt.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        firstGetProvince.setAdapter(firstGetProvinceAdpt);
        secondGetProvince.setAdapter(secondGetProvinceAdpt);
        firstEndProvince.setAdapter(firstEndProvinceAdpt);
        secondEndProvince.setAdapter(secondEndProvinceAdpt);
    }

    private void initDatePickerFirst(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = day + "." + month + "." + year;
                step1FirstGetDate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, R.style.date_time_dialog_theme, dateSetListener, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void initDatePickerSecond(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = day + "." + month + "." + year;
                step1FirstDeliveryDate.setText(date);
            }
        };

        String[] recDatecal = step1FirstGetDate.getText().toString().split("\\.");

        int year = Integer.parseInt(recDatecal[2]);
        int month = Integer.parseInt(recDatecal[1]);
        int day = Integer.parseInt(recDatecal[0]);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);//Year,Mounth -1,Day


        datePickerDialog = new DatePickerDialog(this, R.style.date_time_dialog_theme, dateSetListener, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        datePickerDialog.show();

    }

    public void initProvince() {
        provincesStartFirst = new String[1];
        provincesStartFirst[0] = "Teslim Yeri";

        provincesStartSecond = new String[1];
        provincesStartSecond[0] = "Teslim Yeri";

        provincesEndFirst = new String[1];
        provincesEndFirst[0] = "Teslim Yeri";

        provincesEndSecond = new String[1];
        provincesEndSecond[0] = "Teslim Yeri";

        Call<List<GetProvinceResponse>> call = ApiClient.getApiClient().getProvince();

        call.enqueue(new Callback<List<GetProvinceResponse>>() {
            @Override
            public void onResponse(Call<List<GetProvinceResponse>> call, Response<List<GetProvinceResponse>> response) {
                provincesRes = response.body();
                provincesStartFirst = new String[provincesRes.size()+1];
                provincesStartFirst[0] = "Teslim Yeri";
                provincesStartSecond = new String[provincesRes.size()+1];
                provincesStartSecond[0] = "Teslim Yeri";
                provincesEndFirst = new String[provincesRes.size()+1];
                provincesEndFirst[0] = "Teslim Yeri";
                provincesEndSecond = new String[provincesRes.size()+1];
                provincesEndSecond[0] = "Teslim Yeri";
                if (provincesRes != null && provincesRes.size() > 0) {
                    for (int i = 0; i< provincesRes.size(); i++){
                        provincesStartFirst[i+1] = provincesRes.get(i).getAd();
                        provincesStartSecond[i+1] = provincesRes.get(i).getAd();
                        provincesEndFirst[i+1] = provincesRes.get(i).getAd();
                        provincesEndSecond[i+1] = provincesRes.get(i).getAd();
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
}
