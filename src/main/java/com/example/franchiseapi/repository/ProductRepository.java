package com.example.franchiseapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.franchiseapi.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
