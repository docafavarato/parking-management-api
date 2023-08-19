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

import com.example.demo.entities.Car;
import com.example.demo.services.CarService;

@RestController
@RequestMapping(value="/cars")
public class CarResource {
	
	@Autowired
	private CarService service;
	
	@GetMapping
	public ResponseEntity<List<Car>> findAll() {
		List<Car> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Car> findById(@PathVariable Long id) {
		Car obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/search", params={"licensePlate"})
	public ResponseEntity<List<Car>> findByLicensePlateIgnoreCaseContains(@RequestParam String licensePlate) {
		List<Car> obj = service.findByLicensePlateIgnoreCaseContains(licensePlate);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/inParkingFalse")
	public ResponseEntity<List<Car>> findByInParkingFalse() {
		List<Car> obj = service.findByInParkingFalse();
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Car> insert(@RequestBody Car obj) {
		obj.setInParking(false);
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
	public ResponseEntity<Car> update(@PathVariable Long id, @RequestBody Car obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
