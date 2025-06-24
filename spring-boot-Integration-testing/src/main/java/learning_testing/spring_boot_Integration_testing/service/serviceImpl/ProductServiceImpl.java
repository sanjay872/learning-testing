package learning_testing.spring_boot_Integration_testing.service.serviceImpl;

import learning_testing.spring_boot_Integration_testing.entity.Product;
import learning_testing.spring_boot_Integration_testing.repository.ProductRepository;
import learning_testing.spring_boot_Integration_testing.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;

    public ProductServiceImpl(
            ProductRepository repository
    ){
        this.repository=repository;
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product=repository.findById(id);
        if(product.isPresent()){
            logger.info("Got product!");
            return product.get();
        }
        logger.error("Product not found!");
        throw new RuntimeException("Product not found");
    }

    @Override
    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
