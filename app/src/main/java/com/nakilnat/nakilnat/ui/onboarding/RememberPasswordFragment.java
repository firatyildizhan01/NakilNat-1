package com.nakilnat.nakilnat.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.RetrofitClient;
import com.nakilnat.nakilnat.models.LoginResponse;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RememberPasswordFragment extends AppCompatActivity {
    Button smsVerificationButton;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_remember_password);
        password = (EditText) findViewById(R.id.password_remember_code);
        smsVerificationButton = (Button) findViewById(R.id.password_remember_button);
        smsVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.length() != 0) {
                    smsVerificationCallBack(password.getText().toString());
                    Intent homePage = new Intent(RememberPasswordFragment.this, HomePageFragment.class);
                    startActivity(homePage);
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen sms kodunu giriniz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void smsVerificationCallBack(String password) {
        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().smsVerification(password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()) {

                    //SharedPrefManager.getInstance(LoginFragment.this)
                    //.saveUser(loginResponse.getUser());

                    /*Intent intent = new Intent(LoginFragment.this, HomePageFragment.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
*/
                    Intent intent = new Intent(RememberPasswordFragment.this, NewPasswordFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(RememberPasswordFragment.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}


