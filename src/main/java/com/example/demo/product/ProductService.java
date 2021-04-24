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

    public int addNewProduct(ProductInputDto product) {
        return productRepository.add(product);
    }

    public Collection<ProductInputDto> getAll() {
        return productRepository.getAll();
    }

    public ProductInputDto getProduct(int id) {
        return productRepository.getProductById(id);
    }

    public Collection<ProductInputDto> getProductsOfPage(int pageId, Filters filters, String sortType) {
        return productRepository.getProductsOfPage(pageId, filters, sortType);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProductById(id);
    }
}
