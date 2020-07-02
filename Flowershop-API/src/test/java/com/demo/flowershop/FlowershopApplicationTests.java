package com.demo.flowershop;

import com.demo.flowershop.controllers.FlowerController;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlowershopApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FlowerController flowerController;

	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void contextLoads() throws Exception{
		assertThat(flowerController).isNotNull();
	}

	@Test
	public void flowerIdReturnOk() throws Exception{
		this.mockMvc.perform(get("/flower/{id}", "id", 1))
					.andExpect(status().isOk());
	}

	@Test
	public void flowerIdReturnInternalServerError() throws Exception{
		this.mockMvc.perform(get("/flower/{id}", "id", 12345))
					.andExpect(status().isBadRequest());
	}

}
