package com.travel.ServiceImplementation;

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

}
