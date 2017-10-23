package com.github.eljah.visualbusroutes.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * User: pawel
 * Date: 20.07.13
 * Time: 17:14
 */
public class CustomRepositoryFactory extends JpaRepositoryFactory {
	protected EntityManager em;

	public CustomRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.em = entityManager;
	}

	//@Override
	protected Object getTargetRepository(RepositoryMetadata metadata) {
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
		return new CustomRepositoryImpl<>(entityInformation, em);
	}
}
