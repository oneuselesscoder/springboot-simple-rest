package com.mac.springboot.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mac.springboot.domain.Ordine;

public interface OrdineRepository extends PagingAndSortingRepository<Ordine, Long> {
	@Query("SELECT t FROM Ordine t where t.createdAt >= :startDate and t.createdAt <= :endDate")
	Optional<List<Ordine>> findByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
