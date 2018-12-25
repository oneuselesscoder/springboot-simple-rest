package com.mac.springboot.fixtures;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import com.mac.springboot.domain.Product;


public class Bag {

	@Autowired
	private ProductFixture productFixture;

	@Autowired
	private OrdineFixture orderFixture;

	@Bean
	public Boolean sendDatabase() throws IOException {
		List<Product> products = productFixture.loadProducts(10);
		products.remove(products.size() - 1);
		products.remove(products.size() - 1);
		products.remove(products.size() - 1);

		orderFixture.loadOrders(products, "test@mail.com");
		return true;

	}

	@Bean
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cache = new ConcurrentMapCacheManager("products");
		return cache;
	}
}
