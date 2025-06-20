package learning_test.spring_boot_unit_testing.config;

import learning_test.spring_boot_unit_testing.service.UserService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UserServiceMockConfig {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
}
