package com.nakilnat.nakilnat.base;

import com.nakilnat.nakilnat.models.DefaultResponse;
import com.nakilnat.nakilnat.models.LoginResponse;
import com.nakilnat.nakilnat.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {


    @FormUrlEncoded
    @POST("createuser")
    Call<LoginResponse> createUser(
            @Field("firma_adi") String name,
            @Field("cep_tel") String phoneNumber,
            @Field("un") String email,
            @Field("pw") String password,
            @Field("hesap_turu") String accountType
    );

    @FormUrlEncoded
    @POST("mobile_db.php?sayfa=uye")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("allusers")
    Call<UsersResponse> getUsers();

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);

}
