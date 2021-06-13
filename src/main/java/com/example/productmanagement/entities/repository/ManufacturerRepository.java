package com.example.productmanagement.entities.repository;

import com.example.productmanagement.entities.ManufacturerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerModel, Long> {
}
