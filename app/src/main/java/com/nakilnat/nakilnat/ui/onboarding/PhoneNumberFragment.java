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
import com.nakilnat.nakilnat.models.request.PhoneNumberRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneNumberFragment extends AppCompatActivity {
    Button phoneNumberVerification;
    EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_remember_password);
        phoneNumber = (EditText) findViewById(R.id.password_remember_telephone);
        phoneNumberVerification = (Button) findViewById(R.id.password_phone_number_button);
        phoneNumberVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber.length() != 0) {
                    phoneNumberCallBack(createRequest(phoneNumber.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen sms kodunu giriniz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public PhoneNumberRequest createRequest(String phoneNumber) {
        PhoneNumberRequest phoneNumberRequest = new PhoneNumberRequest();
        phoneNumberRequest.setTel(phoneNumber);
        return phoneNumberRequest;
    }

    public void phoneNumberCallBack(PhoneNumberRequest phoneNumberRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().sendPhoneNumber(phoneNumberRequest);

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
                    Toast.makeText(PhoneNumberFragment.this, "Sms gönderildi", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PhoneNumberFragment.this, RememberPasswordSmsVerification.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(PhoneNumberFragment.this, "Sms gönderilemedi!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}


