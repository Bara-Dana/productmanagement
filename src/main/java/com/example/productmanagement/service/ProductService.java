package com.example.productmanagement.service;

import com.example.productmanagement.entities.ProductModel;
import com.example.productmanagement.entities.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void add(ProductModel productModel) {
        if (productModel.getPrice() > 1) {
            throw new RuntimeException("Price should be more then 0");
        }
        if (productModel.getName() == null || productModel.getName().equals("")) {
            throw new RuntimeException("Name should not be null");

        }
        productRepository.save(productModel);
    }

        public List<ProductModel> getAll () {
            return productRepository.findAll();
        }

        public void edit (ProductModel productModel){
            if (productModel.getPrice() > 0 &&
                    productModel.getName() != null) {
                productRepository.save(productModel);
            }
        }

        public ProductModel getProductById ( long id){
            Optional<ProductModel> productModelOptional = productRepository.findById(id);
            ProductModel productModel = productModelOptional.get();
            return productModel;
        }
        public void deleteById ( long id){
            productRepository.deleteById(id);
        }
        public List<ProductModel> getProductsOrdored () {
            List<ProductModel> orderedProduct = productRepository.getProductOrderedByName();
            return orderedProduct;
        }
}

