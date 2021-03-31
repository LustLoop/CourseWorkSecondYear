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

//    @RequestMapping
//    public Collection<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
    @PostMapping("/add")
    public void addNewProduct(@RequestBody ProductInputDto productInputDto) {
        productService.addNewProduct(productInputDto);
    }
//
//    @GetMapping("/test")
//    public Collection<Product> test() {
//        return productService.test();
//    }
}
