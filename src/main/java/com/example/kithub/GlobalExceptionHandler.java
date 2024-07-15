package com.example.kithub;

import com.example.kithub.exceptions.CustomException;
import com.example.kithub.exceptions.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponse> handlerNotFoundError(CustomException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getResponse());
    }
}
