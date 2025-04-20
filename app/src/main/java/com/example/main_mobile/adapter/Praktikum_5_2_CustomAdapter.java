package com.example.main_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main_mobile.R;

import java.util.ArrayList;

public class Praktikum_5_2_CustomAdapter extends RecyclerView.Adapter<Praktikum_5_2_CustomAdapter.ViewHolder> {
    private ArrayList<String> localDataSet;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // membuat view baru yang mendefinisikan UI dari list item
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.activity_praktikum52_text_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // mengganti konten dari suatu View
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // mengambil elemen dari dataset pada posisi tertentu dan mennganti kontennya
        viewHolder.getTextView().setText(localDataSet.get(position));
    }

    // mengembalikan ukuran dataset
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView_pra_5_2);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public Praktikum_5_2_CustomAdapter(ArrayList<String> dataSet) {localDataSet = dataSet;}
}
