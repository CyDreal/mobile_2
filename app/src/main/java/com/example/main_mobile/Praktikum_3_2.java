package com.example.main_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Praktikum_3_2 extends AppCompatActivity {

    TextView tvWelcome;
    ImageButton imgBtnLogout, imgBtnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum32);
        getSupportActionBar().hide();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvWelcome = findViewById(R.id.tvWelcome_pra_3_2);
        tvWelcome.setText("Welcome = "+getIntent().getStringExtra("nama")+"("+getIntent().getStringExtra("email")+")");
        imgBtnLogout = findViewById(R.id.imgBtnLogout_pra_3_2);
        imgBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Praktikum_3_2.this, Praktikum_3_1.class);
                startActivity(intent);
                finish();
            }
        });
        imgBtnEditProfile = findViewById(R.id.imgBtnEditProfile_pra_3_2);
        imgBtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Praktikum_3_2.this, Praktikum_3_2_EditProfile.class);
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                startActivity(intent);
            }
        });
    }
}