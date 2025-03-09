package com.example.main_mobile;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Latihan_2_1_Adapter extends RecyclerView.Adapter<Latihan_2_1_Adapter.ViewHolder> {
    private Context context;
    private List<Latihan_2_1_DataProducts> results;
    private NumberFormat numberFormat;

    public Latihan_2_1_Adapter(Context context, List<Latihan_2_1_DataProducts> results) {
        this.context = context;
        this.results = results;
        this.numberFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }

    @NonNull
    @Override
    public Latihan_2_1_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_latihan21_list_product,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Latihan_2_1_Adapter.ViewHolder holder, int position) {
        Latihan_2_1_DataProducts result = results.get(position);
        holder.aMerk.setText(result.getMerk());
        // Format harga ke dalam bentuk Rupiah
        try {
            double harga = Double.parseDouble(result.getHargajual());
            String formattedPrice = numberFormat.format(harga);
            // Hapus simbol mata uang karena sudah termasuk "Rp"
            formattedPrice = formattedPrice.replace("Rp", "Rp ");
            holder.aHargajual.setText(formattedPrice);
        } catch (NumberFormatException e) {
            holder.aHargajual.setText("Rp " + result.getHargajual());
        }
        holder.aStok.setText(result.getStok());
        Glide.with(context)
                .load(result.getFoto())
                .into(holder.aFoto);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aMerk, aHargajual, aStok;
        ImageView aFoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.aMerk = itemView.findViewById(R.id.tvMerk_lat_2_1);
            this.aHargajual = itemView.findViewById(R.id.tvHarga_lat_2_1);
            this.aStok = itemView.findViewById(R.id.tvStok_lat_2_1);
            this.aFoto = itemView.findViewById(R.id.imgView_lat_2_1);
        }
    }
}
