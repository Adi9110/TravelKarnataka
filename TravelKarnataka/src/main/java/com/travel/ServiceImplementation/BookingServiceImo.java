package com.travel.ServiceImplementation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.BookingEntity;
import com.travel.repository.BookingRepo;
import com.travel.service.BookingService;


@Service
public class BookingServiceImo implements BookingService {

	@Autowired
	private BookingRepo br;
	
	@Override
	public int addBooking(BookingEntity be) {
		return br.save(be).getBid();
	}

	@Override
	public List<BookingEntity> getBookingsByEmail(String email) {
		List<BookingEntity> bookings=br.findByUserEmail(email);
		return bookings.isEmpty() ? Collections.emptyList() : bookings;
	}

	@Override
	public Optional<BookingEntity> getBooking(int id) {
	    return br.findById(id);
	}

	@Override
	public void deleteBooking(int id) {
	    if (br.existsById(id)) {
	        br.deleteById(id);
	    } else {
	        throw new RuntimeException("Booking with ID " + id + " not found");
	    }
	}

	@Override
	public List<BookingEntity> getAllBookings() {
		List<BookingEntity> bookings=br.findAll();
		return bookings.isEmpty() ? Collections.emptyList() : bookings;
	}



}
