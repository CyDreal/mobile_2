package com.example.main_mobile.ui.product;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.main_mobile.Latihan_2_1_DataProducts;
import com.example.main_mobile.Latihan_2_1_RegisterAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductViewModel extends ViewModel {

    public static final String URL="https://hagfish-more-uniquely.ngrok-free.app/mobile_2_API/";

    private MutableLiveData<List<Latihan_2_1_DataProducts>> products;

    public ProductViewModel() {
        products = new MutableLiveData<List<Latihan_2_1_DataProducts>>();
    }

    public LiveData<List<Latihan_2_1_DataProducts>> getProducts() {
        return products;
    }

    public void loadProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Latihan_2_1_RegisterAPI api = retrofit.create(Latihan_2_1_RegisterAPI.class);
        Call<List<Latihan_2_1_DataProducts>> call = api.view();

        call.enqueue(new Callback<List<Latihan_2_1_DataProducts>>() {
            @Override
            public void onResponse(Call<List<Latihan_2_1_DataProducts>> call, Response<List<Latihan_2_1_DataProducts>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Latihan_2_1_DataProducts>> call, Throwable t) {
                Log.e("ProductViewModel", "Error loading products: " + t.getMessage());
            }
        });
    }
}