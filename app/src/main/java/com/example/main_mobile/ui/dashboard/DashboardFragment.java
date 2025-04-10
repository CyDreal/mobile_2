package com.example.main_mobile.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.main_mobile.Latihan_4_3_CartItem;
import com.example.main_mobile.adapter.Latihan_4_3_CartAdapter;
import com.example.main_mobile.databinding.FragmentDashboardBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private Latihan_4_3_CartAdapter cartAdapter;
    private List<Latihan_4_3_CartItem> cartItems;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = requireContext().getSharedPreferences("checkout", Context.MODE_PRIVATE);
        cartItems = new ArrayList<>();

        setupRecyclerView();
        loadCartItems();

        return root;
    }

    private void setupRecyclerView() {
        cartAdapter = new Latihan_4_3_CartAdapter(requireContext(), cartItems, () -> loadCartItems());
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.cartRecyclerView.setAdapter(cartAdapter);
    }

    private void loadCartItems() {
        String cartItemsStr = sharedPreferences.getString("cart_items", "");
        cartItems.clear();

        if (!cartItemsStr.isEmpty()) {
            String[] items = cartItemsStr.split(";");
            for (String item : items) {
                if (!item.isEmpty()) {
                    String[] parts = item.split("\\|");
                    if (parts.length == 5) {
                        Latihan_4_3_CartItem cartItem = new Latihan_4_3_CartItem(
                                parts[0], // name
                                parts[1], // price
                                parts[2], // stock
                                parts[3], // image
                                Integer.parseInt(parts[4]) // quantity
                        );
                        cartItems.add(cartItem);
                    }
                }
            }
        }
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCartItems();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}