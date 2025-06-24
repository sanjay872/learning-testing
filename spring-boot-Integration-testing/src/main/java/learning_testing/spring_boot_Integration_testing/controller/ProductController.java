package learning_testing.spring_boot_Integration_testing.controller;

import learning_testing.spring_boot_Integration_testing.entity.Product;
import learning_testing.spring_boot_Integration_testing.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(
            ProductService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(this.service.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(this.service.getAllProduct(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getProductById(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return new ResponseEntity<>(this.service.updateProduct(product),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        this.service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
