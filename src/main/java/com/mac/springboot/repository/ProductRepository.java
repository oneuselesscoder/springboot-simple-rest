package com.mac.springboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mac.springboot.domain.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
