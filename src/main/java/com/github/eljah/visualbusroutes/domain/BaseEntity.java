package com.github.eljah.visualbusroutes.domain;

import com.google.appengine.api.datastore.Key;

import javax.persistence.*;

/**
 * User: pawel
 * Date: 20.07.13
 * Time: 14:09
 */
@Entity
@MappedSuperclass
public class BaseEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}
}
