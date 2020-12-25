package com.foo.urlshortener;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.foo.urlshortener.URLShortenerServiceApplication;
import com.foo.urlshortener.controllers.URLController;
import com.foo.urlshortener.entities.URLShortener;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		URLShortenerServiceApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class URLShortenerServiceApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	private URLController controller;

	@Test
	public void contextLoads() {
		Assertions.assertThat(controller).isNotNull();
	}

	@Test
	void testFindAllUrls() throws Exception {
		ResponseEntity<Iterable<URLShortener>> responseEntity = restTemplate.exchange(
				"http://localhost:" + port + "/shorten-url/api/v1/geturls", HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<URLShortener>>() {
				});

		Iterable<URLShortener> urls = responseEntity.getBody();
		Assertions.assertThat(urls).hasSize(4);

	}
}
