package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    private void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Collection<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/test")
    public ProductInputDto getProductById() {
        return productService.getProduct(2);
    }

    @PostMapping("/add")
    public void addNewProduct(@RequestBody ProductInputDto productInputDto) {
        productService.addNewProduct(productInputDto);
    }
}
