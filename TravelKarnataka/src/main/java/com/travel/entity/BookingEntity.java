package com.travel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bid;
	private String userEmail;
	private String date;
	private String pName;
	private double pPrice;
	private double total;
	
	@Column(name="people_count")
	private Integer numberOfPeople;
	
	@Column(name = "status")
	private String status = "pending";

	
	
}
