package com.example.kithub.category;

import lombok.*;
@Data
public class CategoryDTO {

    private String value;


    public void setValue(String value) {
        this.value = value;
    }
}
