package com.example.demo.resources;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.CarService;
import com.example.demo.services.ParkingLotService;
import com.example.demo.services.ParkingService;
import com.example.demo.services.PaymentService;

@RestController
@RequestMapping(value="/dashboard")
public class DashboardResource {

	@Autowired
	private CarService carService;
	
	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping(value="/total-cars")
	public ResponseEntity<Long> getRegisteredCarsCount() {
		Long count = carService.countRegisteredCars();
		return ResponseEntity.ok().body(count);
	}
	
	@GetMapping(value="/total-active-parkings")
	public ResponseEntity<Long> getActiveParkingsCount() {
		Long count = parkingService.countActiveParkings();
		return ResponseEntity.ok().body(count);
	}
	
	@GetMapping(value="/total-parkings")
	public ResponseEntity<Long> getParkingsCount() {
		Long count = parkingService.countParkings();
		return ResponseEntity.ok().body(count);
	}
	
	@GetMapping(value="/total-parking-lots")
	public ResponseEntity<Long> getParkingLotsCount() {
		Long count = parkingLotService.countParkingLots();
		return ResponseEntity.ok().body(count);
	}
	
	@GetMapping(value="/total-unoccupied-parking-lots")
	public ResponseEntity<Long> getUnoccupiedParkingLotsCount() {
		Long count = parkingLotService.countUnoccupiedParkingLots();
		return ResponseEntity.ok().body(count);
	}
	
	@GetMapping(value="/total-paid-payments-value")
	public ResponseEntity<Double> getSumTotalByPaidTrue() {
		Double total = paymentService.sumTotalByPaidTrue();
		return ResponseEntity.ok().body(total);
	}
	
	@GetMapping(value="/total-paid-payments-value-today")
	public ResponseEntity<Double> getSumPaidPaymentsByDate() {
		Double total = paymentService.sumPaidPaymentsByDate(LocalDate.now());
		return ResponseEntity.ok().body(total);
	}
	
	@GetMapping(value="/total-pending-payments")
	public ResponseEntity<Long> getcountByPaidFalse() {
		Long count = paymentService.countByPaidFalse();
		return ResponseEntity.ok().body(count);
	}
}
