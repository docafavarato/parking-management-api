package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Car;
import com.example.demo.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository repository;
	
	public List<Car> findAll() {
		return repository.findAll();
	}
	
	public Car findById(Long id) {
		Optional<Car> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Car> findByLicensePlateIgnoreCaseContains(String licensePlate) {
		return repository.findByLicensePlateIgnoreCaseContains(licensePlate);
	}
	
	public Car insert(Car obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Car update(Long id, Car obj) {
		Car entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Car entity, Car obj) {
		entity.setLicensePlate(obj.getLicensePlate());
		entity.setModel(obj.getModel());
		entity.setBrand(obj.getBrand());
		entity.setColor(obj.getColor());
	}
	
	public Long countRegisteredCars() {
		return repository.count();
	}
	
	public List<Car> findByInParkingFalse() {
		return repository.findByInParkingFalse();
	}
}
