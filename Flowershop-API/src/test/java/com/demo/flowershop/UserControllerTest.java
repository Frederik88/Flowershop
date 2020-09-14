package com.demo.flowershop;

import com.demo.flowershop.controllers.FlowerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FlowerController flowerController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(flowerController).isNotNull();
	}

	@Test
	@Order(1)
	public void successfulSignUp() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/user/sign-up")
				.content("{\"name\":\"Moritz\",\"password\":\"12345\",\"email\":\"moritz@web.de\"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void successfulLogin() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
				.param("name", "Moritz")
				.param("password", "12345"))
			.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	@WithMockUser
	public void successfulUserFound() throws Exception {
		this.mockMvc.perform(get("/user/user?name=Moritz")).andExpect(status().isOk());
	}

	@Test
	@Order(4)
	@WithMockUser
	public void successfulDeletUser() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/user/delete?name=Moritz")).andExpect(status().isOk());
	}

	@Test
	@Order(5)
	@WithMockUser
	public void checkIfUserDeleted() throws Exception {
		this.mockMvc.perform(get("/user/user?name=Moritz")).andExpect(status().isNotFound());
	}

}
