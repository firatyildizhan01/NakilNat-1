package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DeleteAccountRequest;
import com.nakilnat.nakilnat.models.request.UpdatePermissionRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.onboarding.OnboardingFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MySettingsFragment extends AppCompatActivity {
    BottomNavigationView bottomBar;
    TextView topBarText;
    ImageView topBarBack;
    CardView permissionsCardView, permissionSubCardView, passwordTransactionsCardView, helpCardView, helpSubCardView, deleteAccountCardView, signOutCardView;
    ImageView permissionsArrow, helpArrow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_settings);
        bottomBarSetup();
        InitSubContents();
        navigationController();
    }

    private void InitSubContents() {
        topBarText = findViewById(R.id.top_bar_title);
        topBarText.setText("Ayarlar");
        topBarBack = findViewById(R.id.top_bar_back);
        topBarBack.setVisibility(View.VISIBLE);
        topBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        permissionsCardView = (CardView) findViewById(R.id.settings_permission);
        permissionSubCardView = (CardView) findViewById(R.id.settings_permission_subview);
        passwordTransactionsCardView = (CardView) findViewById(R.id.settings_password);
        helpCardView = (CardView) findViewById(R.id.settings_help);
        helpSubCardView = (CardView) findViewById(R.id.settings_help_subview);
        deleteAccountCardView = (CardView) findViewById(R.id.settings_delete_account);
        signOutCardView = (CardView) findViewById(R.id.settings_signout);
        permissionsArrow = (ImageView) findViewById(R.id.settings_permission_arrow);
        helpArrow = (ImageView) findViewById(R.id.settings_help_arrow);
    }

    private void navigationController() {
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setDuration(0);

        permissionsCardView.setOnClickListener(view -> {
            if (permissionSubCardView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(permissionsCardView, autoTransition);
                permissionSubCardView.setVisibility(View.GONE);
                permissionsArrow.setImageResource(R.drawable.ic_next);
            } else {
                TransitionManager.beginDelayedTransition(permissionsCardView, new AutoTransition());
                permissionSubCardView.setVisibility(View.VISIBLE);
                permissionsArrow.setImageResource(R.drawable.ic_profile_arrow_down);
            }
        });

        passwordTransactionsCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MySettingsFragment.this, UpdatePasswordFragment.class);
            startActivity(intent);
        });

        helpCardView.setOnClickListener(view -> {
            if (helpSubCardView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(helpCardView, autoTransition);
                helpSubCardView.setVisibility(View.GONE);
                helpArrow.setImageResource(R.drawable.ic_next);
            } else {
                TransitionManager.beginDelayedTransition(helpCardView, new AutoTransition());
                helpSubCardView.setVisibility(View.VISIBLE);
                helpArrow.setImageResource(R.drawable.ic_profile_arrow_down);
            }
        });

        deleteAccountCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MySettingsFragment.this, OnboardingFragment.class);
            startActivity(intent);
        });

        signOutCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MySettingsFragment.this, OnboardingFragment.class);
            startActivity(intent);
        });

    }

    public DeleteAccountRequest deleteAccountRequest(String id) {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest();
        deleteAccountRequest.setToken("korayamana");
        deleteAccountRequest.setSil(id);
        return deleteAccountRequest;
    }

    public void deleteAccountCallBack(DeleteAccountRequest deleteAccountRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().deleteAccount(deleteAccountRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("istekok")) {
                    Toast.makeText(MySettingsFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MySettingsFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public UpdatePermissionRequest updatePermissionRequest(String mailOffer, String mailNotification, String mailBulletin, String smsOffer, String smsNotification) {
        UpdatePermissionRequest updatePermissionRequest = new UpdatePermissionRequest();
        updatePermissionRequest.setToken("korayamana");
        updatePermissionRequest.setIzin_mail_kampanya(mailOffer);
        updatePermissionRequest.setIzin_mail_bildirim(mailNotification);
        updatePermissionRequest.setIzin_mail_bulten(mailBulletin);
        updatePermissionRequest.setIzin_sms_kampanya(smsOffer);
        updatePermissionRequest.setIzin_sms_kampanya(smsOffer);
        return updatePermissionRequest;
    }

    public void updatePermissionCallBack(UpdatePermissionRequest updatePermissionRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().updatePermission(updatePermissionRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("ok")) {
                    Toast.makeText(MySettingsFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MySettingsFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
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
    }

    private void GoBottomMenuIntent(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.bottomMyShipping:
                intent = new Intent(MySettingsFragment.this, MyShipsFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomAddAds:
                intent = new Intent(MySettingsFragment.this, AddAdFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomProfile:
                intent = new Intent(MySettingsFragment.this, ProfilePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.bottomHome:
                intent = new Intent(MySettingsFragment.this, HomePageFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
        }
    }

}