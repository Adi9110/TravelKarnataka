package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.entity.RegisterEntity;
import com.travel.service.RegisterService;


@Controller
@RequestMapping("/travel")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	
	@GetMapping("/app")
	public String loadIndex() {
		return "home";
	}
	
	@GetMapping("/signup")
	public String openSignupPage() {
		return "signup";
	}
	
	@PostMapping("/register")
	public String userRegister(@ModelAttribute RegisterEntity user, Model model) {

		boolean exist = service.checkUser(user.getUserEmail());
		if (!exist) {
			Integer id = service.saveUser(user);
			if (id > 0) {
				model.addAttribute("msg","Registered Successfully with id: " + id);
				return "Login";
			} else {
				model.addAttribute("msg","Registration failed. Please try again.");
				return "Signup";
			}
		} else {
			model.addAttribute("msg", "User already exists with this email.");
			return "Signup";
		}
	}
	
	
}
