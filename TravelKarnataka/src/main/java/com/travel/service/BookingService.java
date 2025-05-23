package com.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.travel.entity.BookingEntity;


public interface BookingService {
	
	int addBooking(BookingEntity be);
	
	void updateBooking(BookingEntity b);
	
	List<BookingEntity> getBookingsByEmail(String email);
	
	Optional<BookingEntity> getBooking(int id);
	
	void deleteBooking(int id);
	
	List<BookingEntity> getAllBookings();
	

	
}
