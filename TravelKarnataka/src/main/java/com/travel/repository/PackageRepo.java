package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.entity.PackageEntity;



public interface PackageRepo extends JpaRepository<PackageEntity, Integer> {
	
	boolean existByPname(String pname);
	
}
