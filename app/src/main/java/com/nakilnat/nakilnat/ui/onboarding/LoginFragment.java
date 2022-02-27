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
import com.nakilnat.nakilnat.base.RetrofitClient;
import com.nakilnat.nakilnat.models.LoginResponse;
import com.nakilnat.nakilnat.storage.SharedPrefManager;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;

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
        email = (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);
        rememberPassword = (TextView)findViewById(R.id.password_remember);

        rememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginFragment.this, RememberPasswordFragment.class);
                startActivity(intent);
            }
        });

        login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.length() != 0 && password.length() != 0) {
                    loginCallBack(email.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(),"Lütfen alanları doldurunuz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        loginSignup = (TextView) findViewById(R.id.login_register);
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

    public void loginCallBack(String email, String password) {
        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email, password);

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
                    Intent intent = new Intent(LoginFragment.this, HomePageFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginFragment.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            System.out.println(t);
            }
        });
    }
}
