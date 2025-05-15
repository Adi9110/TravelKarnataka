package com.travel.service;

import java.util.List;

import com.travel.entity.RegisterEntity;
import com.travel.entity.TripEntity;

import jakarta.servlet.http.HttpSession;

public interface TripService {
	List<TripEntity> getAllTrips();
	 boolean cancelTrip(Long tripId);
}
