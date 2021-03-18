package com.example.demo.product;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private int currentProductId = 0;
    private ArrayList<Product> products = new ArrayList<>();

    public void addNewProduct(Product product) {
        product.setId(currentProductId);
        currentProductId++;
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
