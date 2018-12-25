package com.mac.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mac.springboot.domain.Product;
import com.mac.springboot.service.ProductService;

@RestController
@CrossOrigin
public class ProductRestApiController {

	public static final Logger logger = LoggerFactory.getLogger(ProductRestApiController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	ApiControllerResponse apiControllerResponse;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> ok() {

		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProduct() {

		List<Product> result = productService.listAll();
		return apiControllerResponse.response(result, result.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> newProduct(@RequestBody Product product) {

		return apiControllerResponse.response(productService.insert(product), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {

		return apiControllerResponse.response(productService.listOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/product", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

		return apiControllerResponse.response(productService.update(product), HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {

		productService.delete(id);
		return apiControllerResponse.response(true, HttpStatus.NO_CONTENT);
	}

}
