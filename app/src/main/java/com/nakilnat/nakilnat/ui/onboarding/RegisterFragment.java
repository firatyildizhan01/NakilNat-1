package com.nakilnat.nakilnat.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.RetrofitClient;
import com.nakilnat.nakilnat.models.LoginResponse;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends AppCompatActivity {

    Button login;
    EditText nameSurname, phoneNumber, email, password, repassword;
    CheckBox workerCheckBox, employerCheckBox;
    TextView loginSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        nameSurname = (EditText)findViewById(R.id.register_name_surname);
        phoneNumber = (EditText)findViewById(R.id.register_phone_number);
        email = (EditText)findViewById(R.id.register_email);
        password = (EditText)findViewById(R.id.register_password);
        repassword = (EditText)findViewById(R.id.register_repassword);

        workerCheckBox = (CheckBox)findViewById(R.id.worker_checkbox);
        employerCheckBox = (CheckBox)findViewById(R.id.employer_checkbox);
        login = (Button)findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameSurname.length() != 0 && phoneNumber.length() != 0 && email.length() != 0 && password.length() != 0 && repassword.length() != 0) {
                    if (employerCheckBox.isChecked() || workerCheckBox.isChecked()) {
                        if (password.getText().toString().equals(repassword.getText().toString())) {
                            if (phoneNumber.length() == 10) {
                                registerCallBack(nameSurname.getText().toString(), phoneNumber.getText().toString(), email.getText().toString(), password.getText().toString(), password.getText().toString());
                            } else {
                                Toast.makeText(getApplicationContext(),"Telefon numaranız 10 karakter olmalıdır!!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Şifreler aynı olmalıdır!!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Yük taşıyan/Yük alan seçimi yapınız!!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(),"Lütfen alanları doldurunuz!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        loginSignup = (TextView) findViewById(R.id.register_login);
        loginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterFragment.this, LoginFragment.class);
                startActivity(intent);
            }
        });
    }

    public void registerCallBack(String name, String phoneNumber, String email, String password, String accountType) {
        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().createUser(name, phoneNumber, email, password, accountType);

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
                    Intent homePage = new Intent(RegisterFragment.this, SmsVerificationFragment.class);
                    startActivity(homePage);

                } else {
                    Toast.makeText(RegisterFragment.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}
