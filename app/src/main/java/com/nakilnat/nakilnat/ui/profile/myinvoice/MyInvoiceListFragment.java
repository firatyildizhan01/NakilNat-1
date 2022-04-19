package com.nakilnat.nakilnat.ui.profile.myinvoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.RemoveAdressRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.MyInvoiceResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.profile.MyInvoicesFragment;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyInvoiceListFragment extends BaseFragment implements MyInvoiceListAdapter.OnInvoiceListener {

    private List<MyInvoiceResponse> invoiceList;
    MyInvoiceListAdapter adapter = null;
    private int deleteInvoiceId = 0;
    TextView topBarRightText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_invoices_list);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Faturalarım");
        initRightTitle("Fatura Ekle");
        topBarRightText = findViewById(R.id.top_bar_right_title);
        topBarRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInvoiceListFragment.this, MyInvoicesFragment.class);
                startActivity(intent);
            }
        });
        myAdressListCallBack(createRequest());
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myAdressListCallBack(DefaultRequest defaultRequest) {
        Call<List<MyInvoiceResponse>> call = ApiClient.getApiClient().myInvoice(defaultRequest);

        call.enqueue(new Callback<List<MyInvoiceResponse>>() {
            @Override
            public void onResponse(Call<List<MyInvoiceResponse>> call, Response<List<MyInvoiceResponse>> response) {
                invoiceList = response.body();
                if (response != null) {
                    Toast.makeText(MyInvoiceListFragment.this, "Adres bilgilerim sağlandı", Toast.LENGTH_LONG).show();
                    adapter = new MyInvoiceListAdapter(invoiceList, MyInvoiceListFragment.this, MyInvoiceListFragment.this);
                    setAdapter();
                } else {
                    Toast.makeText(MyInvoiceListFragment.this, "Adres bilgilerim sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyInvoiceResponse>> call, Throwable t) {
                Toast.makeText(MyInvoiceListFragment.this, "Servis hatası!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setAdapter() {
        RecyclerView listview = findViewById(R.id.invoices_list);
        listview.setHasFixedSize(true);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(MyInvoiceListFragment.this));
    }


    public RemoveAdressRequest deleteRequest(String id) {
        RemoveAdressRequest removeAdressRequest = new RemoveAdressRequest();
        removeAdressRequest.setToken("korayaman");
        removeAdressRequest.setId(id);
        return removeAdressRequest;
    }

    public void removeAdressCallBack(RemoveAdressRequest removeAdressRequest) {
        Call<DefaultResponse> call = ApiClient.getApiClient().removeAdress(removeAdressRequest);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse.getResult().toString().equals("OK")) {
                    Toast.makeText(MyInvoiceListFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                    System.out.println(deleteInvoiceId);
                    invoiceList.remove(deleteInvoiceId);
                    adapter.notifyItemRemoved(deleteInvoiceId);
                } else {
                    Toast.makeText(MyInvoiceListFragment.this, defaultResponse.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    @Override
    public void onRemoveClick(int position) {
        deleteInvoiceId = position;
        //removeAdressCallBack(deleteRequest(invoiceList.get(deleteInvoiceId).getId()));
    }

    @Override
    public void onEditClick(int position) {
        /*Intent intent = new Intent(MyInvoiceListFragment.this, MyInvoicesFragment.class);
        intent.putExtra("id", invoiceList.get(position).getId());
        intent.putExtra("adressHeader", invoiceList.get(position).getAdressHeader());
        intent.putExtra("adressCity", invoiceList.get(position).getAdressCity());
        intent.putExtra("adressDistrict", invoiceList.get(position).getAdressDistrict());
        intent.putExtra("adressStreet", invoiceList.get(position).getAdressStreet());
        intent.putExtra("adressNeighborhood", invoiceList.get(position).getAdressNeighborhood());
        intent.putExtra("adressBuildingNo", invoiceList.get(position).getAdressBuildingNo());
        intent.putExtra("adressFloor", invoiceList.get(position).getAdressFloor());
        intent.putExtra("adressApertment", invoiceList.get(position).getAdressApertment());
        intent.putExtra("adressOfficial", invoiceList.get(position).getAdressOfficial());
        intent.putExtra("adressPhoneNumber", invoiceList.get(position).getAdress());
        startActivity(intent);

         */
    }
}