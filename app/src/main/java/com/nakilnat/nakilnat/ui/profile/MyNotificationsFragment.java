package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.response.MyNotificationsResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import com.nakilnat.nakilnat.ui.profile.notifications.SwipeHelper;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyNotificationsFragment extends BaseFragment {

    private List<MyNotificationsResponse> notificationList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_notifications);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Bildirimlerim");
        myNotificationsCallBack(createRequest());
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myNotificationsCallBack(DefaultRequest defaultRequest) {
        Call<List<MyNotificationsResponse>> call = ApiClient.getApiClient().myNotifications(defaultRequest);

        call.enqueue(new Callback<List<MyNotificationsResponse>>() {
            @Override
            public void onResponse(Call<List<MyNotificationsResponse>> call, Response<List<MyNotificationsResponse>> response) {
                notificationList = response.body();
                if (response != null && !notificationList.isEmpty()) {
                    Toast.makeText(MyNotificationsFragment.this, "Bilgilendirmelerim bilgisi sağlandı", Toast.LENGTH_LONG).show();
                    NotificationsAdapter adapter = new NotificationsAdapter(notificationList, MyNotificationsFragment.this);
                    RecyclerView listview = findViewById(R.id.notification_list);
                    adapter.getItemCount();
                    listview.setAdapter(adapter);
                    listview.setLayoutManager(new LinearLayoutManager(MyNotificationsFragment.this));
                    ItemTouchHelper.Callback callback = new SwipeHelper(adapter);
                    ItemTouchHelper helper = new ItemTouchHelper(callback);
                    helper.attachToRecyclerView(listview);
                    listview.setHasFixedSize(true);

                } else {
                    Toast.makeText(MyNotificationsFragment.this, "Değerlendirmelerim bilgisi sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyNotificationsResponse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}