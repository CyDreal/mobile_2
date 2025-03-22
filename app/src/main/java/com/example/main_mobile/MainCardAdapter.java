package com.example.main_mobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MainCardAdapter extends RecyclerView.Adapter<MainCardAdapter.CardViewHolder>{
    private List<MainCardItem> mainCardItems;
    private Context context;

    public MainCardAdapter(Context context, List<MainCardItem> mainCardItems) {
        this.context = context;
        this.mainCardItems = mainCardItems;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        MainCardItem item = mainCardItems.get(position);
        holder.titleText.setText(item.getTitle());
        holder.descriptionText.setText(item.getDescription());

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, item.getTargetActivity());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mainCardItems.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView titleText;
        TextView descriptionText;

        CardViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            titleText = itemView.findViewById(R.id.cardTitle);
            descriptionText = itemView.findViewById(R.id.cardDescription);
        }
    }
}