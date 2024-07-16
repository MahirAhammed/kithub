package com.example.kithub.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidUUIDException extends CustomException{
    public InvalidUUIDException(){
        super(HttpStatus.BAD_REQUEST, new CustomResponse(LocalDateTime.now(), ErrorMessage.INVALID_UUID.getMessage()));
    }
}
