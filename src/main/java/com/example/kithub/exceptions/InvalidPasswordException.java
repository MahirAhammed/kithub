package com.example.kithub.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidPasswordException extends CustomException{

    public InvalidPasswordException(){
        super(HttpStatus.BAD_REQUEST, new CustomResponse(LocalDateTime.now(), ErrorMessage.INVALID_PASSWORD.getMessage()));
    }
}
