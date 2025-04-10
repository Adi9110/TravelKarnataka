package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travel.entity.PackageEntity;


@Repository
public interface PackageRepo extends JpaRepository<PackageEntity, Integer> {
	
	boolean existsByPname(String pname);
	

}
