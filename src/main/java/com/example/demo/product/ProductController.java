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
    public Collection<ProductInputDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/id")
    public ProductInputDto getProductById(@RequestParam Integer id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public void addNewProduct(@RequestBody ProductInputDto productInputDto) {
        productService.addNewProduct(productInputDto);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam Integer id) {
        productService.deleteProduct(id);
    }
}
