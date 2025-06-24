package learning_testing.spring_boot_Integration_testing.repository;

import learning_testing.spring_boot_Integration_testing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestH2ProductRepository extends JpaRepository<Product,Long> {
}
