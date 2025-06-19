package learning_test.spring_boot_unit_testing.repository;

import learning_test.spring_boot_unit_testing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
