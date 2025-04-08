package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.entity.RegisterEntity;


public interface RegisterRepoistory extends JpaRepository<RegisterEntity, Integer> {
	boolean existsByUserEmail(String userEmail);
	
	@Query("select u from RegisterEntity u where email=?1")
	Optional<RegisterEntity> findByEmail(String email);
	
}