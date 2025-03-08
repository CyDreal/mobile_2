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

import java.util.List;

public class Latihan_2_1_Adapter extends RecyclerView.Adapter<Latihan_2_1_Adapter.ViewHolder> {
    private Context context;
    private List<Latihan_2_1_DataProducts> results;

    public Latihan_2_1_Adapter(Context context, List<Latihan_2_1_DataProducts> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public Latihan_2_1_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_latihan21_list_product,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Latihan_2_1_Adapter.ViewHolder holder, int position) {
        Latihan_2_1_DataProducts result = results.get(position);
        holder.aMerk.setText(result.getMerk());
        holder.aHargajual.setText(result.getHargajual());
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
