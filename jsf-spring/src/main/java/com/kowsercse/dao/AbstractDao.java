package com.kowsercse.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public abstract class AbstractDao<T> implements GenericDao<T> {

	protected final Logger logger;
	protected final Class<T> clazz;
	
	@PersistenceContext
	protected EntityManager entityManager;

	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
		this.logger = Logger.getLogger(clazz);
	}

	@Transactional(readOnly = false)
	public void persist(T transientInstance) {
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	@Transactional(readOnly = false)
	public void persist(List<T> records) {
		for (Iterator<T> iterator = records.iterator(); iterator.hasNext();) {
			persist(iterator.next());
		}
	}

	@Transactional(readOnly = false)
	public T merge(T detachedInstance) {
		try {
			T result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	@Transactional(readOnly = false)
	public List<T> merge(List<T> items) {
		List<T> persistedItems = new ArrayList<T>();
		if (items != null) {
			for (T item : items) {
				T persistedItem = merge(item);
				persistedItems.add(persistedItem);
			}
		}
		return persistedItems;
	}

	@Transactional(readOnly = false)
	public void remove(T persistentInstance) {
		try {			
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	@Transactional(readOnly = false)
	public void remove(List<T> items) {
		if (items != null) {
			for (T item : items) {
				remove(item);
			}
		}
	}

	public T findById(Object primaryKey) {
		try {
			T instance = entityManager.find(clazz, primaryKey);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("finding failed", re);
			throw re;
		}
	}
	
	public void refresh(T refrashableInstance) {
		try {
			entityManager.refresh(refrashableInstance);
			logger.debug("refresh successful");
		} catch (RuntimeException re) {
			logger.error("refresh failed", re);
			throw re;
		}
	}
	
}
