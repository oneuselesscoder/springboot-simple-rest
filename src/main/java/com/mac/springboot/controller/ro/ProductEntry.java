package com.mac.springboot.controller.ro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.springboot.domain.Product;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEntry {
	private Long productId;
	private Integer quantity;
	private Product product;
	private Double price;

	public ProductEntry() {

	}

	public ProductEntry(Long productId, Integer quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public ProductEntry(Product product, Integer quantity, Double price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
