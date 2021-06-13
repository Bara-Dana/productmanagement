package com.example.productmanagement.controller;

import com.example.productmanagement.entities.ProductModel;
import com.example.productmanagement.entities.repository.ProductRepository;
import com.example.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("getProducts")
    public List<ProductModel> getAllProduct() {
        List<ProductModel> productModelList = productService.getAll();
        return productModelList;

    }

    @PostMapping("addProduct")
    public ResponseEntity addProduct(@RequestBody ProductModel productToBeAdded) {
        try {
            productService.add(productToBeAdded);

            return ResponseEntity.ok("Product added!");

        } catch (RuntimeException exception) {
            return new ResponseEntity<Object>("Product is not valid", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("edit")
    public void editProduct(@RequestBody ProductModel productToBeEdited) {

        productService.edit(productToBeEdited);
    }

    @GetMapping("getProductById/{id}")
    public ProductModel getProductById(@PathVariable long id) {
        ProductModel productFound = productService.getProductById(id);
        return productFound;
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteProductById(@PathVariable long id) {

        productService.deleteById(id);
    }

    @GetMapping("orderedProducts")
    public List<ProductModel> getProductOrdored() {
        List<ProductModel> orderedProducts = productService.getProductsOrdored();
        return orderedProducts;
    }
}
