package com.mac.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
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

import com.mac.springboot.domain.Product;
import com.mac.springboot.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTests {

	@Autowired
	private MockMvc mockMvc;

	private MvcResult mvcResult;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		// productRepository.deleteAll();
	}

	@Test
	public void _1_shouldCreateEntity() throws Exception {

		mvcResult = mockMvc
				.perform(post("/product").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(createProductRequest(new Product("Product X", 15.0))))
				.andExpect(status().isCreated()).andReturn();

	}

	@Test
	public void _2_shouldRetrieveEntity() throws Exception {

		mvcResult = mockMvc.perform(get("/product/1")).andExpect(status().isOk()).andReturn();

		Assert.assertEquals(mvcResult.getResponse().getContentAsString(),
				createProductRequest(new Product(1L, "Product X", 15.0)));
	}

	@Test
	public void _3_shouldUpdateEnity() throws Exception {
		mvcResult = mockMvc
				.perform(
						put("/product").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(createProductRequest(new Product(1L, "Product Y", 16.0))))
				.andExpect(status().isOk()).andReturn();

		mvcResult = mockMvc.perform(get("/product/1")).andExpect(status().isOk()).andReturn();

		Assert.assertEquals(mvcResult.getResponse().getContentAsString(),
				createProductRequest(new Product(1L, "Product Y", 16.0)));

	}

	@Test
	public void _4_shouldntRetrieveEntity() throws Exception {

		mvcResult = mockMvc.perform(get("/product/100")).andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void _5_shouldDeleteEntity() throws Exception {
		mvcResult = mockMvc.perform(delete("/product/1")).andExpect(status().isNoContent()).andReturn();
		mvcResult = mockMvc.perform(get("/product/1")).andExpect(status().isNotFound()).andReturn();
	}

	private String createProductRequest(Product product) {
		return Util.toJson(product);
	}

}