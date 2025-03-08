package com.example.main_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Praktikum_2_2_Main_Home extends AppCompatActivity {

    TextView tvwelcome;
    Button btnkeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum22_main_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvwelcome = findViewById(R.id.tvWelcome_pra_2_2);
        btnkeluar = findViewById(R.id.btnLogout_pra_2_2);
        String username = getIntent().getStringExtra("username").toString();
        String email = getIntent().getStringExtra("email").toString();
        tvwelcome.setText("Selamat Datang "+username+"("+email+")");
        btnkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Praktikum_2_2_Main_Home.this, Praktikum_2_2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}