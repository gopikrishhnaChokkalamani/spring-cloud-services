package com.springboot.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private int address_id;

	private String houseNumber;

	private String street;

	private String city;

	private String state;

	private String zipCode;

	public int getAddress_id() {
		return address_id;
	}

	public String getCity() {
		return city;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
