package com.example.kithub.category.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCategoryHandler implements Command<Category, Category> {

    private CategoryRepository repository;

    @Autowired
    public UpdateCategoryHandler(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Category> execute(Category category) {

        Optional<Category> newCategory = repository.findById(category.getCategoryId());

        if (newCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        repository.save(category);

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }
}
