package com.travel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.RegisterEntity;
import com.travel.entity.TripEntity;

public interface TripRepository extends JpaRepository<TripEntity, Long> {
	List<TripEntity> findAll();
}