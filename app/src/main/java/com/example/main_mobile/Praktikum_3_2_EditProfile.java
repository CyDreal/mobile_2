package com.example.main_mobile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

public class Praktikum_3_2_EditProfile extends AppCompatActivity {

    TextView tvWelcome, tvBack;
    com.google.android.material.textfield.TextInputEditText etNama, etAlamat, etKota, etProvinsi, etTelp, etKodePos;
    String email;
    com.google.android.material.button.MaterialButton imgBtnSubmit;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum32_edit_profile);
        getSupportActionBar().hide();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etNama = findViewById(R.id.etProfileNama_pra_3_2Ep);
        etAlamat = findViewById(R.id.etProfileAlamat_pra_3_2Ep);
        etKota = findViewById(R.id.etProfileKota_pra_3_2Ep);
        etProvinsi = findViewById(R.id.etProfileProvince_pra_3_2Ep);
        etTelp = findViewById(R.id.etProfileTelp_pra_3_2Ep);
        etKodePos = findViewById(R.id.etProfileKodePos_pra_3_2Ep);

        imgBtnSubmit = findViewById(R.id.imgBtnProfileSubmit_pra_3_2Ep);

        tvWelcome = findViewById(R.id.tvWelcome_pra_3_2Ep);
        tvBack = findViewById(R.id.tvProfileBack_pra_3_2Ep);

        // Mengambil dan menampung email dari intent
        email = getIntent().getStringExtra("email");
        String nama = getIntent().getStringExtra("nama");
        tvWelcome.setText("Welcome = "+nama+" ( "+email+" ) ");

        // inisialisasi progress dialog
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);

        // mendapatkan data profile menggunakan email
        if (email != null && !email.isEmpty()){
            getProfile(email);
        }

        tvBack.setOnClickListener(view -> finish());

//        tvBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });


        imgBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Praktikum_3_2_DataPelanggan data = new Praktikum_3_2_DataPelanggan();
                data.setEmail(email); // set the email
                data.setNama(etNama.getText().toString());
                data.setAlamat(etAlamat.getText().toString());
                data.setKota(etKota.getText().toString());
                data.setProvinsi(etProvinsi.getText().toString());
                data.setTelp(etTelp.getText().toString());
                data.setKodepos(etKodePos.getText().toString());

                updateProfile(data);
            }
        });
    }

    void getProfile(String vemail) {
        pd.show();

        Praktikum_3_2_ServerAPI urlAPI = new Praktikum_3_2_ServerAPI();
        String URL = urlAPI.BASE_URL;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Praktikum_3_2_RegisterAPI api = retrofit.create(Praktikum_3_2_RegisterAPI.class);
        api.getProfile(vemail).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                pd.dismiss();
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        JSONObject json = new JSONObject(response.body().string());
                        if (json.getString("result").equals("1")) {
                            // mendapatkan object data terlebih dahulu
                            JSONObject data = json.getJSONObject("data");

                            // akses values dari object data
                            etNama.setText(data.getString("nama").toString());
                            etAlamat.setText(data.getString("alamat").toString());
                            etKota.setText(data.getString("kota").toString());
                            etProvinsi.setText(data.getString("provinsi").toString());
                            etTelp.setText(data.getString("telp").toString());
                            etKodePos.setText(data.getString("kodepos").toString());

                            Log.i("Info profile", "Data loaded successfully");
                        } else {
                            showError("Gagal untuk mendapatkan data profile");
                        }
                    }
                }  catch ( JSONException | IOException e) {
                    e.printStackTrace();
                    showError("Gagal untuk meneruskan permintaan" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pd.dismiss();
                showError("Network error: "+t.getMessage());
            }
        });
    }

    private void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    void updateProfile(Praktikum_3_2_DataPelanggan data) {
        Praktikum_3_2_ServerAPI urlAPI = new Praktikum_3_2_ServerAPI();
        String URL = urlAPI.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Praktikum_3_2_RegisterAPI api = retrofit.create(Praktikum_3_2_RegisterAPI.class);
            Call<ResponseBody> call = api.updateProfile(data.getNama(),
                    data.getAlamat(),
                    data.getKota(),
                    data.getProvinsi(),
                    data.getTelp(),
                    data.getKodepos(),
                    data.getEmail());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        Toast.makeText(Praktikum_3_2_EditProfile.this, json.getString("message").toString(),
                                Toast.LENGTH_SHORT).show();
                        getProfile(data.getEmail());
                    } catch (JSONException|IOException e) {

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    AlertDialog.Builder msg = new AlertDialog.Builder(Praktikum_3_2_EditProfile.this);
                    msg.setMessage("Simpan Gagal, Error = "+t.toString())
                            .setNegativeButton("retry", null)
                            .create().show();
                }
            });
    }

}