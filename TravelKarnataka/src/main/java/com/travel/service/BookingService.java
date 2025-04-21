package com.travel.service;

import org.springframework.stereotype.Service;

import com.travel.entity.BookingEntity;


public interface BookingService {
	
	int addBooking(BookingEntity be);
	
}
