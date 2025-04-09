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


public class MainPraktikumFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_praktikum, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_praktikum);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<MainCardItem> cardItems = new ArrayList<>();
        cardItems.add(new MainCardItem(
                "Praktikum 1.1",
                "Menampilkan gambar melalui url menggunakan library Glide",
                Praktikum_1_1_Glide.class
        ));
        cardItems.add(new MainCardItem(
                "Praktikum 2.2",
                "Aplikasi login form yang terintegrasi API static",
                Praktikum_2_2.class
        ));
        cardItems.add(new MainCardItem(
                "Praktikum 3.1",
                "Aplikasi login dan register form yang terintegrasi API static dengan record password yang ter enkripsi",
                Praktikum_3_1.class
        ));
        cardItems.add(new MainCardItem(
                "Praktikum 3.2",
                "Aplikasi pilihan menu dan edit profile dengan integrasi API static",
                Praktikum_3_2.class
        ));
        cardItems.add(new MainCardItem(
                "Praktikum 3.3",
                "Aplikasi bottom navigation",
                Praktikum_3_3_BottomNavigation.class
        ));
        cardItems.add(new MainCardItem(
                "Praktikum 4.1",
                "Aplikasi bottom navigation",
                Praktikum_4_1.class
        ));
        cardItems.add(new MainCardItem(
                "Praktikum 4.2",
                "Aplikasi...",
                Praktikum_4_2.class
        ));
        // Add more cards as needed

        adapter = new MainCardAdapter(getContext(), cardItems);
        recyclerView.setAdapter(adapter);

        return view;
    }
}