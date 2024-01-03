package com.saeinlee.dblab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saeinlee.dblab.domain.ProductStock;

public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {

}
