package com.example.demo.product;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    private int currentProductId = 0;
    private ArrayList<Product> products = new ArrayList<Product>();

    public void addNewProduct(Product product) {
        product.setId(currentProductId);
        currentProductId++;
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
