package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;


public class MyAccountFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    TextView navigationBarTitle;
    EditText nameSurname, phoneNumber, email, adress, city, district, taxAdministration,
            taxNumber, aboutText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_account);

        navigationBarTitle = (TextView)findViewById(R.id.top_bar_title);
        navigationBarTitle.setText("Hesabım");

        bottomBarSetup(R.id.bottomAddAds);
        InitSubContents();
        observeAccount();
    }



    private void InitSubContents() {
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
        aboutText.setText("Burcu Kaya Mehmet Aydın Nakilnat");
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
