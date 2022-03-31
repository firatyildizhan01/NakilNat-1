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
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import com.nakilnat.nakilnat.ui.profile.MyAccountFragment;

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
                        selectPasswordCallBack(createRequest(password.getText().toString(), repassword.getText().toString()));
                    } else {
                        Toast.makeText(getApplicationContext(), "Şifre eşleşmiyor!!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen alanları doldurunuz!!", Toast.LENGTH_LONG).show();
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

    public void selectPasswordCallBack(NewPasswordRequest newPasswordRequest) {
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
                    Toast.makeText(NewPasswordFragment.this, "Şifre belirleme başarılı", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NewPasswordFragment.this, MyAccountFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(NewPasswordFragment.this, "Şifre belirleme başarısız!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}



