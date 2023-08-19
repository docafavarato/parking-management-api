package com.example.demo.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_car")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String licensePlate;
	
	private String model;
	
	private String brand;
	
	private String color;
	
	@OneToMany(mappedBy="car")
	@JsonIgnore
	private Set<Parking> parkings;
	
	private Boolean inParking;
	
	public Car() {
	}
	
	public Car(String licensePlate, String model, String brand, String color) {
		this.licensePlate = licensePlate;
		this.model = model;
		this.brand = brand;
		this.color = color;
		setInParking(false);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<Parking> getParkings() {
		return parkings;
	}

	public void setParkings(Set<Parking> parkings) {
		this.parkings = parkings;
	}
	
	public Boolean getInParking() {
		return inParking;
	}

	public void setInParking(Boolean inParking) {
		this.inParking = inParking;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(id, other.id);
	}
}
