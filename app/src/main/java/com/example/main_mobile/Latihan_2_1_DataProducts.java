package com.example.main_mobile;

import com.google.gson.annotations.SerializedName;

public class Latihan_2_1_DataProducts {
    @SerializedName("merk")
    private String merk;

    @SerializedName("hargajual")
    private String hargajual;

    @SerializedName("stok")
    private String stok;

    @SerializedName("foto")
    private String foto;
    public String getMerk() {
        return merk;
    }

    public String getHargajual() {
        return hargajual;
    }

    public String getStok() {
        return stok;
    }

    public String getFoto() {
        return foto;
    }
}
