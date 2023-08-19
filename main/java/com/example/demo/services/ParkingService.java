package com.example.demo.services;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Parking;
import com.example.demo.repositories.ParkingRepository;

@Service
public class ParkingService {
	
	@Autowired
	private ParkingRepository repository;

	public List<Parking> findAll() {
		return repository.findAll();
	}
	
	public Parking findById(Long id) {
		Optional<Parking> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Parking> findByCarId(Long id) {
		return repository.findByCarId(id);
	}
	
	public Parking updateEndTime(Long id, String endTime) {
		Parking obj = repository.getReferenceById(id);
		obj.setEndTime(LocalTime.parse(endTime));
		return repository.save(obj);
	}
	
	public List<Parking> findByParkingLotTagIgnoreCaseContains(String tag) {
		return repository.findByParkingLotTagIgnoreCaseContains(tag);
	}
	
	public Parking insert(Parking obj) {
		obj.getParkingLot().setOccupied(true);
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Long countActiveParkings() {
		return repository.countByEndedFalse();
	}
	
	public Long countParkings() {
		return repository.count();
	}
	
	public List<Parking> findByEndedTrue() {
		return repository.findByEndedTrue();
	}
	
	public List<Parking> findByEndedFalse() {
		return repository.findByEndedFalse();
	}
	
	public List<Parking> findByEndedTrueAndCarId(Long id) {
		return repository.findByEndedTrueAndCarId(id);
	}
	
	public List<Parking> findByEndedFalseAndCarId(Long id) {
		return repository.findByEndedFalseAndCarId(id);
	}
}
