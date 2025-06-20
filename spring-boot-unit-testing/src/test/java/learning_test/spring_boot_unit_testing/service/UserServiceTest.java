package learning_test.spring_boot_unit_testing.service;

import learning_test.spring_boot_unit_testing.entity.User;
import learning_test.spring_boot_unit_testing.repository.UserRepository;
import learning_test.spring_boot_unit_testing.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void UserService_CreateUser_ReturnUserId(){
        User user=User.builder().id(1L).name("Zoro").build();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        Long id=userService.createUserService(user);

        Assertions.assertEquals(user.getId(),id);
    }

    @Test
    public void UserService_GetAllUser_ReturnManyUser(){
        User user1=User.builder().id(1L).name("Luffy").build();
        User user2=User.builder().id(2L).name("Zoro").build();
        User user3=User.builder().id(3L).name("Sanji").build();

        List<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(userRepository.findAll()).thenReturn(users);

        List<User> returnedUser=userService.getAllUser();

        Assertions.assertEquals(users.size(),returnedUser.size());
    }
}
