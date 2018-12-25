package com.mac.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.mac.springboot.controller.ro.OrderEntry;
import com.mac.springboot.controller.ro.ProductEntry;
import com.mac.springboot.domain.Product;
import com.mac.springboot.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTests {

	@Autowired
	private MockMvc mockMvc;

	private MvcResult mvcResult;

	@Test
	public void _0_shouldCreatePRoducts() throws Exception {

		mvcResult = mockMvc
				.perform(post("/product").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(createProductRequest(new Product("Product X", 15.0))))
				.andExpect(status().isCreated()).andReturn();

		mockMvc.perform(
				post("/product").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(createProductRequest(new Product("Product Y", 16.0))))
				.andExpect(status().isCreated()).andReturn();

	}

	@Test
	public void _1_shouldCreateEntity() throws Exception {
		mvcResult = mockMvc.perform(post("/order").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(createOrderEntry()))
				.andExpect(status().isCreated()).andReturn();
	}

	@Test
	public void _2_shouldRetrieveEntity() throws Exception {
		mvcResult = mockMvc.perform(get("/order/3")).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void _3_shouldRetrieveTotalCost() throws Exception {
		mvcResult = mockMvc.perform(get("/order/3/totalCost")).andExpect(status().isOk()).andReturn();
		Assert.assertEquals(new Double(mvcResult.getResponse().getContentAsString()), new Double(171.0));
	}

	@Test
	public void _4_shouldRetrieveSameCost() throws Exception {
		mvcResult = mockMvc
				.perform(
						put("/product").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(createProductRequest(new Product(1L, "Product X", 17.0))))
				.andExpect(status().isOk()).andReturn();

		mvcResult = mockMvc.perform(get("/order/3/totalCost")).andExpect(status().isOk()).andReturn();
		Assert.assertEquals(new Double(mvcResult.getResponse().getContentAsString()), new Double(171.0));
	}

	@Test
	public void _5_shouldRetrieveAllOrders() throws Exception {
		mvcResult = mockMvc.perform(get("/orders")).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void _6_shouldRetrieveAllOrdersByPeriod() throws Exception {

		String date = Util.formatDate(new Date());

		mvcResult = mockMvc.perform(get("/orders?startDate=" + date + "&endDate=" + date)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void _7_shouldRetrieveNoOrdersByPeriod() throws Exception {

		String date = Util.formatDate(Util.yesterday());
		mvcResult = mockMvc.perform(get("/orders?startDate=" + date + "&endDate=" + date))
				.andExpect(status().isNoContent()).andReturn();
	}

	private String createProductRequest(Product product) {
		return Util.toJson(product);
	}

	private String createOrderEntry() {
		ProductEntry productEntry1 = new ProductEntry(1L, 5);
		ProductEntry productEntry2 = new ProductEntry(2L, 6);
		List<ProductEntry> products = new ArrayList<>();
		products.add(productEntry1);
		products.add(productEntry2);

		OrderEntry orderEntry = new OrderEntry(products, "me@here.com");
		return Util.toJson(orderEntry);

	}

}
