package com.mac.springboot.fixtures;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mac.springboot.domain.Product;
import com.mac.springboot.service.ProductService;

@Service
public class ProductFixture {
	@Autowired
	private ProductService productService;

	public List<Product> loadProducts(int n) {

		Product product;
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			product = new Product();
			product.setName("Product " + (i + 1));
			product.setPrice((i + 1) * 10.0);

			productService.insert(product);
			products.add(product);
		}
		return products;
	}
}
