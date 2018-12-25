package com.mac.springboot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mac.springboot.controller.ro.DetailEntry;
import com.mac.springboot.controller.ro.OrderEntry;
import com.mac.springboot.domain.Ordine;
import com.mac.springboot.service.OrderService;

@RestController
@CrossOrigin
public class OrderRestApiController {

	@Autowired
	private OrderService orderService;

	@Autowired
	ApiControllerResponse apiControllerResponse;

	@RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ordine> newProduct(@RequestBody OrderEntry order) {

		return apiControllerResponse.response(orderService.insert(order), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetailEntry> getProduct(@PathVariable Long id) {

		return apiControllerResponse.response(orderService.listOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/order/{id}/totalCost", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getCost(@PathVariable Long id) {

		return apiControllerResponse.response(orderService.getCost(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DetailEntry>> getAllProduct(
			@RequestParam(name = "startDate", required = false) Date startDate,
			@RequestParam(name = "endDate", required = false) Date endDate) {

		List<DetailEntry> result = orderService.listAll(startDate, endDate);
		return apiControllerResponse.response(result, result.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
}
