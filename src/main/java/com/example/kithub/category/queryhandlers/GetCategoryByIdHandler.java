package com.example.kithub.category.queryhandlers;

import com.example.kithub.Query;
import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCategoryByIdHandler implements Query<Long, Category> {

    private CategoryRepository repo;

    @Autowired
    public GetCategoryByIdHandler(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public ResponseEntity<Category> execute(Long id) {

        Optional<Category> category = repo.findById(id);
        if (category.isEmpty()){

            // TODO
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         //   throw new RuntimeException("Category does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(category.get());
    }

}
