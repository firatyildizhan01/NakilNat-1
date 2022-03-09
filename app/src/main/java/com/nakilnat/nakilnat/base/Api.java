package com.nakilnat.nakilnat.base;

import com.nakilnat.nakilnat.models.request.LoginRequest;
import com.nakilnat.nakilnat.models.request.NewPasswordRequest;
import com.nakilnat.nakilnat.models.request.PhoneNumberRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("mobile.php?sayfa=uyeGiris")
    Call<DefaultResponse>userLogin(@Body LoginRequest loginRequest);

    @POST("mobile.php?sayfa=unuttum")
    Call<DefaultResponse>sendPhoneNumber(@Body PhoneNumberRequest phoneNumberRequest);

    @POST("mobile.php?sayfa=yeniSifre")
    Call<DefaultResponse>selectPassword(@Body NewPasswordRequest newPasswordRequest);

}