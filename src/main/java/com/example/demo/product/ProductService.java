package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public int getNumberOfProducts() {
        return productRepository.getNumberOfProducts();
    }

    public void addNewProduct(ProductInputDto product) {
        productRepository.add(product);
    }

//    public Collection<Product> getAllProducts() {
//        return productRepository.getAllProducts();
//    }
//
//    public void addNewProduct(Product product) {
//        productRepository.addNewProduct(product);
//    }
//
//    public Collection<Product> test() {
//        Product product1 = new Tool(null,
//                "Some kind of screwdriver",
//                new BigDecimal("800.08"),
//                "Battery",
//                "80%",
//                ToolType.OTHER,
//                false,
//                false);
//
//        Product product2 = new Tool(null,
//                "Dunno",
//                new BigDecimal("55550.08"),
//                "Make energy sources enums, maybe??",
//                "100%",
//                ToolType.HAND_TOOL,
//                false,
//                true);
//
//        Product product3 = new HydraulicWorktable(null,
//                "Hydraulic cutting board",
//                new BigDecimal("45360.08"),
//                "Socket?",
//                "Why is it even string?",
//                WorktableType.GRINDING_MACHINE,
//                false,
//                10,
//                6);
//
//        Product product4 = new LaserWorktable(null,
//                "Laser cutting board",
//                new BigDecimal("460.08"),
//                "Socket?",
//                "Why is it even string?",
//                WorktableType.GRINDING_MACHINE,
//                false,
//                10,
//                5,
//                20,
//                5);
//
//        Worktable product5 = new PlasmicWorktable(null,
//                "Plasmic cutting board",
//                new BigDecimal("4512310.08"),
//                "Socket?",
//                "Why is it even string?",
//                WorktableType.GRINDING_MACHINE,
//                false,
//                10,
//                6,
//                3);
//
//        productRepository.addNewProduct(product1);
//        productRepository.addNewProduct(product2);
//        productRepository.addNewProduct(product3);
//        productRepository.addNewProduct(product4);
//        productRepository.addNewProduct(product5);
//
//        System.out.println(product5.countEfficiency());
//
//        return productRepository.getAllProducts();
//    }
}
