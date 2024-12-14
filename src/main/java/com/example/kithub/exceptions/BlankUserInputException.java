package com.example.kithub.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class BlankUserInputException extends CustomException{

    public BlankUserInputException(){
        super(HttpStatus.BAD_REQUEST, new CustomResponse(LocalDateTime.now(), ErrorMessage.BLANK_USER_INPUTS.getMessage()));
    }
}
