package com.nakilnat.nakilnat.ui.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.LoginRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends AppCompatActivity {
    Button login;
    EditText email, password;
    TextView loginSignup, rememberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        rememberPassword = (TextView) findViewById(R.id.password_remember);
        login = (Button) findViewById(R.id.login_button);
        loginSignup = (TextView) findViewById(R.id.login_register);

        rememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginFragment.this, PhoneNumberFragment.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.length() != 0 && password.length() != 0) {
                    loginCallBack(createRequest(email.getText().toString(), password.getText().toString(), ""));
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen alanları doldurunuz!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        loginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginFragment.this, RegisterFragment.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

       /* if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, OnboardingFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        */
    }

    public LoginRequest createRequest(String email, String password, String token) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUn(email);
        loginRequest.setPw(password);
        loginRequest.setToken("korayaman");
        return loginRequest;
    }

    public void loginCallBack(LoginRequest loginRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().userLogin(loginRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {

                    Intent intent = new Intent(LoginFragment.this, ApplicationPageFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginFragment.this, defaultResponse.getResult(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

}
