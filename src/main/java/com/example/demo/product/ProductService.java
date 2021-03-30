package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addNewProduct(ProductInputDto product) {
        productRepository.add(product);
    }

    public Collection<Product> getAll() {
        return productRepository.getAll();
    }

    public ProductInputDto getTool(int id) {
        return productRepository.getProductById(id);
    }
}
