package com.example.shopify;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product 1", 10.0));
        productList.add(new Product("Product 2", 20.0));
        productList.add(new Product("Product 3", 30.0));

        productAdapter = new ProductAdapter(productList, (product, add) -> {
            if (add) {
                sharedViewModel.addProduct(product);
            } else {
                sharedViewModel.removeProduct(product);
            }
        });
        productRecyclerView.setAdapter(productAdapter);

        return view;
    }
}
