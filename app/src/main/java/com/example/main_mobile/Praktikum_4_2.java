package com.example.main_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Praktikum_4_2 extends AppCompatActivity {

    EditText etProduct, etPrice;
    ArrayList<Praktikum_4_2_Product> listProduct;
    SharedPreferences sharedPreferences;
    Button btnSimpan, btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum42);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("pref_product",MODE_PRIVATE);
        sharedPreferences.contains("listProduct");
        listProduct = new ArrayList<>();
        // mengambil shared pref yang sudah ada
        if (sharedPreferences.contains("listProduct")) {
            Gson getgson = new Gson();
            String getJsonText = sharedPreferences.getString("listProduct",null);
            Praktikum_4_2_Product[] product = getgson.fromJson(getJsonText, Praktikum_4_2_Product[].class);
            for (Praktikum_4_2_Product product1 : product) {
                listProduct.add(product1);
            }
        }

        etProduct = findViewById(R.id.etProduct_pra_4_2);
        etPrice = findViewById(R.id.etPrice_pra_4_2);
        btnSimpan = findViewById(R.id.btnSave_pra_4_2);
        btnDisplay = findViewById(R.id.btnDisplay_pra_4_2);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Praktikum_4_2_Product product = new Praktikum_4_2_Product(etProduct.getText().toString(),
                        Integer.parseInt(etPrice.getText().toString()));
                listProduct.add(product);
                Gson gson = new Gson();
                String jsonText = gson.toJson(listProduct);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("listProduct",jsonText);
                editor.apply();
                Toast.makeText(getApplicationContext(),"Simpan ke sharepreferences",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Praktikum_4_2.this, Praktikum_4_2_MainDisplay.class);
                startActivity(intent);
            }
        });

    }
}