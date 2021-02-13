package com.exercise.ya;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class RestControllerTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	@Order(1)
	void invalidPostRequest() throws Exception {
		mockMvc.perform(post("/be/trace")
					.content("{ \"ip\": \"oiiuiooifjoif\" }")
					.contentType(MediaType.APPLICATION_JSON))
			      	.andExpect(status().isBadRequest());
	}
	
	@Test
	@Order(2)
	/**
	 * Table empty
	 * @throws Exception
	 */
	void getStatsNoData() throws Exception {
		mockMvc.perform(get("/be/stats")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound());
	}
	
	@Test
	@Order(3)
	void validPostRequest() throws Exception {
		mockMvc.perform(post("/be/trace")
					.content("{ \"ip\": \"8.8.8.8\" }")
					.contentType(MediaType.APPLICATION_JSON))
			      	.andExpect(status().isOk());
	}
	
	@Test
	@Order(4)
	void getStatsOK() throws Exception {
		mockMvc.perform(get("/be/stats")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
}
