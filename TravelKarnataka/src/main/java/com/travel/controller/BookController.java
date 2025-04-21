package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String showBookingForm(@RequestParam int pid , HttpSession session , Model model ) {
		
		if(session.getAttribute("uname")==null) {
			model.addAttribute("msg", "please login");
			return "login";
		}
		
		PackageEntity pkg= ps.findPackageById(pid);
		
		if(pkg==null) {
			model.addAttribute("msg", "package is not available");
			return "userHome";
		}
		
		model.addAttribute("uname", session.getAttribute("uname"));
		model.addAttribute("uemail", session.getAttribute("umail"));
		model.addAttribute("uphone", session.getAttribute("uphone"));
		
		model.addAttribute("packageId", pid);
		model.addAttribute("packageName", pkg.getPname());
		
		return "bookingForm";
	}
	
	@PostMapping("/booking")
	public String addBooking(Model model , BookingEntity be) {
		
		int bid=bs.addBooking(be);
		if(bid>0) {
			model.addAttribute("msg", "booking successfull");
			return "booking";
		}
		model.addAttribute("msg", "booking failed");
		return "userHome";
		
	}
	
}
