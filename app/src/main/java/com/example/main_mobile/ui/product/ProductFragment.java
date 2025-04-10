package com.example.main_mobile.ui.product;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main_mobile.Latihan_2_1_Adapter;
import com.example.main_mobile.Latihan_2_1_DataProducts;
import com.example.main_mobile.adapter.Latihan_4_3_Adapter;
import com.example.main_mobile.databinding.FragmentProductBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;
    private ProductViewModel productViewModel;
    private Latihan_4_3_Adapter adapter;
    private List<Latihan_2_1_DataProducts> productList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // hapus duplikasi atau redudansi dari instalasi ViewModel
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        setupRecyclerView();
        observeProducts();
        productViewModel.loadProducts(); // memuat data dari API

        return root;
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.rVProductFragPra33;
        adapter = new Latihan_4_3_Adapter(requireContext(), productList);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = 10;
                outRect.right = 10;
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void observeProducts() {
        // Fix the parameter naming conflict
        productViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            if(products != null) {
                productList.clear();
                productList.addAll(products); // Use products instead of productList
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}