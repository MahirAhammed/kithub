package com.example.kithub.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class UserExistsException extends CustomException{

    public UserExistsException(){
        super(HttpStatus.BAD_REQUEST, new CustomResponse(LocalDateTime.now(), ErrorMessage.USERNAME_EXISTS.getMessage()));
    }
}
