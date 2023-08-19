package com.example.demo.entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="tb_parking")
public class Parking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIME)
	private LocalTime startTime;
	
	@Temporal(TemporalType.TIME)
	private LocalTime endTime;
	
	private Boolean ended;
	private Boolean paymentGenerated;
	
	private Double rate;
	
	private Duration duration;
	
	private Double total;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@OneToOne
	@JoinColumn(name="parking_lot_id")
	private ParkingLot parkingLot;
	
	@OneToMany(mappedBy="parking")
	@JsonIgnore
	private Set<Payment> payments;
	
	public Parking() {
	}

	public Parking(Car car, String startTime, ParkingLot parkingLot, Double rate) {
		this.startTime = LocalTime.parse(startTime);
		this.car = car;
		this.parkingLot = parkingLot;
		this.rate = rate;
		parkingLot.setOccupied(true);
		car.setInParking(true);
	}
	
	@PreUpdate
    @PrePersist
    public void prePersistOrUpdate() {
        if (endTime != null) {
            setEnded(true);
            car.setInParking(false);
        } else {
        	setEnded(false);
        }
        
        if (ended) {
        	setDuration(Duration.between(startTime, endTime));
        }
        
        if (duration != null) {
        	setTotal(getRate() * getDuration().doubleValue());
        }
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return startTime.format(formatter);
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		if (endTime != null) {
			return endTime.format(formatter);
		} return null;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
		setEnded(true);
	}
	
	public Boolean getEnded() {
		return ended;
	}

	public void setEnded(Boolean ended) {
		this.ended = ended;
	}
	
	public Boolean getPaymentGenerated() {
		return paymentGenerated;
	}

	public void setPaymentGenerated(Boolean paymentGenerated) {
		this.paymentGenerated = paymentGenerated;
	}

	public Long getDuration() {
		if (endTime != null) {
			return duration.toHours();
		} return 0L;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public Double getTotal() {
		if (getDuration() != null) {
			return total;
		} return 0.0;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parking other = (Parking) obj;
		return Objects.equals(id, other.id);
	}
}
