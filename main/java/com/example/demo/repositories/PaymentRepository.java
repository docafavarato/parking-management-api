package com.example.demo.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	public List<Payment> findByParkingCarId(Long id);
	public List<Payment> findByPaidTrue();
	public List<Payment> findByPaidFalse();
	public List<Payment> findByDate(LocalDate date);
	
	@Query("SELECT COALESCE(SUM(p.total), 0) FROM Payment p WHERE p.paid = true")
	public Double sumTotalByPaidTrue();
	 
	@Query("SELECT COALESCE(SUM(p.total), 0) FROM Payment p WHERE p.paid = true AND p.date = :date")
    public Double sumPaidPaymentsByDate(@Param("date") LocalDate date);
	
	public Long countByPaidFalse();
}
