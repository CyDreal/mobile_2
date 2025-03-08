package com.example.main_mobile;

public class Latihan_1_1_Models {
    private String nim, nama, foto;

    public Latihan_1_1_Models(String nim, String nama, String foto) {
        this.nim = nim;
        this.nama = nama;
        this.foto = foto;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
