package com.example.kithub.product;

import java.util.EnumSet;
import java.util.List;

public class ProductValidator {

    public static void validator(Product product){

        validateName(product.getProductName());
        validatePrice(product.getPrice());
        validateQuantity(product.getQuantity());
        // TODO: invalid category

        //TODO: proper exception handling

        validateRegions(product.getRegions());
    }

    private static void validateName(String name){
        if (name.isEmpty() || name.isBlank()){
            throw new RuntimeException("Product name cannot be empty");
        }
    }

    private static void validatePrice(double price){
        if (price <= 0){
            throw new RuntimeException("Price amount is invalid");
        }
    }

    private static void validateQuantity(int quantity){
        if (quantity <= 0){
            throw new RuntimeException("Product quantity is invalid");
        }
    }

    private static void validateCategory(){

    }

    private static void validateRegions(List<Region> regions){
        for (Region region: regions){
            if (!EnumSet.allOf(Region.class).contains(region)){
                throw new RuntimeException("An invalid region was provided");
            }
        }

    }
}
