package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Parking;
import com.example.demo.services.CarService;
import com.example.demo.services.ParkingLotService;
import com.example.demo.services.ParkingService;

@RestController
@RequestMapping(value="/parkings")
public class ParkingResource {
	
	@Autowired
	private ParkingService service;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	@Autowired
	private CarService carService;
	
	
	@GetMapping
	public ResponseEntity<List<Parking>> findAll() {
		List<Parking> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Parking> findById(@PathVariable Long id) {
		Parking obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/search", params={"carId"})
	public ResponseEntity<List<Parking>> findByCarId(@RequestParam Long carId) {
		List<Parking> obj = service.findByCarId(carId);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/search", params={"parkingLotTag"})
	public ResponseEntity<List<Parking>> findByParkingLotTagIgnoreCaseContains(@RequestParam String tag) {
		List<Parking> obj = service.findByParkingLotTagIgnoreCaseContains(tag);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/end/{id}", params={"endTime"})
	public ResponseEntity<Parking> updateEndTime(@PathVariable Long id, @RequestParam String endTime) {
		Parking obj = service.updateEndTime(id, endTime);
		parkingLotService.updateOccupied(obj.getParkingLot().getId(), false);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/going")
	public ResponseEntity<List<Parking>> findByEndedFalse(@RequestParam(required=false) Long carId) {
		List<Parking> obj = (carId != null) ? service.findByEndedFalseAndCarId(carId) : service.findByEndedFalse();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/ended")
	public ResponseEntity<List<Parking>> findByEndedTrue(@RequestParam(required=false) Long carId) {
		List<Parking> obj = (carId != null) ? service.findByEndedTrueAndCarId(carId) : service.findByEndedTrue();
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/insert", params={"carId", "parkingLotId"})
	public ResponseEntity<Parking> insert(@RequestBody Parking obj, @RequestParam("carId") Long carId, @RequestParam("parkingLotId") Long parkingLotId) {
		obj.setCar(carService.findById(carId));
		obj.getCar().setInParking(true);
		obj.setParkingLot(parkingLotService.findById(parkingLotId));
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.findById(id).getCar().setInParking(false);
		service.findById(id).getParkingLot().setOccupied(false);
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
