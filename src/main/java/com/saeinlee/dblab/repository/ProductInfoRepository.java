package com.saeinlee.dblab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saeinlee.dblab.domain.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

}
