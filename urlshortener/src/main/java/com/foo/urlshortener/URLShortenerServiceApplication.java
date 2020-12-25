package com.foo.urlshortener;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.foo.urlshortener.entities.URLShortener;
import com.foo.urlshortener.repositories.IURLRepository;
import com.foo.urlshortener.util.IConstants;
import com.foo.urlshortener.util.ShortenerUtil;

@SpringBootApplication
public class URLShortenerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(URLShortenerServiceApplication.class, args);
	}

	/**
	 * Inserting Dummy Data, just for testing.
	 */
	@Bean
	CommandLineRunner init(IURLRepository urlRepository) {
		return args -> {
			Stream.of("googel", "facebook", "linkedin", "ericsson").forEach(url -> {
				URLShortener entry = new URLShortener("http://wwww."+url.toLowerCase() + ".com/login.htlm");
				String code = ShortenerUtil.generateShortURL(entry.getUrl(), 0, IConstants.URL_CODE_SIZE);
				entry.setCode(code);
				urlRepository.save(entry);
			});
			urlRepository.findAll().forEach(System.out::println);
		};
	}
}