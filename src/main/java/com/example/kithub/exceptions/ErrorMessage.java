package com.example.kithub.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    PRODUCT_NOT_FOUND("Requested product does not exist"),
    INVALID_UUID("The UUID provided is not in the valid format"),
    PRODUCT_EXISTS("Product already exists"),
    PRODUCT_NAME_EMPTY("Product name cannot be empty"),
    PRODUCT_QUANTITY_INVALID("Numerical values cannot be negative or zero"),
    CATEGORY_NOT_FOUND("No such cateogory exists"),
    USERNAME_EXISTS("User with this username already exists"),
    BLANK_USER_INPUTS("Username or password cannot be blank or empty"),
    INVALID_PASSWORD("Password should be at least 6 characters long");

    private final String message;

    ErrorMessage(String s) {
        this.message = s;
    }

}
