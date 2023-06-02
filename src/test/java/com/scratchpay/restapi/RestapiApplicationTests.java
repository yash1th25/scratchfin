package com.scratchpay.restapi;

import com.scratchpay.restapi.UserEntity.User;
import com.scratchpay.restapi.resources.Controller;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.mockito.Mockito.*;
import java.io.*;
import java.util.*;


@SpringBootTest
class RestapiApplicationTests {

	@MockBean
	private RestTemplate restTemplate;

	private Controller controller;

	@Before
	public void setUp() {
		controller = new Controller();
	}

	@Test
	public void testPostAndGetCallsOnData() throws IOException {
//		// Load data from CSV
//		String csvFile = "data.csv";
//		String line;
//		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
//		while ((line = reader.readLine()) != null) {
//			String[] data = line.split(",");
//			String id = data[0];
//			String name = data[1];
//			// Perform POST call
//			String postUrl = "http://localhost:8080/v1/users";
//			HttpHeaders headers = new HttpHeaders();
//			String requestBody = "{\"id\": \"" + id + "\", \"name\": \"" + name + "\"}";
//			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
////            ResponseEntity<String> postResponse = new ResponseEntity<>("Post Success", HttpStatus.OK);
//			ResponseEntity<String> postResponse = restTemplate.exchange(postUrl, HttpMethod.POST, requestEntity, String.class);
////            assertEquals(HttpStatus.OK, postResponse.getStatusCode());
////            assertEquals("Post Success", postResponse.getBody());



		}

}


