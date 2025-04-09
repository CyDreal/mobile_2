package com.example.main_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main_mobile.Praktikum_4_2_Product;
import com.example.main_mobile.R;

import java.util.ArrayList;

public class Praktikum_4_2_ProductAdapter extends RecyclerView.Adapter<Praktikum_4_2_ProductAdapter.ViewHolder> {
    private final ArrayList<Praktikum_4_2_Product> listProduct;

    public Praktikum_4_2_ProductAdapter(ArrayList<Praktikum_4_2_Product> listProduct) {
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_praktikum42_list_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Praktikum_4_2_Product product = listProduct.get(position);
        holder.tvProduct.setText(product.getProduct());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProduct;
        private final TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProduct = itemView.findViewById(R.id.tvProduct_pra_4_2_listP);
            tvPrice = itemView.findViewById(R.id.tvPrice_pra_4_2_listP);
        }
    }
}