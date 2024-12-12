package com.example.kithub.security;

import com.example.kithub.user.CustomUser;
import com.example.kithub.user.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<CustomUser> user =  repository.findByUsername(username);

        if (user.isPresent()){
            return User
                    .withUsername(username)
                    .password(user.get().getPassword())
                    .roles(user.get().getRole())
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
