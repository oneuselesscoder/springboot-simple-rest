package com.mac.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mac.springboot.controller.ro.DetailEntry;
import com.mac.springboot.controller.ro.OrderEntry;
import com.mac.springboot.controller.ro.ProductEntry;
import com.mac.springboot.domain.Detail;
import com.mac.springboot.domain.Ordine;
import com.mac.springboot.domain.Product;
import com.mac.springboot.repository.DetailRepository;
import com.mac.springboot.repository.OrdineRepository;

@Service
public class OrderService {
	@Autowired
	private OrdineRepository orderRepository;
	@Autowired
	private DetailRepository detailRepository;

	@Autowired
	private ProductService productService;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Ordine insert(OrderEntry orderEntry) {
		Ordine order = new Ordine();
		order.setCode(generateCode());
		order.setCreatedAt(new Date());
		order.setUserMail(orderEntry.getUserEmail());

		Ordine newOrder = orderRepository.save(order);

		orderEntry.getProducts().forEach((product) -> {
			Detail detail = new Detail();
			detail.setOrdine(order);
			Product searchProduct = productService.listOne(product.getProductId());

			detail.setPrice(searchProduct.getPrice());
			detail.setProduct(searchProduct);
			detail.setQuantity(product.getQuantity());
			detailRepository.save(detail);
		});

		return newOrder;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DetailEntry listOne(Long id) {
		Optional<Ordine> savedOrderOpt = orderRepository.findById(id);
		if (savedOrderOpt.isPresent()) {
			return new DetailEntry(savedOrderOpt.get());
		}
		return null;

	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DetailEntry> listAll(Date startDate, Date endDate) {
		List<DetailEntry> result = new ArrayList<DetailEntry>();
		if (startDate != null && endDate != null) {
			Optional<List<Ordine>> resultOpt = orderRepository.findByPeriod(startDate, endDate);

			if (resultOpt.isPresent()) {
				resultOpt.get().forEach((retail) -> {
					result.add(new DetailEntry(retail));
				});
			}
			return result;

		} else {
			orderRepository.findAll().forEach((retail) -> {
				result.add(new DetailEntry(retail));
			});
			return result;
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Double getCost(Long id) {
		DetailEntry detail = listOne(id);
		if (detail != null) {
			Double totalCost = 0.0;
			for (ProductEntry product : detail.getProducts()) {
				totalCost += (product.getPrice() * product.getQuantity());
			}
			return totalCost;
		}
		return null;
	}

	private String generateCode() {
		return UUID.randomUUID().toString();
	}
}
