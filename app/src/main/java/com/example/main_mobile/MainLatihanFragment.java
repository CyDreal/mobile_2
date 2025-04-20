package com.example.main_mobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainLatihanFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_latihan,container, false);

        recyclerView = view.findViewById(R.id.recyclerview_latihan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<MainCardItem> cardItems = new ArrayList<>();
        cardItems.add(new MainCardItem(
                "Latihan 1.1",
                "Aplikasi list mahasiswa dengan pengambilan record secara static",
                Latihan_1_1.class
        ));
        cardItems.add(new MainCardItem(
                "Latihan 2.1",
                "Aplikasi list product dengan pengambilan record menggunakan API static",
                Latihan_2_1.class
        ));
        cardItems.add(new MainCardItem(
                "Latihan 3.1",
                "Aplikasi list product dengan akses setelah melakukan login akun user",
                Latihan_3_1.class
        ));
        cardItems.add(new MainCardItem(
                "Latihan 3.2",
                "Lanjutan dari Praktikum 3.3 menampilkan list product pada bottom navigation",
                Praktikum_3_3_BottomNavigation.class
        ));
        cardItems.add(new MainCardItem(
                "Latihan 4.1",
                "Aplikasi halaman login dengan memanfaatkan Restfull API dan Shared Preference",
                Latihan_4_1.class
        ));
        cardItems.add(new MainCardItem(
                "Latihan 4.2",
                "Lanjutan dari Praktikum 3.3 menambahkan tombol delete pada daftar product",
                Praktikum_3_3_BottomNavigation.class
        ));
        cardItems.add(new MainCardItem(
                "Latihan 4.3",
                "Lanjutan dari Praktikum 3.3 yaitu aplikasi halaman product dan order product menggunakan restfull API dan Shared Preference",
                Praktikum_3_3_BottomNavigation.class
        ));
        // Add more cards as needed

        adapter = new MainCardAdapter(getContext(), cardItems);
        recyclerView.setAdapter(adapter);

        return view;
    }
}