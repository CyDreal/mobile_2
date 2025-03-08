package com.example.main_mobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Praktikum_2_2 extends AppCompatActivity {
    public static final String URL = new Praktikum_2_2_ServerAPI().BASE_URL;
    ProgressDialog pd;
    Button btnLogin;
    TextView etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum22);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etUsername = findViewById(R.id.etUserName_pra_2_2);
        etPassword = findViewById(R.id.etPassword_pra_2_2);

        btnLogin = findViewById(R.id.btnLogin_pra_2_2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(view.getContext());
                pd.setTitle("Progress Login...");
                pd.setMessage("Tunggu Sebentar...");
                pd.setCancelable(true);
                pd.setIndeterminate(true);

                prosesLogin(etUsername.getText().toString(),etPassword.getText().toString());
            }
        });
    }

    void prosesLogin(String vusername, String vpassword) {
        pd.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Praktikum_2_2_RegisterAPI api = retrofit.create(Praktikum_2_2_RegisterAPI.class);
        api.login(vusername, vpassword).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject json = new JSONObject(response.body().string());

                    if (json.getString("result").toString().equals("1")) {
                        if (json.getJSONObject("data").getString("status").equals("1")) {
                            Toast.makeText(Praktikum_2_2.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Praktikum_2_2.this, Praktikum_2_2_Main_Home.class);
                            intent.putExtra("username", json.getJSONObject("data").getString("username"));
                            intent.putExtra("email", json.getJSONObject("data").getString("email"));
                            startActivity(intent);
                            finish();
                            pd.dismiss();
                        } else {
                            pd.dismiss();
                            AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_2_2.this);
                            msg.setMessage("Status User Ini Tidak Aktif")
                                    .setNegativeButton("Retry", null)
                                    .create().show();
                        }
                    } else {
                        pd.dismiss();
                        AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_2_2.this);
                        msg.setMessage("Username Atau Password Salah")
                                .setNegativeButton("Retry", null)
                                .create().show();
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Info Load", "Load Gagal"+t.toString());
            }
        });
    }
}