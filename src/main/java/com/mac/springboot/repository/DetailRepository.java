package com.mac.springboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mac.springboot.domain.Detail;

public interface DetailRepository extends PagingAndSortingRepository<Detail, Long> {

}
