package com.kowsercse.dao;

import java.util.List;

public interface GenericDao<T> {

	public void persist(T transientInstance);

	public void persist(List<T> records);

	public T merge(T detachedInstance);

	public List<T> merge(List<T> records);

	public void remove(T persistentInstance);

	public void remove(List<T> records);

	public T findById(Object primaryKey);
	
	public List<T> findAll();

	public void refresh(T refrashableInstance);

}