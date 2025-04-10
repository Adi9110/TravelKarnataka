package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.service.PackageService;
import com.travel.service.RegisterService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private  PackageService packageService;

	@GetMapping("/home") 
	public String openUserHome(Model model) {
	    model.addAttribute("packages", packageService.getAllPackages());
	    return "userHome"; 
	}
	
}
