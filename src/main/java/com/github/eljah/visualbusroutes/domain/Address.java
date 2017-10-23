package com.github.eljah.visualbusroutes.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * User: pawel
 * Date: 24.07.13
 * Time: 20:43
 */
@Entity
public class Address extends BaseEntity {
	private String country;
	private String city;
	private String street;
	private int buildingNumber;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(int houseNo) {
		this.buildingNumber = houseNo;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("country", country)
				.add("city", city)
				.add("street", street)
				.add("houseNo", buildingNumber)
				.toString();
	}
}
