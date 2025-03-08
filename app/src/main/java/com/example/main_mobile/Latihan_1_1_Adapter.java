package com.example.main_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Latihan_1_1_Adapter extends RecyclerView.Adapter<Latihan_1_1_Adapter.ViewHolder> {
    ArrayList<Latihan_1_1_Models> modelsArrayList = new ArrayList<>();

    public Latihan_1_1_Adapter(ArrayList<Latihan_1_1_Models> modelsArrayList) {
        this.modelsArrayList = modelsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewMhs = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_latihan11_list,parent,false);
        return new ViewHolder(viewMhs);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tnim.setText(modelsArrayList.get(position).getNim());
        holder.tnama.setText(modelsArrayList.get(position).getNama());
        Glide.with(holder.ifoto)
                .load(modelsArrayList.get(position).getFoto()).into(holder.ifoto);
    }

    @Override
    public int getItemCount() {
        return modelsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tnim, tnama;
        ImageView ifoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tnim = itemView.findViewById(R.id.tvNim_lat_1_1_list);
            tnama = itemView.findViewById(R.id.tvNama_lat_1_1_list);
            ifoto = itemView.findViewById(R.id.imageView_lat_1_1_list);
        }
    }
}
