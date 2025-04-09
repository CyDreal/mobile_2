package com.example.main_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.content.SharedPreferences;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main_mobile.adapter.Praktikum_4_2_ProductAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Praktikum_4_2_MainDisplay extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum42_main_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Praktikum_4_2_Product> listProduct = new ArrayList<>();

        sharedPreferences = getSharedPreferences("pref_product",MODE_PRIVATE);
        if (sharedPreferences.contains("listProduct")) {
            sharedPreferences = getSharedPreferences("pref_product",MODE_PRIVATE);
            Gson gson = new Gson();
            String jsonText = sharedPreferences.getString("listProduct",null);
            Praktikum_4_2_Product[] product = gson.fromJson(jsonText, Praktikum_4_2_Product[].class);
            for (Praktikum_4_2_Product product1 : product) {
                listProduct.add(product1);
            }
            Log.i("info pref",""+ listProduct.toString());
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView_pra_4_2_mDisplay);
        Praktikum_4_2_ProductAdapter adapter = new Praktikum_4_2_ProductAdapter(listProduct);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);

        btnKembali = findViewById(R.id.btnKembali_pra_4_2_mDisplay);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Praktikum_4_2_MainDisplay.this, Praktikum_4_2.class);
                startActivity(intent);
            }
        });
    }
}