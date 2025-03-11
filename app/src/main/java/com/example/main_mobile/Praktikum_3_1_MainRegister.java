package com.example.main_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Praktikum_3_1_MainRegister extends AppCompatActivity {
TextView tvBack;
EditText etNama, etEmail, etPassword;
ImageButton imgbtnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum31_main_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvBack = findViewById(R.id.tvToLogin_pra_3_1);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Praktikum_3_1_MainRegister.this, Praktikum_3_1.class);
                startActivity(intent);
                finish();
            }
        });

        etEmail = findViewById(R.id.etEmail_pra_3_1);
        etNama = findViewById(R.id.etName_pra_3_1);
        etPassword = findViewById(R.id.etPassword_pra_3_1);

        imgbtnSubmit = findViewById(R.id.imgbtnSubmit_pra_3_1);
        imgbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesSubmit(etEmail.getText().toString(),
                        etNama.getText().toString(),
                        etPassword.getText().toString());
            }
        });
    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

    void prosesSubmit(String vemail, String vnama, String vpassword){
        Praktikum_3_1_ServerAPI urlapi = new Praktikum_3_1_ServerAPI();
        String URL = urlapi.BASE_URL;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Praktikum_3_1_RegisterAPI api = retrofit.create(Praktikum_3_1_RegisterAPI.class);
        // Cek apakah email sudah valid
        if(!isEmailValid(etEmail.getText().toString())){
            AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_3_1_MainRegister.this);

            msg.setMessage("Email Tidak Valid")
                    .setNegativeButton("Retry", null)
                    .create().show();
            return;
        }

        api.register(vemail, vnama, vpassword).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    // Cek apakah user sudah ada atau tidak
                    if(json.getString("status").toString().equals("1")) {
                        // Cek result apakah berhasil di simpan atau tidak
                        if(json.getString("result").toString().equals("1")) {
                            AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_3_1_MainRegister.this);
                            msg.setMessage("Register Berhasil")
                                    .setPositiveButton("OK",null)
                                    .create().show();
                            etEmail.setText("");
                            etNama.setText("");
                            etPassword.setText("");
                        } else {
                            AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_3_1_MainRegister.this);
                            msg.setMessage("Simpan Gagal")
                                    .setNegativeButton("retry",null)
                                    .create().show();
                        }
                    } else {
                        AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_3_1_MainRegister.this);
                        msg.setMessage("User Sudah Ada")
                                .setNegativeButton("retry",null)
                                .create().show();
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Info Register", "Register Gagal "+t.toString());
            }
        });
    }
}