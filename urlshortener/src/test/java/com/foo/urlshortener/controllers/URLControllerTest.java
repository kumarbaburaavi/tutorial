package com.foo.urlshortener.controllers;

import static com.foo.urlshortener.util.IConstants.URL_CODE_SIZE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.foo.urlshortener.entities.URLShortener;
import com.foo.urlshortener.repositories.IURLRepository;
import com.foo.urlshortener.repositories.IURLService;
import com.foo.urlshortener.util.ShortenerUtil;

@RunWith(SpringRunner.class)
@WebMvcTest
class URLControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IURLRepository repository;

	@MockBean
	private IURLService service;

	@Test
	void testSaveUrl() throws Exception {

		String inputJson = "{\"url\":\"http://www.google.com\"}";
		this.mockMvc.perform(
				post("/shorten-url/api/v1/addurl/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNoContent());

		String newURL = "http://www.google.com";
		int startIndex = 0;
		int endIndex = startIndex + URL_CODE_SIZE - 1;
		String newCode = ShortenerUtil.generateShortURL(newURL, startIndex, endIndex);
		URLShortener url = new URLShortener(newURL);
		url.setCode(newCode);
		given(service.findCodeByUrl(url.getUrl())).willReturn(url);
		
		this.mockMvc.perform(
				post("/shorten-url/api/v1/addurl/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andExpect(status().isNotFound())
		.andExpect(content().json("{'message':'URL is already exist and short code:ZWQ2NDZ'}"));

	}

	@Test
	void testUpdateUrl() throws Exception {

		String newURL = "http://www.google.com";
		String existingCode = "ZWQ2NDZ";

		int startIndex = 0;
		int endIndex = startIndex + URL_CODE_SIZE - 1;
		String newCode = ShortenerUtil.generateShortURL(newURL, startIndex, endIndex);
		URLShortener url = new URLShortener(newURL);
		url.setCode(newCode);
		given(service.findUrlbyCode(existingCode)).willReturn(url);
		String inputJson = "{\"code\":\"ZWQ2NDZ\", \"url\":\"http://www.google.com\"}";
		this.mockMvc.perform(put("/shorten-url/api/v1/" + existingCode).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andExpect(status().isNoContent());
		
		given(service.findUrlbyCode(existingCode)).willReturn(null);
		this.mockMvc.perform(put("/shorten-url/api/v1/" + existingCode).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andExpect(status().isNotFound()).andExpect(content().json("{'message':'URL not found for the short code:ZWQ2NDZ'}"));

	}

	@Test
	void testDeleteUrl() throws Exception {
		String existingCode = "ZWQ2NDZ";
		URLShortener url = new URLShortener();
		url.setCode(existingCode);
		given(service.findUrlbyCode(existingCode)).willReturn(url);
		this.mockMvc.perform(delete("/shorten-url/api/v1/" + existingCode)).andExpect(status().isNoContent());
		
		given(service.findUrlbyCode(existingCode)).willReturn(null);
		this.mockMvc.perform(delete("/shorten-url/api/v1/" + existingCode)).andExpect(status().isNotFound())
		.andExpect(content().json("{'message':'URL not found for the short code:ZWQ2NDZ'}"));
	}

	@Test
	void testFindUrl() throws Exception {
		String existingCode = "ZWQ2NDZ";
		URLShortener url = new URLShortener("http://www.google.com");
		url.setCode(existingCode);
		given(service.findUrlbyCode(existingCode)).willReturn(url);
		this.mockMvc.perform(get("/shorten-url/api/v1/" + existingCode + "/url")).andExpect(status().isOk())
				.andExpect(content().json("{'id':0, 'code': 'ZWQ2NDZ','url': 'http://www.google.com'}"));
		;
	}

	@Test
	void testFindAllUrls() throws Exception {
		String existingCode = "ZWQ2NDZ";
		URLShortener url = new URLShortener("http://www.google.com");
		url.setCode(existingCode);
		given(repository.findAll()).willReturn(Arrays.asList(url));
		this.mockMvc.perform(get("/shorten-url/api/v1/geturls")).andExpect(status().isOk()).andExpect(content().json("[{'id':0, 'code': 'ZWQ2NDZ','url': 'http://www.google.com'}]"));
	}

	@Test
	void testFindAndRedirect() throws Exception {
		String existingCode = "ZWQ2NDZ";
		String newURL = "http://www.google.com";

		URLShortener url = new URLShortener(newURL);
		url.setCode(existingCode);
		given(service.findUrlbyCode(existingCode)).willReturn(url);
		this.mockMvc.perform(get("/shorten-url/api/v1/" + existingCode)).andExpect(status().is3xxRedirection())
				.andExpect(header().string(HttpHeaders.LOCATION, equalTo(url.getUrl())));
	}

}
