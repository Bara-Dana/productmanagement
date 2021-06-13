package com.example.productmanagement.service;

import com.example.productmanagement.entities.CategoryModel;
import com.example.productmanagement.entities.ProductModel;
import com.example.productmanagement.entities.repository.CategoryRepository;
import com.example.productmanagement.entities.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public void addCategory(CategoryModel categoryModel) {
        categoryRepository.save(categoryModel);
    }

    public List<CategoryModel> getAll() {
        List<CategoryModel> categories = categoryRepository.findAll();
        return categories;
    }

    public void addProduct(long idProduct, long idCategory) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(idCategory);
        if (!categoryModelOptional.isPresent()) {
            throw new RuntimeException("Category doesn't exist");
        }
        CategoryModel categoryModel = categoryModelOptional.get();

        Optional<ProductModel> optionalProductModel = productRepository.findById(idProduct);
        if (!optionalProductModel.isPresent()) {
            throw new RuntimeException("Product does not exist");
        }
        ProductModel productModel = optionalProductModel.get();

        List<ProductModel> productFromCategory = categoryModel.getProducts();
        productFromCategory.add(productModel);
        categoryRepository.save(categoryModel);
    }
}
