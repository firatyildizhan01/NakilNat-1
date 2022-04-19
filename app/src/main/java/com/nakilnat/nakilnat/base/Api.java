package com.nakilnat.nakilnat.base;

import com.nakilnat.nakilnat.models.request.AddAdressRequest;
import com.nakilnat.nakilnat.models.request.DefaultRequest;
import com.nakilnat.nakilnat.models.request.DeleteAccountRequest;
import com.nakilnat.nakilnat.models.request.GetDistrictRequest;
import com.nakilnat.nakilnat.models.request.LoginRequest;
import com.nakilnat.nakilnat.models.request.NewPasswordRequest;
import com.nakilnat.nakilnat.models.request.PhoneNumberRequest;
import com.nakilnat.nakilnat.models.request.RegisterRequest;
import com.nakilnat.nakilnat.models.request.RemoveAdressRequest;
import com.nakilnat.nakilnat.models.request.SmsRequest;
import com.nakilnat.nakilnat.models.request.UpdateAccountRequest;
import com.nakilnat.nakilnat.models.request.UpdateAdressRequest;
import com.nakilnat.nakilnat.models.request.UpdateInvoiceRequest;
import com.nakilnat.nakilnat.models.request.UpdatePasswordRequest;
import com.nakilnat.nakilnat.models.request.UpdatePermissionRequest;
import com.nakilnat.nakilnat.models.response.DefaultResponse;
import com.nakilnat.nakilnat.models.response.GetDistrictResponse;
import com.nakilnat.nakilnat.models.response.GetProvinceResponse;
import com.nakilnat.nakilnat.models.response.MyAccountResponse;
import com.nakilnat.nakilnat.models.response.MyAdressListResponse;
import com.nakilnat.nakilnat.models.response.MyInvoiceResponse;
import com.nakilnat.nakilnat.models.response.MyNotificationsResponse;
import com.nakilnat.nakilnat.models.response.MyReviewsResponse;
import com.nakilnat.nakilnat.models.response.MyWalletTransactionsResponse;
import com.nakilnat.nakilnat.models.response.SmsResponse;
import com.nakilnat.nakilnat.models.response.TotalAmountResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("mobile.php?sayfa=uyeGiris")
    Call<DefaultResponse>userLogin(@Body LoginRequest loginRequest);

    @POST("mobile.php?sayfa=unuttum")
    Call<DefaultResponse>sendPhoneNumber(@Body PhoneNumberRequest phoneNumberRequest);

    @POST("mobile.php?sayfa=smsDogrula")
    Call<DefaultResponse>smsVerificationRememberPassword(@Body SmsRequest smsRequest);

    @POST("mobile.php?sayfa=yeniSifre")
    Call<DefaultResponse>selectPassword(@Body NewPasswordRequest newPasswordRequest);

    @POST("mobile.php?sayfa=sifreGuncelle")
    Call<DefaultResponse>updatePassword(@Body UpdatePasswordRequest updatePasswordRequest);

    @POST("mobile.php?sayfa=hesabim")
    Call<MyAccountResponse>myAccount(@Body DefaultRequest defaultRequest);

    @POST("mobile.php?sayfa=hesapGuncelle")
    Call<DefaultResponse>updateAccount(@Body UpdateAccountRequest updateAccountRequest);

    @POST("mobile.php?sayfa=adreslerim")
    Call<List<MyAdressListResponse>>myAdressList(@Body DefaultRequest defaultRequest);

    @POST("mobile.php?sayfa=yeniAdres")
    Call<DefaultResponse>addAdress(@Body AddAdressRequest addAdressRequest);

    @POST("mobile.php?sayfa=adresKaldir")
    Call<DefaultResponse>removeAdress(@Body RemoveAdressRequest removeAdressRequest);

    @POST("mobile.php?sayfa=adresKaldir")
    Call<DefaultResponse>updateAdress(@Body UpdateAdressRequest updateAdressRequest);

    @POST("mobile.php?sayfa=uyeKayit")
    Call<DefaultResponse>register(@Body RegisterRequest registerRequest);

    @POST("mobile.php?sayfa=yeniKayit")
    Call<SmsResponse>smsVerification(@Body SmsRequest smsRequest);

    @POST("mobile.php?sayfa=faturaAdres")
    Call<List<MyInvoiceResponse>>myInvoice(@Body DefaultRequest defaultRequest);

    @POST("mobile.php?sayfa=faturaguncelle")
    Call<DefaultResponse>updateInvoice(@Body UpdateInvoiceRequest updateInvoiceRequest);

    @POST("mobile.php?sayfa=izinler")
    Call<DefaultResponse>updatePermission(@Body UpdatePermissionRequest updatePermissionRequest);

    @POST("mobile.php?sayfa=hesapsil")
    Call<DefaultResponse>deleteAccount(@Body DeleteAccountRequest deleteAccountRequest);

    @POST("mobile.php?sayfa=bakiyem")
    Call<TotalAmountResponse>totalAmount(@Body DefaultRequest defaultRequest);

    @POST("mobile.php?sayfa=yorumlar")
    Call<List<MyReviewsResponse>>myReviews(@Body DefaultRequest defaultRequest);

    @POST("mobile.php?sayfa=cuzdan")
    Call<List<MyWalletTransactionsResponse>>myWalletTransactions(@Body DefaultRequest defaultRequest);

    @POST("mobile.php?sayfa=bildirimler")
    Call<List<MyNotificationsResponse>>myNotifications(@Body DefaultRequest defaultRequest);

    @POST("mobile.php?sayfa=il")
    Call<List<GetProvinceResponse>>getProvince();

    @POST("mobile.php?sayfa=ilce")
    Call<List<GetDistrictResponse>>getDistrict(@Body GetDistrictRequest getDistrictRequest);
}