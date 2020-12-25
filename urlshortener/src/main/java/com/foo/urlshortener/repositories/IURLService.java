package com.foo.urlshortener.repositories;

import java.util.List;

import com.foo.urlshortener.entities.URLShortener;

public interface IURLService {
	public URLShortener findCodeByUrl(String url);
	public List<URLShortener> findAllCodeByUrl(String url);
	public URLShortener findUrlbyCode(String code);
	public List<URLShortener> findAllUrlbyCode(String code);


}
