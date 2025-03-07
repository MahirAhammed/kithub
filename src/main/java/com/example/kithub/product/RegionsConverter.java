package com.example.kithub.product;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class RegionsConverter implements AttributeConverter<List<Region>, String[]> {


    @Override
    public String[] convertToDatabaseColumn(List<Region> attribute) {
        if (attribute == null || attribute.isEmpty()){
            return null;
        }

        return attribute.stream()
                .map(Enum::name)
                .toArray(String[]::new);
    }

    @Override
    public List<Region> convertToEntityAttribute(String[] dbData) {
        if (dbData == null || dbData.length == 0){
            return null;
        }

        return Arrays.stream(dbData).map(Region::valueOf).collect(Collectors.toList());
    }
}
