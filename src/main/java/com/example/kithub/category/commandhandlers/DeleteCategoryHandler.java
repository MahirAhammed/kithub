package com.example.kithub.category.commandhandlers;

import com.example.kithub.Command;
import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryRepository;
import com.example.kithub.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteCategoryHandler implements Command<Long,Void> {

    private final CategoryRepository repository;

    @Autowired
    public DeleteCategoryHandler(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseEntity<Void> execute(Long id) {
        Optional<Category> category = repository.findById(id);

        if (category.isEmpty()){
            throw new CategoryNotFoundException();
        }

        repository.delete(category.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
