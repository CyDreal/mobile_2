package com.example.main_mobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Latihan_2_1_RegisterAPI {
    @GET("get_product.php")
    Call<List<Latihan_2_1_DataProducts>> view();
}
