package com.foo.urlshortener.util;

public interface IConstants {

	// General
	static final String ALLOWEDSTRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	static final int URL_CODE_SIZE = 7;
	static final int MAX_LONG_URL_SIZE = 2048;
	static final String PATTERN_BREAKING_CHARACTERS = "[\n|\r|\t]";

	// UrlResources
	public static final String FINDING_URL_FOR_REDIRECTING = "Finding url for redirecting, code: {0}";
	public static final String FINDING_LONG_URL = "Finding long url, code: {0}";
	public static final String FINDING_OR_CREATING_URL = "Finding or creating url: {0}";
	public static final String UPDATING_URL = "Updating url: {0}";
	public static final String REMOVING_URL = "Removing url, code: {0}";

	// UrlService
	public static final String URL_NOT_FOUND_FOR_CODE = "Url not found for code ";
	public static final String FINDING_URL_BY_CODE = "Finding url, code: {0}";
	public static final String RECURSIVE_INSERT = "Recursive inserting, long url: {0}";
	public static final String FOUND_DIFFERENT_URLS_FOR_SAME_CODE = "Found different long urls for same code: {0}";
	public static final String URL_NOT_FOUND_CREATING_NEW_ONE = "Url not found, then creating a new one, code: {0}";

	// UrlShortnerHelper
	static final String MD5_ALGORITHM_IS_NOT_AVAILABLE = "The MD5 algorithm is not available in the environment.";

}
