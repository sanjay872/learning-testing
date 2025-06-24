package learning_testing.spring_boot_Integration_testing.service;

import learning_testing.spring_boot_Integration_testing.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProduct();
    Product updateProduct(Product product);
    void deleteProduct(Long id);
}
