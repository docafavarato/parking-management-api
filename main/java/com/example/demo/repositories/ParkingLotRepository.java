package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
	public List<ParkingLot> findByTagIgnoreCaseContains(String tag);
	public List<ParkingLot> findByOccupiedFalse();
	public List<ParkingLot> findByOccupiedTrue();
	public Long countByOccupiedFalse();
}
