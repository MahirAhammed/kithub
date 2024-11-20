package com.example.kithub.category.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddCategoryHandler implements Command<Category,Category> {

    private final CategoryRepository repository;

    @Autowired
    public AddCategoryHandler(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    @Override
    public ResponseEntity<Category> execute(Category category) {

        if (category.getValue().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        Category newCategory = repository.findByValueContaining(category.getValue());
        if (newCategory != null){
            return ResponseEntity.status(HttpStatus.OK).body(newCategory);
        }

        newCategory = new Category(category.getValue());
        repository.save(newCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
}
