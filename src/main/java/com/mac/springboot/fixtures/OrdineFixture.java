package com.mac.springboot.fixtures;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mac.springboot.controller.ro.OrderEntry;
import com.mac.springboot.controller.ro.ProductEntry;
import com.mac.springboot.domain.Product;
import com.mac.springboot.service.OrderService;

@Service
public class OrdineFixture {
	@Autowired
	private OrderService orderService;

	public void loadOrders(List<Product> products, String userMail) {
		List<ProductEntry> productsEntry = new ArrayList<>();
		ProductEntry productEntry;

		int i = 1;
		for (Product product : products) {
			productEntry = new ProductEntry(product.getId(), i);
			productsEntry.add(productEntry);
			i++;
		}

		orderService.insert(new OrderEntry(productsEntry, userMail));
	}
}
