package com.nttd.rampUp.cars.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author pabellap
 *
 */
@Entity
@Table(name = "Car")
@NamedQuery(name ="findAll", query = "SELECT c FROM Car c")
public class Car {

	@Id
	@GeneratedValue
	@Column(name ="id")
	private long id;
	
	@Column(name ="brand")
	private String brand;
	@Column(name ="registration")
	private LocalDate registration;
	@Column(name = "country")
	private String country;
	@Column(name ="createdAt")
	private LocalDate createdAt;
	@Column(name ="lastUpdate")
	private LocalDate lastUpdate;
	
	/**
	 * @param id
	 * @param brand
	 * @param country
	 */
	public Car(int id, String brand, String country) {
		this.id = id;
		this.brand = brand;
		this.registration = LocalDate.now();
		this.country = country;
		this.createdAt = LocalDate.now();
		this.lastUpdate = LocalDate.now();
	}
	
	/**
	 * 
	 */
	public Car() {
		this.registration = LocalDate.now();
		this.createdAt = LocalDate.now();
		this.lastUpdate = LocalDate.now();
	}

	/**
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.lastUpdate = LocalDate.now();
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.lastUpdate = LocalDate.now();
		this.brand = brand;
	}

	/**
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 */
	public void setCountry(String country) {
		this.lastUpdate = LocalDate.now();
		this.country = country;
	}
	
	
}
