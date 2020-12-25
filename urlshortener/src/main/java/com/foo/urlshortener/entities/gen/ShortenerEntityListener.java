package com.foo.urlshortener.entities.gen;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class ShortenerEntityListener {

	@PrePersist
	@PreUpdate
	private void beforeAnyOperation(Object object) {}
}