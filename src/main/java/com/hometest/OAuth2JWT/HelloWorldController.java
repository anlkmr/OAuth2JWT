package com.hometest.OAuth2JWT;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {

	@Autowired
	RestTemplate restTemplate;
	@RequestMapping("/home")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/test")
	public void test() {
		LoginForm loginForm = new LoginForm("username", "password");
		HttpEntity<LoginForm> requestEntity
				= new HttpEntity<LoginForm>(loginForm);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<String> responseEntity
				= restTemplate.getForEntity(
				"http://localhost:8080/home", String.class
		);

		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		Assertions.assertEquals(responseEntity.getHeaders()
				.get("Foo")
				.get(0), "bar");
	}


}