package com.travel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PackageEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	
	private String pname;
	private String pimage;
	private String pimage2;
	private String pimage3;
	private String pimage4;
	private String pimage5;
	
	 @Column(length = 1000)
	private String pdescription;
	
	private double pprice;
	private String pduration;
	private String pdestination;
	private String pactivities;
	private String paccommodation;
	private String pinclusion;
}
