package com.nakilnat.nakilnat.ui.addad;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.InsurancesResponse;
import com.nakilnat.nakilnat.models.response.VehiclesResponse;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;

import java.util.ArrayList;


public class AddAdStepCargoDetail extends AppCompatActivity {
    private BottomNavigationView bottomBar;
    private CardView bottomFab;
    private TextView navigationBarTitle;
    private AppCompatButton btnParsiyel, btnAll;
    private AppCompatButton btninsuranceNo, btninsuranceYes;
    private EditText selectVehicleType;
    private LinearLayout insuranceYesDetail;
    private ArrayList<VehiclesResponse> vehicleList;
    private ArrayList<InsurancesResponse> insuranceList;
    private RecyclerView vehicleRecycle;
    private RecyclerView insuranceRecycle;
    private Button next;

    private boolean nextable = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_ad_step_cargo_detail);

        navigationBarTitle = (TextView)findViewById(R.id.top_bar_title);
        navigationBarTitle.setText("Yük İlanı Oluştur");

        bottomBarSetup();
        initScreen();
        loadMethods();
    }

    private void initScreen() {
        btnParsiyel = findViewById(R.id.burdenChoiseParsiyel);
        btnAll = findViewById(R.id.burdenChoiseAll);
        btninsuranceNo = findViewById(R.id.insuranceNo);
        btninsuranceYes = findViewById(R.id.insuranceYes);

        insuranceYesDetail = findViewById(R.id.insuranceYesDetail);

        selectVehicleType = findViewById(R.id.vehicleTypeEdt);

        next = findViewById(R.id.next_step2);


    }

    private void loadMethods() {
        btnParsiyel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAll.setBackgroundColor(getResources().getColor(R.color.white));
                btnAll.setTextColor(getResources().getColor(R.color.purple_500));

                btnParsiyel.setBackgroundColor(getResources().getColor(R.color.purple_500));
                btnParsiyel.setTextColor(getResources().getColor(R.color.white));
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAll.setBackgroundColor(getResources().getColor(R.color.purple_500));
                btnAll.setTextColor(getResources().getColor(R.color.white));

                btnParsiyel.setBackgroundColor(getResources().getColor(R.color.white));
                btnParsiyel.setTextColor(getResources().getColor(R.color.purple_500));
            }
        });

        selectVehicleType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showVehicleDialog();

            }
        });

        btninsuranceNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insuranceYesDetail.setVisibility(View.GONE);
                btninsuranceNo.setBackgroundColor(getResources().getColor(R.color.purple_500));
                btninsuranceNo.setTextColor(getResources().getColor(R.color.white));
                btninsuranceYes.setBackgroundColor(getResources().getColor(R.color.white));
                btninsuranceYes.setTextColor(getResources().getColor(R.color.purple_500));
                nextable = true;
            }
        });

        btninsuranceYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insuranceYesDetail.setVisibility(View.VISIBLE);
                btninsuranceYes.setBackgroundColor(getResources().getColor(R.color.purple_500));
                btninsuranceYes.setTextColor(getResources().getColor(R.color.white));
                btninsuranceNo.setBackgroundColor(getResources().getColor(R.color.white));
                btninsuranceNo.setTextColor(getResources().getColor(R.color.purple_500));
                nextable = false;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nextable){
                    showInsuranceDialog();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sonuç sayfasına geçiliyor.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddAdStepCargoDetail.this, AddAdLastStepFragment.class);
                    startActivity(intent);
                }
            }
        });


    }

    private void showInsuranceDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.insurance_bottom_sheet);

        insuranceRecycle = dialog.findViewById(R.id.insuranceRecycle);

        insuranceList = new ArrayList<>();
        insuranceList.add(new InsurancesResponse("ray","125₺"));
        insuranceList.add(new InsurancesResponse("allianze","200₺"));
        insuranceList.add(new InsurancesResponse("axa","300₺"));
        insuranceList.add(new InsurancesResponse("neova","189₺"));



        //setting adapter and listview
        InsuranceAdapter adapter = new InsuranceAdapter(insuranceList, AddAdStepCargoDetail.this);
        adapter.getItemCount();
        insuranceRecycle.setAdapter(adapter);
        insuranceRecycle.setLayoutManager(new LinearLayoutManager(this));


        Button apply = dialog.findViewById(R.id.applyInsurance);
        ImageView dismissIcon = dialog.findViewById(R.id.ic_cancel);


        dismissIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();


            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //selectVehicleType.setText(adapter.getSelectedItem());
                dialog.dismiss();
                nextable = true;
            }
        });



        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showVehicleDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.vehicle_bottom_sheet);

        vehicleRecycle = dialog.findViewById(R.id.vehiclesRecycle);

        vehicleList = new ArrayList<>();
        vehicleList.add(new VehiclesResponse("Sal Kasa"));
        vehicleList.add(new VehiclesResponse("Tenteli Dorse"));
        vehicleList.add(new VehiclesResponse("Lowbed"));
        vehicleList.add(new VehiclesResponse("Kamyon Römork"));
        vehicleList.add(new VehiclesResponse("Çekici"));
        vehicleList.add(new VehiclesResponse("Silobas"));

        //setting adapter and listview
        VehicleAdapter adapter = new VehicleAdapter(vehicleList, AddAdStepCargoDetail.this);
        adapter.getItemCount();
        vehicleRecycle.setAdapter(adapter);
        vehicleRecycle.setLayoutManager(new LinearLayoutManager(this));


        Button apply = dialog.findViewById(R.id.applyVehicle);
        ImageView dismissIcon = dialog.findViewById(R.id.ic_cancel);


        dismissIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();


            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectVehicleType.setText(adapter.getSelectedItem());
                dialog.dismiss();
            }
        });



        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
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
                Intent intent = new Intent(AddAdStepCargoDetail.this, ApplicationPageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomHome:
                intent = new Intent(AddAdStepCargoDetail.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomMyShipping:
                intent = new Intent(AddAdStepCargoDetail.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(AddAdStepCargoDetail.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }
}