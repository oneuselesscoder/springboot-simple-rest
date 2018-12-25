package com.mac.springboot.controller.ro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.springboot.domain.Ordine;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailEntry {
	private Long id;
	private String code;
	private String userMail;
	private Date createdAt;
	private List<ProductEntry> products;

	public DetailEntry(Ordine order) {
		this.id = order.getId();
		this.code = order.getCode();
		this.userMail = order.getUserMail();
		this.createdAt = order.getCreatedAt();
		this.products = new ArrayList<>();
		order.getProdutcs().forEach((detail) -> {
			this.products.add(new ProductEntry(detail.getProduct(), detail.getQuantity(), detail.getPrice()));
		});
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<ProductEntry> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntry> products) {
		this.products = products;
	}

}
