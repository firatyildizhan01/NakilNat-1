package com.nakilnat.nakilnat.base;

import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.LoginRequest;
import com.nakilnat.nakilnat.models.request.NewPasswordRequest;
import com.nakilnat.nakilnat.models.request.PhoneNumberRequest;
import com.nakilnat.nakilnat.models.request.UpdatePasswordRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.MyAccountResponse;

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

    @POST("mobile.php?sayfa=sifreGuncelle")
    Call<DefaultResponse>updatePassword(@Body UpdatePasswordRequest updatePasswordRequest);

    @POST("mobile.php?sayfa=hesabim")
    Call<MyAccountResponse>myAccount(@Body DefaultRequest defaultRequest);

    @POST("mobile.php?sayfa=adreslerim")
    Call<DefaultResponse>myAdressList(@Body DefaultRequest defaultRequest);

}