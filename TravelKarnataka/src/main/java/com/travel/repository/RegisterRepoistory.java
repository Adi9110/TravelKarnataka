package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.RegisterEntity;


public interface RegisterRepoistory extends JpaRepository<RegisterEntity, Integer> {
	boolean existsByUserEmail(String userEmail);
}