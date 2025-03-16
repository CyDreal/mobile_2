package com.example.main_mobile;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Latihan_3_1 extends AppCompatActivity {

    public static final String URL="https://hagfish-more-uniquely.ngrok-free.app/mobile_2_API/";
    private List<Latihan_2_1_DataProducts> results;
    private Latihan_2_1_Adapter adapter;
    RecyclerView recyclerView;
    com.google.android.material.appbar.MaterialToolbar btnBack;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_latihan31);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // back button
        btnBack = findViewById(R.id.btnBack_lat_3_1);
        btnBack.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvWelcome = findViewById(R.id.tvWelcome_lat_3_1);
        tvWelcome.setText("Welcome = "+getIntent().getStringExtra("nama")+"("+getIntent().getStringExtra("email")+")");
        // Inisialisasi list
        results = new ArrayList<>();

        // Setyp RecyclerView
        recyclerView = findViewById(R.id.recycleView_lat_3_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Latihan_2_1_Adapter(this, results);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                // Menambahkan margin pada setiap sisi item (atas, bawah, kiri, kanan)
                outRect.left = 10;   // Jarak kiri
                outRect.right = 10;  // Jarak kanan
                outRect.top = 5;    // Jarak atas
                outRect.bottom = 5; // Jarak bawah
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

        // Load Data
        loadDataMahasiswa();
    }

    private void loadDataMahasiswa() {
        Retrofit retrofit = new Retrofit.
                Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        Latihan_2_1_RegisterAPI api = retrofit.create(Latihan_2_1_RegisterAPI.class);
        Call<List<Latihan_2_1_DataProducts>> call = api.view();

        Log.i("Info Load","Load Data Product Diakses");
        call.enqueue(new Callback<List<Latihan_2_1_DataProducts>>() {
            @Override
            public void onResponse(Call<List<Latihan_2_1_DataProducts>> call, Response<List<Latihan_2_1_DataProducts>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    results.clear();
                    results.addAll(response.body());
                    Log.i("Info Load", "Respon Body, Persiapan Masuk Adapter");
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Latihan_2_1_DataProducts>> call, Throwable t) {
                Log.i("Info Load", "Load Gagal "+t.toString());
            }
        });
    }
}