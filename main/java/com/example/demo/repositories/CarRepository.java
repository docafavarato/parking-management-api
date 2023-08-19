package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	public List<Car> findByLicensePlateIgnoreCaseContains(String licensePlate);
	public List<Car> findByInParkingFalse();
}
