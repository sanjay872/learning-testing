package learning_test.spring_boot_unit_testing.service;

import learning_test.spring_boot_unit_testing.entity.User;

import java.util.List;

public interface UserService {
    Long createUserService(User user);
    User getUserById(Long id);
    List<User> getAllUser();
    User updateUser(User user);
    void deleteUser(Long id);
}
