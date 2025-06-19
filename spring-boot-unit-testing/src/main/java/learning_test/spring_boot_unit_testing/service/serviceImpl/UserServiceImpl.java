package learning_test.spring_boot_unit_testing.service.serviceImpl;

import learning_test.spring_boot_unit_testing.entity.User;
import learning_test.spring_boot_unit_testing.repository.UserRepository;
import learning_test.spring_boot_unit_testing.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(
            UserRepository userRepository
    ){
        this.repository=userRepository;
    }

    @Override
    public Long createUserService(User user) {
        return repository.save(user).getId();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional=repository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new RuntimeException("User not found!");
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
