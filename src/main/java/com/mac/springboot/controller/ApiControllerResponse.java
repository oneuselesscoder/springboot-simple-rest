package com.mac.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApiControllerResponse {
	public <T> ResponseEntity<T> response(T entity, HttpStatus status) {
		if (entity == null)
			return new ResponseEntity<>(entity, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(entity, status);
	}
}
