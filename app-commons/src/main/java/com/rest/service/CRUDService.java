package com.rest.service;

import java.util.List;

public interface CRUDService<E> {
	E save(E entity);
	
	E update(E entity);

	E findById(long id);

	List<E> findAll();

	void delete(long id);
	
	void deleteAll();
}
