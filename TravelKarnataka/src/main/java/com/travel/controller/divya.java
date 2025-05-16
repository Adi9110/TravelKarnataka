package com.travel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travel.entity.RegisterEntity;
import com.travel.entity.TripEntity;
import com.travel.repository.TripRepository;
import com.travel.service.ReviewService;
import com.travel.service.TripService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/divya")
public class divya {

	@Autowired
    private TripService tripService;
	
	 @Autowired
	 private TripRepository tripRepository;
	
	@GetMapping("/statuspage")
	public String getTrips(Model model) {
	    List<TripEntity> trips = tripService.getAllTrips(); // Fetch all trips
	    System.out.println(trips);
	    model.addAttribute("trips", trips);
        return "statuss";
    }	
	
	@PostMapping("/cancel")
	public String cancelTrip(Long tripId, HttpSession session) {
	    String userEmail = (String) session.getAttribute("umail");
	    
	    if (userEmail == null) {
	        throw new RuntimeException("User is not logged in.");
	    }
	    System.out.println(userEmail);

	    Optional<TripEntity> optionalTrip = tripRepository.findById(tripId);
	    System.out.println(optionalTrip);

	    if (optionalTrip.isPresent()) {
	        TripEntity trip = optionalTrip.get();
	        System.out.println(trip);

	        if (trip.getUser() != null && trip.getUser().getUserEmail().equals(userEmail)) {
	            trip.setStatus("Canceled");
	            tripRepository.save(trip);
	        } else {
	            throw new RuntimeException("You can only cancel your own trips.");
	        }
	    } else {
	        throw new RuntimeException("Trip not found.");
	    }
	    return "redirect:/divya/statuspage";  // <-- Correct redirect here
	}

}