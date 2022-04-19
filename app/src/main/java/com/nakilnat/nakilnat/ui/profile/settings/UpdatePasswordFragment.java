package com.nakilnat.nakilnat.ui.profile.settings;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.UpdatePasswordRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.profile.ProfilePageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdatePasswordFragment extends BaseFragment {

    Button updatePasswordButton;
    EditText currentPassword, newPassword, reNewPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_update_password);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Şifre Güncelle");

        updatePasswordButton = (Button) findViewById(R.id.update_password_button);

        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPassword.length() != 0 && newPassword.length() != 0 && reNewPassword.length() != 0) {
                    if (newPassword.getText().toString().equals(reNewPassword.getText().toString())) {
                        updatePasswordCallBack(createRequest(currentPassword.getText().toString(), newPassword.getText().toString(), ""));
                    } else {
                        Toast.makeText(getApplicationContext(), "Şifreler eşleşmiyor!!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen alanları doldurunuz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public UpdatePasswordRequest createRequest(String currentPassword, String newPassword, String token) {
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEskiSifre(currentPassword);
        updatePasswordRequest.setYeniSifre(newPassword);
        updatePasswordRequest.setToken("korayaman");
        return updatePasswordRequest;
    }

    public void updatePasswordCallBack(UpdatePasswordRequest updatePasswordRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().updatePassword(updatePasswordRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {

                    Intent intent = new Intent(UpdatePasswordFragment.this, ProfilePageFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(UpdatePasswordFragment.this, defaultResponse.getResult(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}