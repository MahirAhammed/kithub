package com.example.kithub.product;

import com.example.kithub.category.Category;
import com.example.kithub.exceptions.ErrorMessage;
import com.example.kithub.exceptions.InvalidFieldException;

import java.util.EnumSet;
import java.util.List;

public class ProductValidator {

    public static void validator(Product product){

        validateName(product.getProductName());
        validatePrice(product.getPrice());
        validateQuantity(product.getQuantity());
        validateRegions(product.getRegions());
    }

    public static void categoryValidator(Category category, List<Category> categories){
        long id = category.getCategoryId();

        for (Category c : categories){
            if (id == c.getCategoryId()){
                return;
            }
        }

        throw new InvalidFieldException("Provided category id does not exist");
    }

    private static void validateName(String name){
        if (name.isEmpty() || name.isBlank()){
            throw new InvalidFieldException(ErrorMessage.PRODUCT_NAME_EMPTY.getMessage());
        }
    }

    private static void validatePrice(double price){
        if (price <= 0){
            throw new InvalidFieldException(ErrorMessage.PRODUCT_QUANTITY_INVALID.getMessage());
        }
    }

    private static void validateQuantity(int quantity){
        if (quantity <= 0){
            throw new InvalidFieldException(ErrorMessage.PRODUCT_QUANTITY_INVALID.getMessage());

        }
    }

    private static void validateRegions(List<Region> regions){
        for (Region region: regions){
            if (!EnumSet.allOf(Region.class).contains(region)){
                throw new InvalidFieldException(String.format("%s is not an available region", region.toString()));
            }
        }

    }
}
