package com.travel.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travel.entity.PackageEntity;
import com.travel.entity.RegisterEntity;
import com.travel.service.PackageService;
import com.travel.service.RegisterService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private  PackageService packageService;
	
	@Autowired
	private RegisterService registerService;

	@GetMapping("/home") 
	public String openUserHome(Model model) {
	    model.addAttribute("packages", packageService.getAllPackages());
	    return "userHome"; 
	}
	
	@GetMapping("/bookPackage/{id}")
	public String viewBookPacakge(@PathVariable Integer id, Model model) {
		
		PackageEntity packageData = packageService.findPackageById(id);
		
		 String[] inclusions = packageData.getPinclusion().split(",");
		    
		 LocalDate today = LocalDate.now();
		 String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
		
		 model.addAttribute("package", packageData);
		 model.addAttribute("inclusions", inclusions);
		 model.addAttribute("minDate", formattedDate);
		
		 return "bookNow";
	}
	
	@GetMapping("/getAllCustomers")
	public String getAllCustomers(Model model, String message) {
	    List<RegisterEntity> customers = registerService.getAllUsers()
	        .stream()
	        .filter(user -> "user".equals(user.getRole()))
	        .collect(Collectors.toList());
	    
	    model.addAttribute("users", customers);
	    return "viewAllCustomers";
	}
	
	
 }
	

