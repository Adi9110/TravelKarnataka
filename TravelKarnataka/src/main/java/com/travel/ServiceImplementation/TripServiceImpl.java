package com.travel.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.RegisterEntity;
import com.travel.entity.TripEntity;
import com.travel.repository.TripRepository;
import com.travel.service.TripService;

import jakarta.servlet.http.HttpSession;

@Service
public class TripServiceImpl implements TripService {
	
	@Autowired
    private TripRepository tripRepository;

	@Override
	public List<TripEntity> getAllTrips() {
	    return tripRepository.findAll();
	}
	@Override
	public boolean cancelTrip(Long tripId) {
        // Check if the trip exists
        TripEntity trip = tripRepository.findById(tripId).orElse(null);

        if (trip == null || trip.getStatus().equals("Canceled")) {
            // If the trip doesn't exist or is already canceled, return false
            return false;
        }

        // Update the status to 'Canceled'
        trip.setStatus("Canceled");
        tripRepository.save(trip);  // Save the updated trip entity

        return true;  // Return true if the trip was successfully canceled
    }

}
