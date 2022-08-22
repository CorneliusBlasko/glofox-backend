package com.glofox.backend.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudioControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String classesUrl;
  private String bookingsUrl;

  private MultiValueMap<String, String> headers;

  @BeforeEach
  public void setUp(){
    headers = new LinkedMultiValueMap<>();
    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    classesUrl = "http://localhost:" + port + "/andy/classes";
    bookingsUrl = "http://localhost:" + port + "/Jane/bookings";
  }

  @AfterEach
  public void clean(){
    this.headers = null;
    this.classesUrl = null;
    this.bookingsUrl = null;
  }

  @Test
  public void emptyBodyShouldReturnBadRequestClasses() {
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);
    ResponseEntity<String> response = restTemplate.postForEntity(classesUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void properBodyShouldReturnCreatedClasses() {
    String body = "{\"name\":\"class 1\",\"start\":\"22-09-2022\",\"end\":\"23-09-2022\",\"capacity\":\"200\"}";
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(classesUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
  }

  @Test
  public void wrongBodyShouldReturnBadRequestClasses() {
    String body = "{\"name\":\"class 1\",\"start\":\"22-09-2022\",\"end\":\"23-09-2022\"}";
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(classesUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void nonExistentOwnerReturnsForbiddenClasses() {
    String wrongUrl = "http://localhost:" + port + "/steve/classes";
    String body = "{\"name\":\"class 1\",\"start\":\"22-09-2022\",\"end\":\"23-09-2022\",\"capacity\":\"200\"}";
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(wrongUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
  }

  @Test
  public void wrongDateReturnsConflictClasses() {
    String body = "{\"name\":\"class 1\",\"start\":\"24-09-2022\",\"end\":\"23-09-2022\",\"capacity\":\"200\"}";
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(classesUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    Assertions.assertEquals(response.getBody(), "The start date must be before the end date");
  }

  @Test
  public void emptyBodyShouldReturnBadRequestBookings(){
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);
    ResponseEntity<String> response = restTemplate.postForEntity(bookingsUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void properBodyShouldReturnCreatedBookings() {
    String body = "{\"className\":\"spinning\",\"date\":\"26-09-2022\"}";
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(bookingsUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
  }

  @Test
  public void wrongBodyShouldReturnBadRequestBookings() {
    String body = "{\"className\":\"spinning\"}";
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(bookingsUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void nonExistentMemberReturnsForbiddenBookings() {
    String wrongUrl = "http://localhost:" + port + "/andy/bookings";
    String body = "{\"className\":\"spinning\",\"date\":\"26-09-2022\"}";
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(wrongUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
  }

  @Test
  public void wrongDateReturnsConflictBookings() {
    String body = "{\"className\":\"spinning\",\"date\":\"14-09-2022\"}";
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(bookingsUrl, httpEntity, String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
  }

}