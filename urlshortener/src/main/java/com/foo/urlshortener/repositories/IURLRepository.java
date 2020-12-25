package com.foo.urlshortener.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foo.urlshortener.entities.URLShortener;

@Repository
public interface IURLRepository extends CrudRepository<URLShortener, Long>{}
