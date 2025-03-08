package com.example.main_mobile;

import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Praktikum_1_1_Glide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum11_glide);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imagevie = findViewById(R.id.glide_img_view);
        Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/" +
                        "Logo_udinus1.jpg/893px-Logo_udinus1.jpg")
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(imagevie);
    }
}