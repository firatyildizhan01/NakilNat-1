package com.nakilnat.nakilnat.ui.onboarding;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.RetrofitClient;
import com.nakilnat.nakilnat.models.DefaultResponse;
import com.nakilnat.nakilnat.models.LoginResponse;
import com.nakilnat.nakilnat.ui.home.HomePageFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends AppCompatActivity {

    Button login;
    EditText nameSurname, phoneNumber, email, password, repassword;
    RadioButton radioWorker, radioEmployer;
    TextView loginSignup;
    WebView agreementPopupText;
    CheckBox agreementCheckBox;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        nameSurname = (EditText)findViewById(R.id.register_name_surname);
        phoneNumber = (EditText)findViewById(R.id.register_phone_number);
        email = (EditText)findViewById(R.id.register_email);
        password = (EditText)findViewById(R.id.register_password);
        repassword = (EditText)findViewById(R.id.register_repassword);

        radioWorker = (RadioButton)findViewById(R.id.radioWorker);
        radioEmployer = (RadioButton)findViewById(R.id.radioEmployer);
        agreementCheckBox = (CheckBox)findViewById(R.id.agreement_checkBox);
        login = (Button)findViewById(R.id.register_button);

        TextView textView = findViewById(R.id.agreement_textView);

        textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                        agreementDialog();
                                        }
                                    });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameSurname.length() != 0 && phoneNumber.length() != 0 && email.length() != 0 && password.length() != 0 && repassword.length() != 0) {
                    if (radioWorker.isChecked() || radioEmployer.isChecked()) {
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
                .getInstance().getApi().createUser(name, phoneNumber, email, password, "0");

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
/*
    public void agreementCallBack(String id) {
        Call<List<DefaultResponse>> call = RetrofitClient
                .getInstance().getApi().agreementText(id);

        call.enqueue(new Callback<List<DefaultResponse>>() {
            @Override
            public void onResponse(Call<List<DefaultResponse>> call, Response<List<DefaultResponse>> response) {
                List<DefaultResponse> defaultResponse = response.body();

                if (defaultResponse != null && !defaultResponse.isEmpty()) {
                    agreementDialog(defaultResponse);
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<List<DefaultResponse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

 */

    public void agreementDialog() {
        String currentUrl = "https://nakilnat.com/sayfa?link=Ki%C5%9Fisel%20Verilerin%20Korunmas%C4%B1";
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.agreement_popup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.popup_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog
        dialog.show();
        Button Okay = dialog.findViewById(R.id.btn_okay);
        agreementPopupText = dialog.findViewById(R.id.agreement_popup_textview);
        agreementPopupText.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
       agreementPopupText.loadUrl(currentUrl);

        dialog.setCancelable(true);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreementCheckBox.setChecked(true);
                dialog.dismiss();
            }
        });


    }
}

