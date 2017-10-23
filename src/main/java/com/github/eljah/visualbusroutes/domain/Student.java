package com.github.eljah.visualbusroutes.domain;


import com.google.appengine.datanucleus.annotations.Unowned;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * User: pawel
 * Date: 20.07.13
 * Time: 10:38
 */
@Entity
public class Student extends BaseEntity {
	private String firstName;
	private String lastName;
	private String password;

	@Unowned
	@OneToOne(cascade = CascadeType.ALL)
	private Address address = new Address();

	@Unowned
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "student")
	private List<Grade> grades = Lists.newArrayList();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("firstName", firstName)
				.add("lastName", lastName)
				.add("password", password)
				.add("address", address)
				.toString();
	}
}
