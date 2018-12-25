package com.mac.springboot.controller.ro;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEntry {
	private List<ProductEntry> products;
	private String userEmail;

	public OrderEntry() {

	}

	public OrderEntry(List<ProductEntry> products, String userEmail) {
		super();
		this.products = products;
		this.userEmail = userEmail;
	}

	public List<ProductEntry> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntry> products) {
		this.products = products;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
