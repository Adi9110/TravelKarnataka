package com.travel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BookingForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String date;
	
	@Column(name="people_count")
	private Integer numberOfPeople;
	
	@Column(name = "status", columnDefinition = "VARCHAR(50) DEFAULT 'pending'")
	private String status;
	
}
