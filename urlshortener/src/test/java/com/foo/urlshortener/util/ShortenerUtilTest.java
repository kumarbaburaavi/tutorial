package com.foo.urlshortener.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.foo.urlshortener.util.ShortenerUtil;

class ShortenerUtilTest {

	/**
	 * Test same URL should have unique hash code.
	 * i.e. UC: Each shortened url should be unique and specific to a single original url
	 */
	@Test
	public void testGenerateShortURLSameHash() {
		String longUrl = "www.ericsson.com";
		int startIndex = 0;
		int endIndex = 5;
		
		String hash1 = ShortenerUtil.generateShortURL(longUrl, startIndex, endIndex);
		String hash2 = ShortenerUtil.generateShortURL(longUrl, startIndex, endIndex);
		
		Assert.assertEquals(hash1, hash2);
	}
	/**
	 * Test Different URL should have different HASH code.
	 * i.e. UC: Each shortened url should be unique and specific to a single original url
	 */
	@Test
	public void testGenerateShortURLDifferentHashes() {
		String longUrl1 = "www.ericsson.com";
		String longUrl2 = "www.google.com";
		int startIndex = 0;
		int endIndex = 5;
		
		String hash1 = ShortenerUtil.generateShortURL(longUrl1, startIndex, endIndex);
		String hash2 = ShortenerUtil.generateShortURL(longUrl2, startIndex, endIndex);
		
		Assert.assertNotEquals(hash1, hash2);
	}
	/**
	 * HASH code size must consistent with start and end indexes.
	 */
	@Test
	public void testGenerateShortURLIndexes() {
		String longUrl = "www.ericsson.com";
		int startIndex = 0;
		int endIndex = 5;
		String hash = ShortenerUtil.generateShortURL(longUrl, startIndex, endIndex);
		Assert.assertEquals(endIndex - startIndex + 1, hash.length());
	}


}
