package com.nakilnat.nakilnat.ui.profile;

import android.os.Bundle;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.base.ApiClient;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.response.MyReviewsResponse;
import com.nakilnat.nakilnat.ui.BaseFragment;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewFragment extends BaseFragment {
    private List<MyReviewsResponse> reviewList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_reviews);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Değerlendirmelerim");
        myReviewsCallBack(createRequest());
    }

    public DefaultRequest createRequest() {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken("korayaman");
        return defaultRequest;
    }

    public void myReviewsCallBack(DefaultRequest defaultRequest) {
        Call<List<MyReviewsResponse>> call = ApiClient.getApiClient().myReviews(defaultRequest);

        call.enqueue(new Callback<List<MyReviewsResponse>>() {
            @Override
            public void onResponse(Call<List<MyReviewsResponse>> call, Response<List<MyReviewsResponse>> response) {
                reviewList = response.body();
                if (response != null && !reviewList.isEmpty()) {
                    Toast.makeText(ReviewFragment.this, "Değerlendirmelerim bilgisi sağlandı", Toast.LENGTH_LONG).show();
                    ReviewsAdapter adapter = new ReviewsAdapter(reviewList, ReviewFragment.this);
                    RecyclerView listview = findViewById(R.id.review_list);
                    adapter.getItemCount();
                    listview.setAdapter(adapter);
                    listview.setLayoutManager(new LinearLayoutManager(ReviewFragment.this));
                } else {
                    Toast.makeText(ReviewFragment.this, "Değerlendirmelerim bilgisi sağlanamadı!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyReviewsResponse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}