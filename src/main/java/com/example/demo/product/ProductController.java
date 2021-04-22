package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
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
    @GetMapping("/page")
    public Collection<ProductInputDto> getProductsOfPage(@RequestParam int id,
                                                         @RequestParam(required = false)String title,
                                                         @RequestParam(required = false) String[] types,
                                                         @RequestParam(required = false) BigDecimal startPriceRange,
                                                         @RequestParam(required = false) BigDecimal endPriceRange) {
        System.out.println(Arrays.toString(types));
        return productService.getProductsOfPage(id, new Filters(title, types, startPriceRange, endPriceRange));
    }

    @CrossOrigin
    @PostMapping
    public ProductInputDto addNewProduct(@RequestBody ProductInputDto productInputDto) {
        int productId = productService.addNewProduct(productInputDto);
        return productService.getProduct(productId);
    }

    @CrossOrigin
    @DeleteMapping
    public void deleteProduct(@RequestParam Integer id) {
        productService.deleteProduct(id);
    }
}
