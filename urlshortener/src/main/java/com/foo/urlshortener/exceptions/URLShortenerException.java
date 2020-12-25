package com.foo.urlshortener.exceptions;

public class URLShortenerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public URLShortenerException() {
		super();
	}

	public URLShortenerException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public URLShortenerException(final String message) {
		super(message);
	}

	public URLShortenerException(final Throwable cause) {
		super(cause);
	}
}
