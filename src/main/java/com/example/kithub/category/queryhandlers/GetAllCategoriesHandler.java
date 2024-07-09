package com.example.kithub.category.queryhandlers;

import com.example.kithub.Query;
import com.example.kithub.category.Category;
import com.example.kithub.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCategoriesHandler implements Query<Void,List<Category>> {

    private CategoryRepository repo;

    @Autowired
    public GetAllCategoriesHandler(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public ResponseEntity<List<Category>> execute(Void input) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
    }
}
