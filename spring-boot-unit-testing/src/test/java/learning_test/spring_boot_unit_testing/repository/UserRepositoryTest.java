package learning_test.spring_boot_unit_testing.repository;

import learning_test.spring_boot_unit_testing.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest // for spring to pick this up as a file the do JPA testing
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // creates in memory database for testing
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    public UserRepositoryTest(
//        UserRepository _repository
//    ){
//        this.userRepository=_repository;
//    }

    @Test
    public void UserRepository_SaveAll_ReturnSavedUser(){

        // Arrange
        User user=new User();
        user.setName("Sanjay");

        // Act
        User savedUser=userRepository.save(user);

        // Assert
        Assertions.assertNotNull(savedUser);
    }

    @Test
    public  void UserRepository_GetAll_ReturnMoreThanOneUser(){
        User user1=new User();
        User user2=new User();
        user1.setName("Zoro");
        user2.setName("Luffy");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users=userRepository.findAll();

        Assertions.assertNotNull(users);
        Assertions.assertEquals(users.size(),2);
    }

    @Test
    public  void UserRepository_GetById_ReturnOneUser(){
        User user1=new User();
        User user2=new User();
        user1.setName("Zoro");
        user2.setName("Luffy");

        User savedUser1=userRepository.save(user1);
        User savedUser2=userRepository.save(user2);

        Optional<User> foundUser=userRepository.findById(savedUser1.getId());

        if(foundUser.isPresent()){
            User user=foundUser.get();
            Assertions.assertEquals(user.getName(),user1.getName());
        }
    }
}
