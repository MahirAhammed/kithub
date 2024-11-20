package com.example.kithub.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CategoryNotFoundException extends CustomException{

    public CategoryNotFoundException(){
        super(HttpStatus.NOT_FOUND, new CustomResponse(LocalDateTime.now(), ErrorMessage.CATEGORY_NOT_FOUND.getMessage()));
    }
}
