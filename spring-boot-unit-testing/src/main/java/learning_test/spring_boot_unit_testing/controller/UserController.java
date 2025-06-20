package learning_test.spring_boot_unit_testing.controller;

import learning_test.spring_boot_unit_testing.entity.User;
import learning_test.spring_boot_unit_testing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    public UserController(
            UserService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        logger.info("i am in");
        return new ResponseEntity<>(service.createUserService(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(service.getUserById(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(service.getAllUser(),HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(service.getUserByName(name),HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return new ResponseEntity<>(service.updateUser(user),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
