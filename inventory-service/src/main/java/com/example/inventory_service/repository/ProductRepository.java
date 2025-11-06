package com.example.inventory_service.repository;

import com.example.inventory_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//j'ai pas besion de cree le controller
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,String> {
}