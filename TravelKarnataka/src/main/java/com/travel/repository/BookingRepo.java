package com.travel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.entity.BookingEntity;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, Integer> {

	
	@Query("SELECT b FROM BookingEntity b WHERE b.userEmail = ?1")
	List<BookingEntity> findByUserEmail(String email);
	
	Optional<BookingEntity> findById(Integer id);
}
