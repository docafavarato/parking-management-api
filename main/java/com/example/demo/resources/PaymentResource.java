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
import com.example.demo.entities.Payment;
import com.example.demo.services.ParkingService;
import com.example.demo.services.PaymentService;

@RestController
@RequestMapping(value="/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService service;
	
	@Autowired
	private ParkingService parkingService;
	
	@GetMapping
	public ResponseEntity<List<Payment>> findAll() {
		List<Payment> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Payment> findById(@PathVariable Long id) {
		Payment obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/insert", params={"parkingId"})
	public ResponseEntity<Payment> insert(@RequestBody Payment obj, @RequestParam("parkingId") Long parkingId) {
		Parking pObj = parkingService.findById(parkingId);
		obj.setParking(pObj);
		pObj.setPaymentGenerated(true);
		parkingService.insert(pObj);
		obj.setTotal(obj.getParking().getTotal());
		obj = service.insert(obj, false);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/end/{id}")
	public ResponseEntity<Payment> updateEndTime(@PathVariable Long id) {
		Payment obj = service.findById(id);
		service.insert(obj, true);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/paid")
	public ResponseEntity<List<Payment>> findByPaidTrue() {
		List<Payment> obj = service.findByPaidTrue();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/pending")
	public ResponseEntity<List<Payment>> findByPaidFalse() {
		List<Payment> obj = service.findByPaidFalse();
		return ResponseEntity.ok().body(obj);
	}
}
