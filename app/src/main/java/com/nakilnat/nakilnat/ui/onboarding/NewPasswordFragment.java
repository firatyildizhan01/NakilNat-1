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
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordFragment extends AppCompatActivity {
    Button verificationButton;
    EditText password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_password);
        password = (EditText) findViewById(R.id.new_password);
        repassword = (EditText) findViewById(R.id.new_repassword);
        verificationButton = (Button) findViewById(R.id.newpassword_button);
        verificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.length() != 0 || repassword.length() != 0) {
                    if (password.getText().toString().equals(repassword.getText().toString())) {
                        //rePasswordCallBack(password.getText().toString());
                        Intent homePage = new Intent(NewPasswordFragment.this, ApplicationPageFragment.class);
                        startActivity(homePage);
                    } else {
                        Toast.makeText(getApplicationContext(), "Şifre eşleşmiyor!!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen alanları doldurunuz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void rePasswordCallBack(String password) {
        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().smsVerification(password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (true) {

                  /*  //SharedPrefManager.getInstance(LoginFragment.this)
                    //.saveUser(loginResponse.getUser());

                    /*Intent intent = new Intent(LoginFragment.this, HomePageFragment.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);*/

                    Intent intent = new Intent(NewPasswordFragment.this, ApplicationPageFragment.class);
                    startActivity(intent);

                } else {
                   // Toast.makeText(NewPasswordFragment.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}


