package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Payment;
import com.example.demo.repositories.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;
	
	public List<Payment> findAll() {
		return repository.findAll();
	}
	
	public Payment findById(Long id) {
		Optional<Payment> obj = repository.findById(id);
		return obj.get();
	}
	
	public Payment insert(Payment obj, Boolean paid) {
		obj.setDate(LocalDate.now());
		obj.setPaid(paid);
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Double sumTotalByPaidTrue() {
		return repository.sumTotalByPaidTrue();
	}
	
	public Long countByPaidFalse() {
		return repository.countByPaidFalse();
	}
	
	public List<Payment> findByPaidTrue() {
		return repository.findByPaidTrue();
	}
	
	public List<Payment> findByPaidFalse() {
		return repository.findByPaidFalse();
	}
	
	public List<Payment> findByDate(LocalDate date) {
		return repository.findByDate(date);
	}
	
	public Double sumPaidPaymentsByDate(LocalDate date) {
		return repository.sumPaidPaymentsByDate(date);
	}
}
