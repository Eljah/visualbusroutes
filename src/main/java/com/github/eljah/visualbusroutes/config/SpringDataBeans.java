package com.github.eljah.visualbusroutes.config;

import com.github.eljah.visualbusroutes.repository.CustomRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jdo.DefaultJdoDialect;

/**
* User: pawel
* Date: 20.07.13
* Time: 14:11
*/
@Configuration
@EnableJpaRepositories(
		value = "com.github.eljah.visualbusroutes",
        repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class SpringDataBeans {
	@Bean
	public PersistenceExceptionTranslator exceptionTranslator() {
		return new DefaultJdoDialect();
	}
}
