package com.example.main_mobile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Praktikum_2_2_RegisterAPI {
    @FormUrlEncoded
    @POST("get_login.php")
    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password);

}
