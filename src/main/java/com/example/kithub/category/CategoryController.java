package com.example.kithub.category;

import com.example.kithub.category.commandhandlers.AddCategoryHandler;
import com.example.kithub.category.commandhandlers.DeleteCategoryHandler;
import com.example.kithub.category.commandhandlers.UpdateCategoryHandler;
import com.example.kithub.category.queryhandlers.GetAllCategoriesHandler;
import com.example.kithub.category.queryhandlers.GetCategoryByIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@EnableMethodSecurity
public class CategoryController {

    private final GetAllCategoriesHandler getAllCategoriesHandler;
    private final GetCategoryByIdHandler getCategoryByIdHandler;
    private final AddCategoryHandler addCategoryHandler;
    private final UpdateCategoryHandler updateCategoryHandler;
    private final DeleteCategoryHandler deleteCategoryHandler;

    @Autowired
    public CategoryController(GetAllCategoriesHandler getAllCategoriesHandler, GetCategoryByIdHandler getCategoryByIdHandler, AddCategoryHandler addCategoryHandler, UpdateCategoryHandler updateCategoryHandler, DeleteCategoryHandler deleteCategoryHandler) {
        this.getAllCategoriesHandler = getAllCategoriesHandler;
        this.getCategoryByIdHandler = getCategoryByIdHandler;
        this.addCategoryHandler = addCategoryHandler;
        this.updateCategoryHandler = updateCategoryHandler;
        this.deleteCategoryHandler = deleteCategoryHandler;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(){
        return getAllCategoriesHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id){
        return getCategoryByIdHandler.execute(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return addCategoryHandler.execute(category) ;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category){

        category.setCategoryId(id);
        return updateCategoryHandler.execute(category);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteCategory(@PathVariable long id){
        return deleteCategoryHandler.execute(id);

    }
}
