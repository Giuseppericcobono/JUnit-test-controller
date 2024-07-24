package com.example.test_controller;

import com.example.test_controller.controllers.HomeController;
import com.example.test_controller.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestControllerApplicationTests {

	@LocalServerPort
	private Integer port;

	@Autowired
	private HomeController controller;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void restTemplateTest() {
		String output = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/", String.class);
		assertThat(output).contains("Hello World");
	}

	@Test
	void restTemplateUserTest() {
		User user = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/user", User.class);
		assertThat(user.getFirstName()).contains("Giuseppe");
		assertThat(user.getLastName()).contains("Riccobono");
	}
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Hello World")));
	}

	@Test
	public void shouldReturnDefaultMessageUser() throws Exception {
		this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("Giuseppe")).andExpect(jsonPath("$.lastName").value("Riccobono"));
	}
}
