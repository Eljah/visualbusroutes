package com.github.eljah.visualbusroutes.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * User: pawel
 * Date: 20.07.13
 * Time: 17:09
 */
public class CustomRepositoryImpl  <T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements Serializable {
	private final JpaEntityInformation<T, ?> entityInformation;

	public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
	}

	/**
	 * it must be modified because original implementation is using aggregation (count function) to check if entity exists in db)
	 * @param id
	 */
	@Override
	public void delete(ID id) {
		T entity = findOne(id);
		if(entity == null) {
			throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1);
		}
		delete(entity);
	}
}
