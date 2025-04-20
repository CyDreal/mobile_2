package com.example.main_mobile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Praktikum_3_1_RegisterAPI_Login {
    @FormUrlEncoded
    @POST("get_login_31.php")
    Call<ResponseBody> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
