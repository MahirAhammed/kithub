package com.example.kithub.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidFieldException extends CustomException{

    public InvalidFieldException(String message){
        super(HttpStatus.BAD_REQUEST, new CustomResponse(LocalDateTime.now(), message));
    }
}
