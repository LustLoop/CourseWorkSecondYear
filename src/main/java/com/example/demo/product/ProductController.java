package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getProducts() {
        Product product1 = new Tool(null,
                "Some kind of screwdriver",
                new BigDecimal("800.08"),
                "Battery",
                "80%",
                ToolType.OTHER,
                false,
                false);

        Product product2 = new Tool(null,
                "Dunno",
                new BigDecimal("55550.08"),
                "Make energy sources enums, maybe??",
                "100%",
                ToolType.HAND_TOOL,
                false,
                true);

        Product product3 = new Worktable(null,
                "Cutting board",
                new BigDecimal("45360.08"),
                "Socket?",
                "Why is it even string?",
                false,
                WorktableType.GRINDING_MACHINE,
                TypeOfWork.HYDRAULIC);

        productRepository.addNewProduct(product1);
        productRepository.addNewProduct(product2);
        productRepository.addNewProduct(product3);

        return productRepository.getAllProducts();
    }
}
