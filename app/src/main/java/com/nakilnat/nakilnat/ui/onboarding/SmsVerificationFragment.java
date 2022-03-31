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
import com.nakilnat.nakilnat.ui.profile.MyAccountFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmsVerificationFragment extends AppCompatActivity {
    Button smsVerificationButton;
    EditText telephoneNumber, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sms_verification);
        password = (EditText) findViewById(R.id.password_register_code);
        smsVerificationButton = (Button) findViewById(R.id.password_register_button);
        smsVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.length() != 0) {
                    smsVerificationCallBack(createRequest(password.getText().toString()));
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

    public void smsVerificationCallBack(SmsRequest smsRequest) {
        Call<SmsResponse> call = ApiClient.getApiClient().smsVerification(smsRequest);

        call.enqueue(new Callback<SmsResponse>() {
            @Override
            public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                SmsResponse smsResponse = response.body();

                if (smsResponse.getResult().toString().equals("onaylandi")) {

                    //SharedPrefManager.getInstance(LoginFragment.this)
                    //.saveUser(loginResponse.getUser());

                    /*Intent intent = new Intent(LoginFragment.this, HomePageFragment.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
*/
                    Toast.makeText(SmsVerificationFragment.this, "User Id: " + smsResponse.getUserId().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SmsVerificationFragment.this, MyAccountFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(SmsVerificationFragment.this, smsResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SmsResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}


