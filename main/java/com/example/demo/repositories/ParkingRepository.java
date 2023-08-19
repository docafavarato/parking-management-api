package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
	public List<Parking> findByCarId(Long id);
	public List<Parking> findByParkingLotTagIgnoreCaseContains(String tag);
	public List<Parking> findByEndedTrue();
	public List<Parking> findByEndedFalse();
	public Long countByEndedFalse();
	public List<Parking> findByEndedTrueAndCarId(Long id);
	public List<Parking> findByEndedFalseAndCarId(Long id);
}
