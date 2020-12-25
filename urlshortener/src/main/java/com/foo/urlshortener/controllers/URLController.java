package com.foo.urlshortener.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foo.urlshortener.entities.URLShortener;
import com.foo.urlshortener.exceptions.URLShortenerException;
import com.foo.urlshortener.repositories.IURLRepository;
import com.foo.urlshortener.repositories.IURLService;
import com.foo.urlshortener.util.IConstants;
import com.foo.urlshortener.util.ShortenerUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "shorten-url/api/v1")
public class URLController {

	private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);

	@Autowired
	private IURLRepository urlRepository;
	
	@Autowired
	private IURLService urlService;


	@PostMapping("/addurl")
	public ResponseEntity<URLShortener> saveUrl(@RequestBody URLShortener url) {
		LOGGER.info("URLController.getShortUrl()");
		URLShortener shortener=urlService.findCodeByUrl(url.getUrl());
		if(shortener != null) {
			throw new URLShortenerException("URL is already exist and short code:" + shortener.getCode());
		}
		if (url.getCode() == null || url.getCode().isBlank()) {
			String code = ShortenerUtil.generateShortURL(url.getUrl(), 0, IConstants.URL_CODE_SIZE);
			url.setCode(code);
		}
		urlRepository.save(url);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{code}")
	public ResponseEntity<URLShortener> updateUrl(@Valid @RequestBody URLShortener url, @PathVariable String code) {
		LOGGER.info(IConstants.UPDATING_URL, url);

		URLShortener shortener=urlService.findUrlbyCode(code);
		if(shortener == null) {
			throw new URLShortenerException("URL not found for the short code:" + code);
		}
		shortener.setCode(url.getUrl());
		urlRepository.save(url);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{code}")
	public ResponseEntity<URLShortener> deleteUrl(@PathVariable String code) {
		LOGGER.info(IConstants.REMOVING_URL, code);
		URLShortener shortener=urlService.findUrlbyCode(code);
		if(shortener == null) {
			throw new URLShortenerException("URL not found for the short code:" + code);
		}
		urlRepository.delete(shortener);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/{code}/url")
	public ResponseEntity<URLShortener> findUrl(@PathVariable String code) {
		LOGGER.info(IConstants.FINDING_LONG_URL, code);
		ResponseEntity<URLShortener> responseEntity;
		URLShortener url = urlService.findUrlbyCode(code);
		responseEntity = ResponseEntity.ok().body(url);
		return responseEntity;
	}

	@GetMapping("/geturls")
	public List<URLShortener> findAllUrls() {
		LOGGER.info("URLController.getUrls()");
		return (List<URLShortener>) urlRepository.findAll();
	}

	@GetMapping(path = "/{code}")
	public ResponseEntity<URLShortener> findAndRedirect(@PathVariable String code,
			@RequestHeader Map<String, String> headersMap) {
		LOGGER.info(IConstants.FINDING_URL_FOR_REDIRECTING, code);
		URLShortener url = urlService.findUrlbyCode(code);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(url.getUrl()));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
}
