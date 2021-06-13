package com.example.productmanagement.controller;

import com.example.productmanagement.entities.CategoryModel;
import com.example.productmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("addCategory")
    public void addCategory(@RequestBody CategoryModel categoryModel) {
        categoryService.addCategory(categoryModel);

    }

    @GetMapping("getCategory")
    public List<CategoryModel> getAll() {
        return categoryService.getAll();
    }

    //id category=>id produs

    @PutMapping("addProductToCategory/{idProduct}/{idCategory}")
    public void addProductToCategory(@PathVariable long idProduct,
                                     @PathVariable long idCategory) {
        categoryService.addProduct(idProduct, idCategory);

    }

}


