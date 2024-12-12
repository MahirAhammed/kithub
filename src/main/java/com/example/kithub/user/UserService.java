package com.example.kithub.user;

import com.example.kithub.exceptions.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public ResponseEntity<String> createUser(CustomUser newUser, String role){

        Optional<CustomUser> user =  repository.findByUsername(newUser.getUsername());

        if (user.isPresent()){
            throw new UserExistsException();
        }

        CustomUser saveUser = new CustomUser();
        saveUser.setUsername(newUser.getUsername());
        saveUser.setPassword(encoder.encode(newUser.getPassword()));
        saveUser.setRole(role.equals("BASIC") ? role : "ADMIN");
        repository.save(saveUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    public ResponseEntity<List<CustomUser>> getAllUsers(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    public ResponseEntity<CustomUser> getUserById(long id){
        Optional<CustomUser> user = repository.findById(id);

        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }

        throw new UsernameNotFoundException("User with that ID does not exist");
    }

    public ResponseEntity<CustomUser> updateUser(CustomUser user, long id){
        Optional<CustomUser> existingUser = repository.findById(id);

        if (existingUser.isPresent()){
            user.setId(existingUser.get().getId());
            user.setRole(existingUser.get().getRole());
            return ResponseEntity.ok(user);
        }

        throw new UsernameNotFoundException("User does not exist");
    }

    public ResponseEntity deleteUser(long id){
        Optional<CustomUser> user = repository.findById(id);

        if (user.isPresent()){
            repository.delete(user.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new UsernameNotFoundException("User does not exist");
    }

}

