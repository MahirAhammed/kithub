package com.example.kithub.category;

import com.example.kithub.category.commandhandlers.AddCategoryHandler;
import com.example.kithub.category.commandhandlers.DeleteCategoryHandler;
import com.example.kithub.category.commandhandlers.UpdateCategoryHandler;
import com.example.kithub.category.queryhandlers.GetAllCategoriesHandler;
import com.example.kithub.category.queryhandlers.GetCategoryByIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
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
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDTO category){
        return addCategoryHandler.execute(category) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category){

        category.setCategoryId(id);
        return updateCategoryHandler.execute(category);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable long id){
        return deleteCategoryHandler.execute(id);

    }
}
