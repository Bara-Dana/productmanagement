package com.example.productmanagement.controller;

import com.example.productmanagement.entities.ManufacturerModel;

import com.example.productmanagement.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @PostMapping("add")
    private void addManufacturer(@RequestBody ManufacturerModel manufacturerToBeADD) {
        manufacturerService.addManufacturer(manufacturerToBeADD);
    }

    @GetMapping("getAll")
    private List<ManufacturerModel> getAllManufacturer() {
        List<ManufacturerModel> manufacturerModelList = manufacturerService.findAll();
        return manufacturerModelList;
    }

    @GetMapping("getById/{id}")
    private ManufacturerModel getManufacturerById(@PathVariable long id){
        ManufacturerModel manufacturerFound = manufacturerService.deleteById(id);
//     ManufacturerModel manufacturerFound = manufacturerService.findById(id);
       return manufacturerFound;

    }

    @PutMapping("editManufacturer")
    private void editManufacturer(@RequestBody ManufacturerModel manufacturerToBeEdited){
        manufacturerService.save(manufacturerToBeEdited);
    }

    @DeleteMapping("deleteManufacturer/{id}")
    private void deleteManufacturerById(@PathVariable long id){
        manufacturerService.deleteById(id);
    }

    @PutMapping("addAProductToAManufacturer/{manufacturerId}/{productId}")
    public void addManufacturerToAProduct(@PathVariable long manufacturerId,
                                  @PathVariable long productId){
        manufacturerService.addManufacturerToAProduct(manufacturerId,productId);
    }
}
