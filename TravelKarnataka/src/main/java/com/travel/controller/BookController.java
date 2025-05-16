package com.travel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travel.entity.BookingEntity;
import com.travel.entity.PackageEntity;
import com.travel.service.BookingService;
import com.travel.service.PackageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/booking")
public class BookController {
	
	@Autowired
	private BookingService bs;
	
	@Autowired
	private PackageService ps;

	@GetMapping("/bookingForm")
	public String showBookingForm(@RequestParam int id, 
									HttpSession session, 
									Model model ) {
		
		if(session.getAttribute("uname")==null) {
			model.addAttribute("msg", "please login");
			return "login";
		}
		
		PackageEntity pkg= ps.findPackageById(id);
		
		if(pkg==null) {
			model.addAttribute("msg", "package is not available");
			return "userHome";
		}
		
		model.addAttribute("uname", session.getAttribute("uname"));
		model.addAttribute("uemail", session.getAttribute("umail"));
		model.addAttribute("uphone", session.getAttribute("uphone"));
		
		model.addAttribute("packageId", id);
		model.addAttribute("packageName", pkg.getPname());
		
		return "bookingForm";
	}
	@PostMapping("/booking/{id}")
	public String addBooking(Model model, 
	                         @PathVariable("id") int id, 
	                         BookingEntity booking, 
	                         HttpSession session) {

	    PackageEntity pkg = ps.findPackageById(id);
	    if (pkg == null) {
	        model.addAttribute("msg", "Invalid package selected.");
	        return "userHome";
	    }

	    Object userObj = session.getAttribute("umail");
	    if (!(userObj instanceof String)) {
	        model.addAttribute("msg", "User session expired. Please log in again.");
	        return "login";
	    }

	    String userEmail = (String) userObj;

	    if (booking.getNumberOfPeople() <= 0) {
	        model.addAttribute("msg", "Please enter a valid number of people.");
	        return "userHome";
	    }

	    booking.setPName(pkg.getPname());
	    booking.setUserEmail(userEmail);
	    booking.setPPrice(pkg.getPprice());
	    booking.setTotal(pkg.getPprice() * booking.getNumberOfPeople());

	    int bid = bs.addBooking(booking);
	    if (bid > 0) {
	        model.addAttribute("msg", "Booking successful.");
	       return "redirect:/booking/getUserBookings";
	    }

	    model.addAttribute("msg", "Booking failed.");
	    return "userHome";
	}
	
	@GetMapping("/getUserBookings")
	public String getUserBookings(@RequestParam(required=false) Integer id, HttpSession session, Model model) {

	    if (session.getAttribute("uname") == null) {
	        model.addAttribute("msg", "please login");
	        return "login";
	    }

//	    PackageEntity pkg = ps.findPackageById(id);
//
//	    if (pkg == null) {
//	        model.addAttribute("msg", "package is not available");
//	        return "userHome";
//	    }

	    String userEmail = (String) session.getAttribute("umail");
	    List<BookingEntity> bookings = bs.getBookingsByEmail(userEmail);

	    if (bookings == null || bookings.isEmpty()) {
	        model.addAttribute("msg", "book package");
	        return "booking";
	    }

	    model.addAttribute("bookings", bookings);

	    return "userBookings";
	}

	@DeleteMapping("/cancelBooking")
	public String deleteBooking(@RequestParam int bid, Model model, HttpSession session) {

	    if (session.getAttribute("uname") == null) {
	        model.addAttribute("msg", "please login");
	        return "login";
	    }

	    Optional<BookingEntity> bookingOpt = bs.getBooking(bid);

	    if (bookingOpt.isPresent() && bookingOpt.get().getStatus()=="pending") {
	        bs.deleteBooking(bid);  // Assuming you have this method in your service
	        model.addAttribute("msg", "Booking cancelled successfully");
	    } else {
	        model.addAttribute("msg", "Booking not found");
	    }

	    return "redirect:/booking/getBookings";
	}

	@GetMapping("/getAllBookings")
	public String getAllBookings(Model model) {
		 List<BookingEntity> bookings = bs.getAllBookings();

		    if (bookings == null || bookings.isEmpty()) {
		        model.addAttribute("msg", "No bookings");
		        return "booking";
		    }

		    model.addAttribute("bookings", bookings);
		    return "booking";
	}
	
	
	
}
