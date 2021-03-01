package com.example.demo.product;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public Product getProducts() {
        return new Tool("Name", new BigDecimal("800.08"), "Battery", "80%", ToolType.OTHER,true, true);
    }
}
