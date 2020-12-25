package com.foo.urlshortener.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.foo.urlshortener.entities.gen.ShortenerEntityListener;

/**
 * @author ekubabu
 *
 */
@EntityListeners(ShortenerEntityListener.class)
@Entity
public class URLShortener implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(nullable = false, name = "SHORT_URL")
	private String code;

	@Column(nullable = false, name = "LONG_URL")
	private String url;

	/**
	 * 
	 */
	public URLShortener() {
		this.code = "";
		this.url = "";
	}

	/**
	 * @param code
	 * @param url
	 */
	public URLShortener(String url) {
		super();
		this.url = url;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof URLShortener)) {
			return false;
		}
		URLShortener other = (URLShortener) obj;
		return Objects.equals(code, other.code) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "URLShortener [id=" + id + ", code=" + code + ", url=" + url + "]";
	}
}
