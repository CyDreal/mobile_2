package com.example.main_mobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Praktikum_3_1 extends AppCompatActivity {

    public static final String URL = new Praktikum_3_1_ServerAPI().BASE_URL_LOGIN;
    ProgressDialog pd;
    com.google.android.material.button.MaterialButton btnLogin;
    com.google.android.material.textfield.TextInputEditText etEmail, etPassword;
    TextView etRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum31);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail = findViewById(R.id.etEmail_pra_3_1);
        etPassword = findViewById(R.id.etPassword2_pra_3_1);
        etRegister = findViewById(R.id.tvRegister_pra_3_1);

        btnLogin = findViewById(R.id.imgbtnSubmit_pra_3_1);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(view.getContext());
                pd.setTitle("Progress Login...");
                pd.setMessage("Tunggu Sebentar...");
                pd.setCancelable(true);
                pd.setIndeterminate(true);

                prosesLogin(etEmail.getText().toString(),etPassword.getText().toString());
            }
        });

        etRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Praktikum_3_1.this, Praktikum_3_1_MainRegister.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void prosesLogin(String vemail, String vpassword) {
        pd.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Praktikum_3_1_RegisterAPI_Login api = retrofit.create(Praktikum_3_1_RegisterAPI_Login.class);
        api.login(vemail, vpassword).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject json = new JSONObject(response.body().string());

                    if (json.getString("result").toString().equals("1")) {
                        if (json.getJSONObject("data").getString("status").equals("1")) {
                            Toast.makeText(Praktikum_3_1.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Praktikum_3_1.this, Praktikum_3_2.class);
                            intent.putExtra("email", json.getJSONObject("data").getString("email"));
                            intent.putExtra("nama", json.getJSONObject("data").getString("nama"));
                            startActivity(intent);
                            finish();
                            pd.dismiss();
                        } else {
                            pd.dismiss();
                            AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_3_1.this);
                            msg.setMessage("Status User Ini Tidak Aktif")
                                    .setNegativeButton("Retry", null)
                                    .create().show();
                        }
                    } else {
                        pd.dismiss();
                        AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_3_1.this);
                        msg.setMessage("email Atau Password Salah")
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