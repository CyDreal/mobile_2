package com.example.main_mobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.main_mobile.Latihan_2_1_DataProducts;
import com.example.main_mobile.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Latihan_4_3_Adapter extends RecyclerView.Adapter<Latihan_4_3_Adapter.ViewHolder> {
    private Context context;
    private List<Latihan_2_1_DataProducts> results;
    private NumberFormat numberFormat;
    private SharedPreferences sharedPreferences;

    public Latihan_4_3_Adapter(Context context, List<Latihan_2_1_DataProducts> results) {
        this.context = context;
        this.results = results;
        this.numberFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        this.sharedPreferences = context.getSharedPreferences("checkout", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_latihan21_list_product,parent,false);
        return new Latihan_4_3_Adapter.ViewHolder(v);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull Latihan_4_3_Adapter.ViewHolder holder, int position) {
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

        holder.btnBuy.setOnClickListener(v -> {
            try {
                String cartItems = sharedPreferences.getString("cart_items", "");
                boolean itemExists = false;
                StringBuilder updatedCart = new StringBuilder();

                if (!cartItems.isEmpty()) {
                    String[] items = cartItems.split(";");
                    for (String item : items) {
                        if (!item.isEmpty()) {
                            String[] parts = item.split("\\|");
                            if (parts[0].equals(result.getMerk())) {
                                int qty = Integer.parseInt(parts[4]) + 1;
                                updatedCart.append(String.format("%s|%s|%s|%s|%d;",
                                        parts[0], parts[1], parts[2], parts[3], qty));
                                itemExists = true;
                            } else {
                                updatedCart.append(item).append(";");
                            }
                        }
                    }
                }

                if (!itemExists) {
                    updatedCart.append(String.format("%s|%s|%s|%s|1;",
                            result.getMerk(),
                            result.getHargajual(),
                            result.getStok(),
                            result.getFoto()
                    ));
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("cart_items", updatedCart.toString());
                editor.apply();

                // Menggunakan Snackbar dengan view klik listener (v)
                String message = itemExists ?
                        "Increased quantity for " + result.getMerk() :
                        "Successfully added " + result.getMerk() + " to cart";
                Snackbar.make(v, message, Snackbar.LENGTH_SHORT).show();

            } catch (Exception e) {
                // Snackbar untuk error handling
                Snackbar.make(v,
                        "Failed to add " + result.getMerk() + " to cart",
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aMerk, aHargajual, aStok;
        ImageView aFoto;
        ImageButton btnBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.aMerk = itemView.findViewById(R.id.tvMerk_lat_2_1);
            this.aHargajual = itemView.findViewById(R.id.tvHarga_lat_2_1);
            this.aStok = itemView.findViewById(R.id.tvStok_lat_2_1);
            this.aFoto = itemView.findViewById(R.id.imgView_lat_2_1);
            this.btnBuy = itemView.findViewById(R.id.btnBuy_lat_2_1);
        }
    }
}
