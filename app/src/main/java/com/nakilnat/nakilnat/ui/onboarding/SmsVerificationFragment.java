package com.nakilnat.nakilnat.ui.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;

public class SmsVerificationFragment extends AppCompatActivity {
    Button smsVerificationButton;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sms_verification);
        password = (EditText)findViewById(R.id.verification_password);
        smsVerificationButton = (Button)findViewById(R.id.verification_button);
        smsVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.length() != 0) {
                    if (password.length() == 4) {
                        Intent homePage = new Intent(SmsVerificationFragment.this, HomePageFragment.class);
                        startActivity(homePage);
                    } else {
                        Toast.makeText(getApplicationContext(),"SMS kodu 4 hane olmalıdır!!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Lütfen sms kodunu giriniz!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}