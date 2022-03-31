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
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.DeleteAccountRequest;
import com.nakilnat.nakilnat.models.request.UpdatePermissionRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.MyAccountResponse;
import com.nakilnat.nakilnat.ui.addad.AddAdFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;
import com.nakilnat.nakilnat.ui.myships.MyShipsFragment;
import com.nakilnat.nakilnat.ui.onboarding.OnboardingFragment;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MySettingsFragment extends AppCompatActivity {

    BottomNavigationView bottomBar;
    TextView topBarText;
    ImageView topBarBack;
    CardView permissionsCardView, permissionSubCardView, passwordTransactionsCardView, helpCardView, helpSubCardView, deleteAccountCardView, signOutCardView;
    ImageView permissionsArrow, helpArrow, permissionEmailToggle, permissionNotificationToggle, permissionPhoneToggle, permissionSmsToggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_settings);
        bottomBarSetup();
        InitSubContents();
        getPermissions();
        navigationController();
        myAccountCallBack(createRequest());
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
        permissionEmailToggle = (ImageView) findViewById(R.id.permission_email_toggle);
        permissionNotificationToggle = (ImageView) findViewById(R.id.permission_notification_toggle);
        permissionPhoneToggle = (ImageView) findViewById(R.id.permission_phone_toggle);
        permissionSmsToggle = (ImageView) findViewById(R.id.permission_sms_toggle);
    }

    private void getPermissions() {
        permissionEmailToggle.setImageResource(R.drawable.ic_toggle_active);
        permissionEmailToggle.setTag(R.drawable.ic_toggle_active);
        permissionNotificationToggle.setImageResource(R.drawable.ic_toggle_active);
        permissionNotificationToggle.setTag(R.drawable.ic_toggle_active);
        permissionPhoneToggle.setImageResource(R.drawable.ic_toggle_active);
        permissionPhoneToggle.setTag(R.drawable.ic_toggle_active);
        permissionSmsToggle.setImageResource(R.drawable.ic_toggle_active);
        permissionSmsToggle.setTag(R.drawable.ic_toggle_active);
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

        permissionEmailToggle.setOnClickListener(view -> {
            int drawable = (Integer) permissionEmailToggle.getTag();
            switch(drawable) {
                case R.drawable.ic_toggle_active:
                    permissionEmailToggle.setImageResource(R.drawable.ic_toggle_passive);
                    permissionEmailToggle.setTag(R.drawable.ic_toggle_passive);
                    break;

                case R.drawable.ic_toggle_passive:
                    permissionEmailToggle.setImageResource(R.drawable.ic_toggle_active);
                    permissionEmailToggle.setTag(R.drawable.ic_toggle_active);
                    break;
            }
            updatePermissionCallBack(updatePermissionRequest());
        });

        permissionPhoneToggle.setOnClickListener(view -> {
            int drawable = (Integer) permissionPhoneToggle.getTag();
            switch(drawable) {
                case R.drawable.ic_toggle_active:
                    permissionPhoneToggle.setImageResource(R.drawable.ic_toggle_passive);
                    permissionPhoneToggle.setTag(R.drawable.ic_toggle_passive);
                    break;

                case R.drawable.ic_toggle_passive:
                    permissionPhoneToggle.setImageResource(R.drawable.ic_toggle_active);
                    permissionPhoneToggle.setTag(R.drawable.ic_toggle_active);
                    break;
            }
            updatePermissionCallBack(updatePermissionRequest());
        });

        permissionNotificationToggle.setOnClickListener(view -> {
            int drawable = (Integer) permissionNotificationToggle.getTag();
            switch(drawable) {
                case R.drawable.ic_toggle_active:
                    permissionNotificationToggle.setImageResource(R.drawable.ic_toggle_passive);
                    permissionNotificationToggle.setTag(R.drawable.ic_toggle_passive);
                    break;

                case R.drawable.ic_toggle_passive:
                    permissionNotificationToggle.setImageResource(R.drawable.ic_toggle_active);
                    permissionNotificationToggle.setTag(R.drawable.ic_toggle_active);
                    break;
            }
            updatePermissionCallBack(updatePermissionRequest());
        });

        permissionSmsToggle.setOnClickListener(view -> {
            int drawable = (Integer) permissionSmsToggle.getTag();
            switch(drawable) {
                case R.drawable.ic_toggle_active:
                    permissionSmsToggle.setImageResource(R.drawable.ic_toggle_passive);
                    permissionSmsToggle.setTag(R.drawable.ic_toggle_passive);
                    break;

                case R.drawable.ic_toggle_passive:
                    permissionSmsToggle.setImageResource(R.drawable.ic_toggle_active);
                    permissionSmsToggle.setTag(R.drawable.ic_toggle_active);
                    break;
            }
            updatePermissionCallBack(updatePermissionRequest());
        });
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
                    Toast.makeText(MySettingsFragment.this, "Hesap bilgileri sağlandı", Toast.LENGTH_LONG).show();
                    switch(myAccountResponse.getPermissionEmail()) {
                        case "0":
                            permissionEmailToggle.setImageResource(R.drawable.ic_toggle_passive);
                            permissionEmailToggle.setTag(R.drawable.ic_toggle_passive);
                            break;

                        case "1":
                            permissionEmailToggle.setImageResource(R.drawable.ic_toggle_active);
                            permissionEmailToggle.setTag(R.drawable.ic_toggle_active);
                            break;
                    }

                    switch(myAccountResponse.getPermissionPhone()) {
                        case "0":
                            permissionPhoneToggle.setImageResource(R.drawable.ic_toggle_passive);
                            permissionPhoneToggle.setTag(R.drawable.ic_toggle_passive);
                            break;

                        case "1":
                            permissionPhoneToggle.setImageResource(R.drawable.ic_toggle_active);
                            permissionPhoneToggle.setTag(R.drawable.ic_toggle_active);
                            break;
                    }

                    switch(myAccountResponse.getPermissionNotification()) {
                        case "0":
                            permissionNotificationToggle.setImageResource(R.drawable.ic_toggle_passive);
                            permissionNotificationToggle.setTag(R.drawable.ic_toggle_passive);
                            break;

                        case "1":
                            permissionNotificationToggle.setImageResource(R.drawable.ic_toggle_active);
                            permissionNotificationToggle.setTag(R.drawable.ic_toggle_active);
                            break;
                    }

                    switch(myAccountResponse.getPermissionSms()) {
                        case "0":
                            permissionSmsToggle.setImageResource(R.drawable.ic_toggle_passive);
                            permissionSmsToggle.setTag(R.drawable.ic_toggle_passive);
                            break;

                        case "1":
                            permissionSmsToggle.setImageResource(R.drawable.ic_toggle_active);
                            permissionSmsToggle.setTag(R.drawable.ic_toggle_active);
                            break;
                    }


                } else {
                    Toast.makeText(MySettingsFragment.this, "Hesap bilgileri sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MyAccountResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public DeleteAccountRequest deleteAccountRequest(String id) {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest();
        deleteAccountRequest.setToken("korayaman");
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

    public UpdatePermissionRequest updatePermissionRequest() {
        String permissionMail, permissionNotification, permissionSms, permissionPhone;

        int drawableSms = (Integer) permissionSmsToggle.getTag();
        int drawableNotification = (Integer) permissionNotificationToggle.getTag();
        int drawableEmail = (Integer) permissionEmailToggle.getTag();
        int drawablePhone = (Integer) permissionPhoneToggle.getTag();

        permissionNotification = drawableCheck(drawableNotification);
        permissionMail = drawableCheck(drawableEmail);
        permissionPhone = drawableCheck(drawablePhone);
        permissionSms = drawableCheck(drawableSms);
        UpdatePermissionRequest updatePermissionRequest = new UpdatePermissionRequest();
        updatePermissionRequest.setToken("korayaman");
        updatePermissionRequest.setEposta(permissionMail);
        updatePermissionRequest.setTelefon(permissionPhone);
        updatePermissionRequest.setSms(permissionSms);
        updatePermissionRequest.setBildirim(permissionNotification);

        return updatePermissionRequest;
    }

    private String drawableCheck (int drawable) {
        String permission = "0";
        switch(drawable) {
            case R.drawable.ic_toggle_active:
                permission = "1";
                break;

            case R.drawable.ic_toggle_passive:
                permission = "0";
                break;
        }

        return permission;
    }

    public void updatePermissionCallBack(UpdatePermissionRequest updatePermissionRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().updatePermission(updatePermissionRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {
                    Toast.makeText(MySettingsFragment.this, "İzinleriniz güncellendi", Toast.LENGTH_LONG).show();
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