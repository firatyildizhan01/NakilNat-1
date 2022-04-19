package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.UpdateInvoiceRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.MyInvoiceResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyInvoicesFragment extends BaseFragment {

    EditText nickName, address, tax, taxNo;
    Button updateInvoice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_invoices);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Fatura Bilgilerim");
        pageInit();
        //invoiceCallback(createRequest());
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    /*public void invoiceCallback(DefaultRequest defaultRequest) {
        Call<MyInvoiceResponse> call = ApiClient.getApiClient().myInvoice(defaultRequest);

        call.enqueue(new Callback<MyInvoiceResponse>() {
            @Override
            public void onResponse(Call<MyInvoiceResponse> call, Response<MyInvoiceResponse> response) {
                MyInvoiceResponse myInvoiceResponse = response.body();
                if (myInvoiceResponse.getId() != null) {
                    Toast.makeText(MyInvoicesFragment.this, "Fatura bilgileriniz alındı", Toast.LENGTH_LONG).show();
                    nickName.setText(myInvoiceResponse.getUnvan());
                    address.setText(myInvoiceResponse.getAdres());
                    tax.setText(myInvoiceResponse.getVergiDaire());
                    taxNo.setText(myInvoiceResponse.getVergiNo());
                } else {
                    Toast.makeText(MyInvoicesFragment.this, "Kayıtlı fatura bulunamamıştır!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MyInvoiceResponse> call, Throwable t) {
                Toast.makeText(MyInvoicesFragment.this, "Servis hatası!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public UpdateInvoiceRequest updateRequest(String unvan, String adres, String vergiDaire, String vergiNo) {
        UpdateInvoiceRequest updateRequest = new UpdateInvoiceRequest();
        updateRequest.setToken("korayaman");
        updateRequest.setUnvan(unvan);
        updateRequest.setAdres(adres);
        updateRequest.setVergidaire(vergiDaire);
        updateRequest.setVergino(vergiNo);
        return updateRequest;
    }

    public void updateInvoiceCallback(UpdateInvoiceRequest updateRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().updateInvoice(updateRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("adresGuncellendi")) {
                    Toast.makeText(MyInvoicesFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MyInvoicesFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

     */

    private void pageInit() {
        nickName = findViewById(R.id.my_inv_nickname_edt);
        address = findViewById(R.id.my_inv_address_edt);
        tax = findViewById(R.id.my_inv_tax_edt);
        taxNo = findViewById(R.id.my_inv_tax_no_edt);
        updateInvoice = findViewById(R.id.update_invoice);

        updateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //updateInvoiceCallback(updateRequest(nickName.getText().toString(), address.getText().toString(), tax.getText().toString(), taxNo.getText().toString()));
            }
        });
    }
}