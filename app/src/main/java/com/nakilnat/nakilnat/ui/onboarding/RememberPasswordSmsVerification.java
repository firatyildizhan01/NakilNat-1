package com.nakilnat.nakilnat.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.NewPasswordRequest;
import com.nakilnat.nakilnat.models.request.PhoneNumberRequest;
import com.nakilnat.nakilnat.models.request.SmsRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.SmsResponse;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RememberPasswordSmsVerification extends AppCompatActivity {
    Button smsVerificationButton;
    EditText telephoneNumber, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_remember_password_sms);
        password = (EditText) findViewById(R.id.password_remember_code);
        smsVerificationButton = (Button) findViewById(R.id.password_remember_button);
        smsVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.length() != 0) {
                    rememberSmsVerificationCallBack(createRequest(password.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen sms kodunu giriniz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public SmsRequest createRequest(String verificationCode) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setSmsKodu(verificationCode);
        return smsRequest;
    }

    public void rememberSmsVerificationCallBack(SmsRequest smsRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().smsVerificationRememberPassword(smsRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {

                    Intent intent = new Intent(RememberPasswordSmsVerification.this, ApplicationPageFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(RememberPasswordSmsVerification.this, defaultResponse.getResult(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}


