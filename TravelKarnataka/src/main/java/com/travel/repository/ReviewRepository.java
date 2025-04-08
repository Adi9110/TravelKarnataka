package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer>{

}
