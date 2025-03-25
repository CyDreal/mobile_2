package com.example.main_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Praktikum_4_1 extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum41);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText etNama = findViewById(R.id.etNama_pra_4_1);
        EditText etUsia = findViewById(R.id.etUsia_pra_4_1);

        Spinner spinJenisKel = findViewById(R.id.spinJenisKel_pra_4_1);

        String[] jeniskel = new String[]{"Laki-laki", "Perempuan"};
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jeniskel);
        spinJenisKel.setAdapter(listAdapter);

        sharedPreferences = getSharedPreferences("data1", MODE_PRIVATE);
        sharedPreferences.contains("nama");
        sharedPreferences.contains("jenisKel");
        sharedPreferences.contains("usia");

        Button button = findViewById(R.id.btn_pra_4_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nama", etNama.getText().toString());
                editor.putString("jenisKel", etNama.getText().toString());
                editor.putInt("usia", Integer.parseInt(etUsia.getText().toString()));
                editor.apply();

                etNama.setText("");
                spinJenisKel.setSelection(0);
                etUsia.setText("");
            }
        });
        Button button2 = findViewById(R.id.btn2_pra_4_1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Praktikum_4_1.this, Praktikum_4_1_home.class);
                startActivity(intent);
            }
        });
    }
}