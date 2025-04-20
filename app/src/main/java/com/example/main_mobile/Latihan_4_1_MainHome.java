package com.example.main_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Latihan_4_1_MainHome extends AppCompatActivity {
    private TextView tvWelcome, tvEmail, tvAlamat;
    private Button btnLogout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_latihan41_main_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("Login_4_1", MODE_PRIVATE);

        // Initialize views
        tvWelcome = findViewById(R.id.tvWelcome_lat_4_1_main_home);
        btnLogout = findViewById(R.id.btnLogout_lat_4_1_main_home);

        // Display user data
        String nama = sharedPreferences.getString("nama", "User");
        String email = sharedPreferences.getString("email", "-");
        String alamat = sharedPreferences.getString("alamat", "-");

        tvWelcome.setText(String.format("Welcome, %s!", nama));

        // Handle logout
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(Latihan_4_1_MainHome.this, Latihan_4_1.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}