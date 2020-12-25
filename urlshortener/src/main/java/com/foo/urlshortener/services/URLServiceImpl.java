package com.foo.urlshortener.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.foo.urlshortener.entities.URLShortener;
import com.foo.urlshortener.repositories.IURLService;

@Service
public class URLServiceImpl implements IURLService {

	private final static Logger LOGGER = LoggerFactory.getLogger(URLServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public URLShortener findCodeByUrl(String url) {
		LOGGER.info("URLServiceImpl.findCodeByUrl()");
		TypedQuery<URLShortener> typedQuery = em.createQuery("SELECT u FROM URLShortener u WHERE u.url=:url",
				URLShortener.class);
		typedQuery.setParameter("url", url);
		return typedQuery.getResultList().isEmpty()?null:typedQuery.getResultList().get(0);
	}

	@Override
	public List<URLShortener> findAllCodeByUrl(String url) {
		LOGGER.info("URLServiceImpl.findAllCodeByUrl()");
		TypedQuery<URLShortener> typedQuery = em.createQuery("SELECT u FROM URLShortener u WHERE u.url=:url",
				URLShortener.class);
		typedQuery.setParameter("url", url);
		return typedQuery.getResultList();
	}

	@Override
	public URLShortener findUrlbyCode(String code) {
		LOGGER.info("URLServiceImpl.findUrlbyCode()");
		TypedQuery<URLShortener> typedQuery = em.createQuery("SELECT u FROM URLShortener u WHERE u.code=:code",
				URLShortener.class);
		typedQuery.setParameter("code", code);
		return typedQuery.getResultList().isEmpty()?null:typedQuery.getResultList().get(0);
	}

	@Override
	public List<URLShortener> findAllUrlbyCode(String code) {
		LOGGER.info("URLServiceImpl.findAllUrlbyCode()");
		TypedQuery<URLShortener> typedQuery = em.createQuery("SELECT u FROM URLShortener u WHERE u.code=:code",
				URLShortener.class);
		typedQuery.setParameter("code", code);
		return typedQuery.getResultList();
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	public EntityManager getEntityManager() {
		return em;
	}
}
