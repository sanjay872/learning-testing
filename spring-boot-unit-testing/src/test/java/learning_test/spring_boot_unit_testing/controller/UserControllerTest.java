package learning_test.spring_boot_unit_testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import learning_test.spring_boot_unit_testing.config.UserServiceMockConfig;
import learning_test.spring_boot_unit_testing.entity.User;
import learning_test.spring_boot_unit_testing.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(controllers = UserController.class) //tells Spring to instantiate only the UserController (and required web layer beans).
@AutoConfigureMockMvc(addFilters = false)// Used to enable and configure MockMvc (Spring’s mock HTTP client for controller testing), addFilter to set the security
@Import(UserServiceMockConfig.class)
//@ExtendWith(MockitoExtension.class) // For mocking
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach()
    public void init(){
        user=User.builder().id(1L).name("Zoro").build();
    }

    @Test
    public void UserController_CreateUser_ReturnUserID() throws Exception{
        given(userService.createUserService(ArgumentMatchers.any())).willReturn(user.getId());
        //when(userService.createUserService(ArgumentMatchers.any())).thenReturn(user.getId());
        ResultActions response=mockMvc.perform(
                post("/user") // request and path
                        .contentType(MediaType.APPLICATION_JSON) // data format
                        .content(objectMapper.writeValueAsBytes(user)) // converts object into string and pass it to the controller
        );
        response.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(user.getName()))) // when return object is json
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(user.getId())))
                .andExpect(content().string("1")); // when return is a value
                //.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void UserController_GetAll_ReturnsManyUser() throws Exception {
        List<User> userList=new ArrayList<>();
        userList.add(user);
        when(userService.getAllUser()).thenReturn(userList);

        ResultActions response=mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                //.param("args",1)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
