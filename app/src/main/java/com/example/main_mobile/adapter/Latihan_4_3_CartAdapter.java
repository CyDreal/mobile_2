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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.main_mobile.Latihan_4_3_CartItem;
import com.example.main_mobile.Latihan_4_3_CartUpdateListener;
import com.example.main_mobile.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Latihan_4_3_CartAdapter extends RecyclerView.Adapter<Latihan_4_3_CartAdapter.ViewHolder> {
    private Context context;
    private List<Latihan_4_3_CartItem> cartItems;
    private NumberFormat numberFormat;
    private SharedPreferences sharedPreferences;
    private Latihan_4_3_CartUpdateListener updateListener;

    public Latihan_4_3_CartAdapter(Context context, List<Latihan_4_3_CartItem> cartItems, Latihan_4_3_CartUpdateListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.updateListener = listener;
        this.numberFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        this.sharedPreferences = context.getSharedPreferences("checkout", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_latihan43_item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Latihan_4_3_CartItem item = cartItems.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(formatPrice(item.getPrice()));
        holder.quantity.setText(String.valueOf(item.getQuantity()));
        holder.subtotal.setText(formatPrice(String.valueOf(
                Double.parseDouble(item.getPrice()) * item.getQuantity())));

        Glide.with(context)
                .load(item.getImageUrl())
                .into(holder.image);

        holder.btnDelete.setOnClickListener(v -> {
            removeItem(position);
            saveCartItems();
            updateListener.onCartUpdate();
        });

        holder.btnIncrease.setOnClickListener(v -> {
            item.incrementQuantity();
            saveCartItems();
            notifyItemChanged(position);
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.decrementQuantity();
                saveCartItems();
                notifyItemChanged(position);
            }
        });
    }

    private void removeItem(int position) {
        cartItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartItems.size());
    }

    @SuppressLint("DefaultLocale")
    private void saveCartItems() {
        StringBuilder cartItemsStr = new StringBuilder();
        for (Latihan_4_3_CartItem item : cartItems) {
            cartItemsStr.append(String.format("%s|%s|%s|%s|%d;",
                    item.getName(),
                    item.getPrice(),
                    item.getStock(),
                    item.getImageUrl(),
                    item.getQuantity()
            ));
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cart_items", cartItemsStr.toString());
        editor.apply();
    }

    private String formatPrice(String price) {
        try {
            double amount = Double.parseDouble(price);
            return numberFormat.format(amount).replace("Rp", "Rp ");
        } catch (NumberFormatException e) {
            return "Rp " + price;
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, quantity, subtotal;
        ImageButton btnIncrease, btnDecrease, btnDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cartItemImage);
            name = itemView.findViewById(R.id.cartItemName);
            price = itemView.findViewById(R.id.cartItemPrice);
            quantity = itemView.findViewById(R.id.tvQuantity);
            subtotal = itemView.findViewById(R.id.tvSubtotal);
            btnIncrease = itemView.findViewById(R.id.btnIncreaseQty);
            btnDecrease = itemView.findViewById(R.id.btnDecreaseQty);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}