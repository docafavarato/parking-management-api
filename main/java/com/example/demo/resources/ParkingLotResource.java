package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.ParkingLot;
import com.example.demo.services.ParkingLotService;

@RestController
@RequestMapping(value="/parkingLots")
public class ParkingLotResource {
	
	@Autowired
	private ParkingLotService service;
	
	@GetMapping
	public ResponseEntity<List<ParkingLot>> findAll() {
		List<ParkingLot> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ParkingLot> findById(@PathVariable Long id) {
		ParkingLot obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/search", params={"tag"})
	public ResponseEntity<List<ParkingLot>> findByTagIgnoreCaseContains(@RequestParam String tag) {
		List<ParkingLot> obj = service.findByTagIgnoreCaseContains(tag);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/unoccupied")
	public ResponseEntity<List<ParkingLot>> findByOccupiedFalse() {
		List<ParkingLot> obj = service.findByOccupiedFalse();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/occupied")
	public ResponseEntity<List<ParkingLot>> findByOccupiedTrue() {
		List<ParkingLot> obj = service.findByOccupiedTrue();
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<ParkingLot> insert(@RequestBody ParkingLot obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ParkingLot> update(@PathVariable Long id, @RequestBody ParkingLot obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
