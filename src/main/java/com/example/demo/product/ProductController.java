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

    @CrossOrigin
    @GetMapping
    public Collection<ProductInputDto> getAll() {
        return productService.getAll();
    }

    @CrossOrigin
    @GetMapping("/product")
    public ProductInputDto getProductById(@RequestParam int id) {
        return productService.getProduct(id);
    }

    @CrossOrigin
    @PostMapping
    public void addNewProduct(@RequestBody ProductInputDto productInputDto) {
        productService.addNewProduct(productInputDto);
    }

    @CrossOrigin
    @DeleteMapping
    public void deleteProduct(@RequestParam Integer id) {
        productService.deleteProduct(id);
    }
}
