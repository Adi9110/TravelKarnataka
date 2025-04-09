package com.travel.entity;

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
	private int pid;
	private String pname;
	private String pimage;
	private double pprice;
	private String pduration;
	private String pdestination;
	private String pactivities;
	private String paccommodation;
	private String pinclusion;
}
