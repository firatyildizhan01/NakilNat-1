package com.nakilnat.nakilnat.ui.addad;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;

import java.util.Calendar;


public class AddAdFragment extends AppCompatActivity {
    private LinearLayout showSecondAddress, showSecondDeliveryAddress;
    private LinearLayout secondAddress, secondDeliveryAddress;
    private ImageView secondAddressImg, secondDeliveryAddressImg;
    private BottomNavigationView bottomBar;
    private DatePickerDialog datePickerDialog;
    private CardView bottomFab;
    private TextView navigationBarTitle;
    private EditText step1FirstGetDate, step1SecondGetDate,step1FirstDeliveryDate, step1SecondDeliveryDate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_ad);

        navigationBarTitle = (TextView)findViewById(R.id.top_bar_title);
        navigationBarTitle.setText("Yük İlanı Oluştur");

        initScreen();
        loadMethods();
        bottomBarSetup();
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
    private void bottomBarSetup() {
        bottomBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomBar.setItemIconTintList(null);
        bottomBar.setSelectedItemId(R.id.bottomAddAds);
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
                Intent intent = new Intent(AddAdFragment.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(AddAdFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomMyShipping:
                intent = new Intent(AddAdFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(AddAdFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}
