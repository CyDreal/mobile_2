package com.example.main_mobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Latihan_4_1 extends AppCompatActivity {

    public static final String URL = new Praktikum_3_1_ServerAPI().BASE_URL_LOGIN;
    ProgressDialog pd;
    MaterialButton btnLogin;
    TextInputEditText etEmail, etPassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_latihan41);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("Login_4_1", MODE_PRIVATE);

        // Check if user is already logged in
//        if (sharedPreferences.contains("email")) {
//            startActivity(new Intent(Latihan_4_1.this, Latihan_4_1_MainHome.class));
//            finish();
//            return;
//        }

//        sharedPreferences.contains("nama");
//        sharedPreferences.contains("jenisKel");
//        sharedPreferences.contains("usia");

        // Initialize views
        etEmail = findViewById(R.id.emailInput_lat_4_1);
        etPassword = findViewById(R.id.passwordInput_lat_4_1);
        btnLogin = findViewById(R.id.loginButton_lat_4_1);

        btnLogin.setOnClickListener(view -> {
            if (validateInput()) {
                pd = new ProgressDialog(view.getContext());
                pd.setTitle("Progress Login...");
                pd.setMessage("Tunggu Sebentar...");
                pd.setCancelable(true);
                pd.setIndeterminate(true);
                prosesLogin(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private boolean validateInput() {
        if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError("Email tidak boleh kosong");
            return false;
        }
        if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError("Password tidak boleh kosong");
            return false;
        }
        return true;
    }

    void prosesLogin(String email, String password) {
        pd.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Praktikum_3_1_RegisterAPI_Login api = retrofit.create(Praktikum_3_1_RegisterAPI_Login.class);
        api.login(email, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    // Check if response body is not null
                    if (response.body() == null) {
                        showError("Response kosong");
                        return;
                    }

                    String responseData = response.body().string();
                    // Log response for debugging
                    Log.d("API Response", responseData);

                    JSONObject json = new JSONObject(responseData);

                    // Remove toString() as it's redundant
                    if (json.getString("result").equals("1")) {
                        JSONObject data = json.getJSONObject("data");
                        if (data.getString("status").equals("1")) {
                            // Save user data to SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", data.getString("email"));
                            editor.putString("nama", data.getString("nama"));
                            editor.putString("alamat", data.getString("alamat"));
                            editor.apply();

                            Toast.makeText(Latihan_4_1.this, "Login Berhasil", Toast.LENGTH_SHORT).show();

                            // Start home activity
                            Intent intent = new Intent(Latihan_4_1.this, Latihan_4_1_MainHome.class);
                            startActivity(intent);
                            finish();
                        } else {
                            showError("Status User Ini Tidak Aktif");
                        }
                    } else {
                        showError("Email Atau Password Salah");
                    }
                } catch (JSONException | IOException e) {
                    // Log the actual error
                    Log.e("JSON Error", "Error: " + e.getMessage());
                    showError("Terjadi kesalahan: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pd.dismiss();
                showError("Koneksi gagal");
                Log.e("Login Error", t.getMessage());
            }
        });
    }

    private void showError(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setNegativeButton("Retry", null)
                .create().show();
    }


}