package com.example.kithub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)       // allows POST, PUT, DELETE
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("api/v1/products").permitAll();
                    authorize.requestMatchers("api/v1/categories").permitAll();
                    authorize.requestMatchers("api/v1/users/create_user").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"api/v1/users/create_admin").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT,"api/v1/users/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"api/v1/users/**").hasRole("ADMIN");


                    authorize.anyRequest().authenticated();
        })
                .addFilterBefore(
                        new BasicAuthenticationFilter(authenticationManager(httpSecurity)),
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

}
