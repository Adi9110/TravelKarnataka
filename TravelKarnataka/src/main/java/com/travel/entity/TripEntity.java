package com.travel.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class TripEntity {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private Double price;

	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	    private LocalDateTime bookedDate;

	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate tripDate;
	    
	    @Column(name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'pending'")
	    private String status; 

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private RegisterEntity user;
		


}
