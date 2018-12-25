package com.mac.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mac.springboot.domain.Product;
import com.mac.springboot.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Product insert(Product product) {
		return productRepository.save(product);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Product> listAll() {
		List<Product> result = new ArrayList<Product>();
		productRepository.findAll().forEach(result::add);
		return result;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Product listOne(Long id) {
		Optional<Product> savedProductOpt = productRepository.findById(id);

		if (savedProductOpt.isPresent()) {
			return savedProductOpt.get();
		}
		return null;

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Long id) {
		Product product = listOne(id);
		if (product != null)
			productRepository.delete(product);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Product update(Product product) {
		Product updateProduct = listOne(product.getId());
		if (product.getName() != null)
			updateProduct.setName(product.getName());
		if (product.getPrice() != null)
			updateProduct.setPrice(product.getPrice());

		return insert(updateProduct);
	}
}
