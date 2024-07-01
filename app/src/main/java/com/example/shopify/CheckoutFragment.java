package com.example.shopify;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class CheckoutFragment extends Fragment {

    private RecyclerView checkoutRecyclerView;
    private ProductAdapter checkoutAdapter;
    private SharedViewModel sharedViewModel;
    private Button buttonOrder;
    private TextView emptyCheckoutMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        checkoutRecyclerView = view.findViewById(R.id.checkoutRecyclerView);
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        emptyCheckoutMessage = view.findViewById(R.id.emptyCheckoutMessage);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getSelectedProducts().observe(getViewLifecycleOwner(), products -> {
            if (products.isEmpty()) {
                emptyCheckoutMessage.setVisibility(View.VISIBLE);
            } else {
                emptyCheckoutMessage.setVisibility(View.GONE);
            }
            checkoutAdapter = new ProductAdapter(products, (product, add) -> {
                if (!add) {
                    sharedViewModel.removeProduct(product);
                }
            });
            checkoutRecyclerView.setAdapter(checkoutAdapter);
        });

        buttonOrder = view.findViewById(R.id.buttonOrder);
        buttonOrder.setOnClickListener(v -> {
            // Clear selected products in ViewModel
            sharedViewModel.clearProducts();

            // Navigate to OrderSuccessfulFragment
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_checkout_to_order_successful);
        });

        return view;
    }
}
