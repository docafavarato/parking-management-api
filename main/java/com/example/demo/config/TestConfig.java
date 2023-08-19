package com.example.demo.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.entities.Car;
import com.example.demo.entities.Parking;
import com.example.demo.entities.ParkingLot;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.ParkingLotRepository;
import com.example.demo.repositories.ParkingRepository;
import com.example.demo.repositories.PaymentRepository;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ParkingRepository parkingRepository;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private PaymentRepository paymentLotRepository;
	
	@Override
	public void run(String... args) throws Exception {
		ParkingLot pl1 = new ParkingLot("A1");
		ParkingLot pl2 = new ParkingLot("A2");
		ParkingLot pl3 = new ParkingLot("A3");
		ParkingLot pl4 = new ParkingLot("A4");
		ParkingLot pl5 = new ParkingLot("A5");
		ParkingLot pl6 = new ParkingLot("A6");
		
		/*Car c1 = new Car("fxz-4203", "Ford", "Black");
		Car c2 = new Car("fgh-3493", "Fiat", "Red");
		Car c3 = new Car("ohj-2354", "Peugeot", "Black");
		Car c4 = new Car("pfj-2351", "BMW", "Blue");

		Parking p1 = new Parking(c1, "13:00", pl1, 4.0);
		Parking p2 = new Parking(c4, "12:22", pl3, 4.0);
		Parking p3 = new Parking(c2, "15:45", pl5, 3.0);*/
		
		/*parkingLotRepository.saveAll(Arrays.asList(pl1, pl2, pl3, pl4, pl5, pl6));
		carRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		parkingRepository.saveAll(Arrays.asList(p1, p2, p3));*/
	}
}