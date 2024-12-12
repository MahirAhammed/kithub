package com.example.kithub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody CustomUser user){
        return userService.createUser(user, "BASIC");
    }

    @PostMapping("/create_admin")
    public ResponseEntity<String> createAdmin(@RequestBody CustomUser user){
        return userService.createUser(user, "ADMIN");
    }
    
    @GetMapping
    public ResponseEntity<List<CustomUser>> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomUser> getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomUser> updateUser(@RequestBody CustomUser user, @PathVariable long id){
        return userService.updateUser(user,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

}
