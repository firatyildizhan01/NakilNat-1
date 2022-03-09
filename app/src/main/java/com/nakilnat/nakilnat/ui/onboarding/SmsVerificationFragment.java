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
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;

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
        password = (EditText) findViewById(R.id.password_remember_code);
        smsVerificationButton = (Button) findViewById(R.id.password_remember_button);
        smsVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.length() != 0) {
                    //smsVerificationCallBack(password.getText().toString());
                    Intent homePage = new Intent(SmsVerificationFragment.this, NewPasswordFragment.class);
                    startActivity(homePage);
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen sms kodunu giriniz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public NewPasswordRequest createRequest(String verificationCode, String password) {
        NewPasswordRequest newPasswordRequest = new NewPasswordRequest();
        newPasswordRequest.setDogrulama(verificationCode);
        newPasswordRequest.setSifre(password);
        return newPasswordRequest;
    }

    public void loginCallBack(NewPasswordRequest newPasswordRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().selectPassword(newPasswordRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();

                if (defaultResponse.getResult().toString().equals("OK")) {

                    //SharedPrefManager.getInstance(LoginFragment.this)
                    //.saveUser(loginResponse.getUser());

                    /*Intent intent = new Intent(LoginFragment.this, HomePageFragment.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
*/
                    Intent intent = new Intent(SmsVerificationFragment.this, ApplicationPageFragment.class);
                    startActivity(intent);

                } else {
                    //Toast.makeText(LoginFragment.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}


