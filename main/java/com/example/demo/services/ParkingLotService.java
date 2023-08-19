package com.example.demo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Car;
import com.example.demo.entities.ParkingLot;
import com.example.demo.repositories.ParkingLotRepository;
import com.example.demo.repositories.ParkingRepository;

@Service
public class ParkingLotService {
	
	@Autowired
	private ParkingLotRepository repository;
	
	public List<ParkingLot> findAll() {
		return repository.findAll();
	}
	
	public ParkingLot findById(Long id) {
		Optional<ParkingLot> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<ParkingLot> findByTagIgnoreCaseContains(String tag) {
		List<ParkingLot> obj = repository.findByTagIgnoreCaseContains(tag);
		return obj;
	}
	
	public List<ParkingLot> findByOccupiedFalse() {
		return repository.findByOccupiedFalse();
	}
	
	public List<ParkingLot> findByOccupiedTrue() {
		return repository.findByOccupiedTrue();
	}
	
	public ParkingLot updateOccupied(Long id, Boolean occupied) {
		ParkingLot obj = repository.getReferenceById(id);
		obj.setOccupied(occupied);
		return repository.save(obj);
	}
	
	public ParkingLot insert(ParkingLot obj) {
		obj.setOccupied(false);
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public ParkingLot update(Long id, ParkingLot obj) {
		ParkingLot entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(ParkingLot entity, ParkingLot obj) {
		entity.setTag(obj.getTag());
	}
	
	public Long countParkingLots() {
		return repository.count();
	}
	
	public Long countUnoccupiedParkingLots() {
		return repository.countByOccupiedFalse();
	}
}
