package com.example.productmanagement.service;

import com.example.productmanagement.entities.ManufacturerModel;
import com.example.productmanagement.entities.ProductModel;
import com.example.productmanagement.entities.repository.ManufacturerRepository;
import com.example.productmanagement.entities.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ProductRepository productRepository;

//    public void addManufacturer(ManufacturerModel manufacturerModel) {
//        manufacturerRepository.save(manufacturerModel);
  //  }

    public List<ManufacturerModel> findAll() {
        List<ManufacturerModel> manufacturerModelList = manufacturerRepository.findAll();
        return manufacturerModelList;
    }


    public void save(ManufacturerModel manufacturerToBeEdited) {
        manufacturerRepository.save(manufacturerToBeEdited);
    }

    public ManufacturerModel deleteById(long id) {
        manufacturerRepository.deleteById(id);
        return null;
    }

    public void addManufacturerToAProduct(long manufacturerId, long productId) {
        Optional<ManufacturerModel> manufacturerModelOptional = manufacturerRepository.findById(productId);
        if(!manufacturerModelOptional.isPresent()){
            throw new RuntimeException("Manufacturer does not exist!");
        }
        ManufacturerModel manufacturerModel = manufacturerModelOptional.get();

        Optional<ProductModel> productModelOptional = productRepository.findById(manufacturerId);
        if(!productModelOptional.isPresent()){
            throw new RuntimeException("Product does not exist!");
        }
        ProductModel productModel = productModelOptional.get();

        List<ProductModel> productFromManufacturer = manufacturerModel.getProducts();
        productFromManufacturer.add(productModel);
        manufacturerRepository.save(manufacturerModel);
    }

    public void addManufacturer(ManufacturerModel manufacturerToBeADD) {
        manufacturerRepository.save(manufacturerToBeADD);
    }
}

