package com.example.kithub.category.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryDTO;
import com.example.kithub.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddCategoryHandler implements Command<CategoryDTO,Category> {

    private CategoryRepository repository;

    @Autowired
    public AddCategoryHandler(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @Override
    public ResponseEntity<Category> execute(CategoryDTO categoryDTO) {

        if (categoryDTO.getValue().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        Category category = repository.findByValueContaining(categoryDTO.getValue());
        if (category != null){
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }

        category = new Category();
        category.setValue(categoryDTO.getValue());
        repository.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
}
