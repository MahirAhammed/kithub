package com.example.kithub.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ProductNotFoundException extends CustomException{

    public ProductNotFoundException(){
        super(HttpStatus.NOT_FOUND, new CustomResponse(LocalDateTime.now(), ErrorMessage.PRODUCT_NOT_FOUND.getMessage()));
    }
}
