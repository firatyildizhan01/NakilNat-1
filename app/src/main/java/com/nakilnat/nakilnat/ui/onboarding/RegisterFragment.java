package com.nakilnat.nakilnat.ui.onboarding;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.LoginRequest;
import com.nakilnat.nakilnat.models.request.RegisterRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.ui.application.ApplicationPageFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends AppCompatActivity {

    Button login;
    EditText nameSurname, phoneNumber, email, password, repassword;
    TextView loginSignup;
    WebView agreementPopupText;
    CheckBox agreementCheckBox;
    private Dialog dialog;
    private static final String agreement_Kvkk = "https://nakilnat.com/sayfa?link=Ki%C5%9Fisel%20Verilerin%20Korunmas%C4%B1";
    private static final String agreement_Cookie = "https://nakilnat.com/sayfa?link=cerezpolitikasi";
    private static final String agreement_Terms_Of_Use = "https://nakilnat.com/sayfa?link=kullanimKosullari";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        nameSurname = (EditText)findViewById(R.id.register_name_surname);
        phoneNumber = (EditText)findViewById(R.id.register_phone_number);
        email = (EditText)findViewById(R.id.register_email);
        password = (EditText)findViewById(R.id.register_password);
        repassword = (EditText)findViewById(R.id.register_repassword);
        agreementCheckBox = (CheckBox)findViewById(R.id.agreement_checkBox);
        login = (Button)findViewById(R.id.register_button);
        TextView textView = findViewById(R.id.agreement_textView);

        SpannableString ss = new SpannableString("Kullanım Koşulları'nı, Çerez Politikası'nı ve Aydınlatma Metni'ni okudum,onaylıyorum");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                agreementDialog(agreement_Terms_Of_Use);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                agreementDialog(agreement_Cookie);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                agreementDialog(agreement_Kvkk);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan4 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.BLACK);
            }
        };

        ClickableSpan clickableSpan5 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {

            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.BLACK);
            }
        };

        ss.setSpan(clickableSpan, 0, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan2, 23, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan3, 46, 60, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan4, 65, 84, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan5, 42, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameSurname.length() != 0 && phoneNumber.length() != 0 && email.length() != 0 && password.length() != 0 && repassword.length() != 0) {
                    if (password.getText().toString().equals(repassword.getText().toString())) {
                        if (phoneNumber.length() == 10) {
                            if (agreementCheckBox.isChecked()) {
                                registerCallBack(createRequest(nameSurname.getText().toString(), phoneNumber.getText().toString(), email.getText().toString(), password.getText().toString()));
                            } else {
                                Toast.makeText(getApplicationContext(), "Sözleşmeyi kabul ediniz!!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Telefon numaranız 10 karakter olmalıdır!!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Şifreler aynı olmalıdır!!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Lütfen alanları doldurunuz!!", Toast.LENGTH_LONG).show();
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

    public RegisterRequest createRequest(String firma, String phoneNumber, String email, String password) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirma(firma);
        registerRequest.setCep_tel(phoneNumber);
        registerRequest.setEposta(email);
        registerRequest.setSifre(password);
        return registerRequest;
    }

    public void registerCallBack(RegisterRequest registerRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().register(registerRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("yeniKayit")) {

                    //SharedPrefManager.getInstance(LoginFragment.this)
                    //.saveUser(loginResponse.getUser());

                    /*Intent intent = new Intent(LoginFragment.this, HomePageFragment.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
*/
                    Toast.makeText(RegisterFragment.this, "Kayıt başarılı", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterFragment.this, SmsVerificationFragment.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(RegisterFragment.this, "Kayıt başarısız!!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
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

    public void agreementDialog(String url) {
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
       agreementPopupText.loadUrl(url);

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

