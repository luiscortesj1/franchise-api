package com.example.franchiseapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.franchiseapi.models.Branch;
import com.example.franchiseapi.models.ProductStockResponse;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Query("SELECT new com.example.franchiseapi.models.ProductStockResponse(b.name, p.name, p.stock) " +
       "FROM Branch b JOIN b.products p " +
       "WHERE b.franchise.id = :franchiseId " +
       "AND p.stock = (SELECT MAX(p2.stock) FROM b.products p2) " +
       "GROUP BY b.id, b.name, p.name " +
       "ORDER BY b.name")
    List<ProductStockResponse> findTopStockProductsByFranchise(@Param("franchiseId") Integer franchiseId);
}
