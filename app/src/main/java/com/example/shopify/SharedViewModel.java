package com.example.shopify;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<List<Product>> selectedProducts = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Product>> getSelectedProducts() {
        return selectedProducts;
    }

    public void addProduct(Product product) {
        List<Product> products = new ArrayList<>(selectedProducts.getValue());
        if (!products.contains(product)) {
            products.add(product);
            selectedProducts.setValue(products);
        }
    }

    public void removeProduct(Product product) {
        List<Product> products = new ArrayList<>(selectedProducts.getValue());
        products.remove(product);
        selectedProducts.setValue(products);
    }

    public void clearProducts() {
        selectedProducts.setValue(new ArrayList<>());
    }
}
