package com.demo.flowershop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.demo.flowershop.controllers.FlowerController;
import com.demo.flowershop.models.FlowerModel;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FlowerControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FlowerController flowerController;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(flowerController).isNotNull();
	}

	@Test
	public void successfulGetFlowers() throws Exception {
		this.mockMvc.perform(get("/flower/flowers")).andExpect(status().isOk());
	}

	@Test
	public void badRequestMissingImageUpload() throws Exception {
		this.mockMvc
				.perform(multipart("/flower/upload")
						.param("name", "TestFlower")
						.param("type", "TestFlower")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(1)
	public void successfulUpload() throws Exception {
		File file = new File("F:\\20201618_SpringBoot_Flowershop\\Flowershop-API\\img.jpg");

		MockMultipartFile img = new MockMultipartFile("image", file.getName() + "..", "image/jpg",
				Files.readAllBytes(file.toPath()));

		this.mockMvc
				.perform(multipart("/flower/upload").file("image", img.getBytes()).param("name", "TestFlower")
						.param("type", "TestFlower").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	public void successfulGetFlower() throws Exception {
		this.mockMvc.perform(get("/flower/TestFlower"))
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(3)
	public void successfulDeleteFlower() throws Exception {
		this.mockMvc.perform(post("/flower/delete/TestFlower"))
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(4)
	public void successfulRemovedFromDatabase() throws Exception {
		this.mockMvc.perform(get("/flower/TestFlower"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void successfulFilterByTypes() throws Exception {
		final List<FlowerModel> filteredFlowers = new ArrayList<>();
		
		MvcResult result = this.mockMvc.perform(get("/flower/type/Orchid"))
				.andExpect(status().isOk())
				.andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
}
