package learning_test.spring_boot_unit_testing.controller;

import learning_test.spring_boot_unit_testing.entity.User;
import learning_test.spring_boot_unit_testing.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(
            UserService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
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
