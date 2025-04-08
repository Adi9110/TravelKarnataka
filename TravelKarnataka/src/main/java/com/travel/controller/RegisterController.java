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

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/travel")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	
	@GetMapping("/app")
	public String loadIndex() {
		return "index";
	}
	
	@GetMapping("/login")
	public String openLoginPAge() {
		return "login";
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
	
	@PostMapping("/login")
	public String userLogin(@ModelAttribute RegisterEntity user , Model model ) {
		
		RegisterEntity logUser=service.getUser(user.getUserEmail());
		
		if(logUser==null) {
			model.addAttribute("msg", "user not found");
			return "login";
		}
		
		if(logUser.getUserPassword()!=user.getUserPassword()) {
			model.addAttribute("msg", "invalid credential");
			return "login";
		}
		
		model.addAttribute("uname", logUser.getUserName());
		model.addAttribute("msg", "login successfull");
		
		return "home";
	}
	
	
}
