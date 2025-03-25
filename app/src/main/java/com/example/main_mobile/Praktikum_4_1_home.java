package com.example.main_mobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Praktikum_4_1_home extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum41_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView tvNama = findViewById(R.id.tvNama_pra_4_1_home);
        TextView tvJenisKel = findViewById(R.id.tvJenisKel_pra_4_1_home);
        TextView tvUsia = findViewById(R.id.tvUsia_pra_4_1_home);

        sharedPreferences = getSharedPreferences("data1", MODE_PRIVATE);
        tvNama.setText(sharedPreferences.getString("nama", null));
        tvJenisKel.setText(sharedPreferences.getString("jenisKel", null));
        tvUsia.setText(String.valueOf(sharedPreferences.getInt("usia", 0)));
    }
}