package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.entity.RegisterEntity;
import com.travel.service.RegisterService;

import com.travel.JwtUtil.*;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/travel")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	public RegisterController(RegisterService service, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JwtService jwtService) {
		super();
		this.service = service;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	private PasswordEncoder passwordEncoder;
	private  AuthenticationManager authenticationManager;
    private JwtService jwtService;
	
	
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
			 user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			service.saveUser(user);
			model.addAttribute("msg", "Resgistered Success");
	        return "login";
	    }
	  else {
		model.addAttribute("msg", "Email already exist, Login ");
        return "signup";
    } 
	}
	
	@PostMapping("/login")
	public String userLogin(@ModelAttribute RegisterEntity user , Model model ) {
		
		RegisterEntity logUser=service.getUser(user.getUserEmail());
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(),user.getUserPassword()));
 //  System.out.println(authRequest.getEmail()+ "between " + authRequest.getPassword());

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String jwtToken = jwtService.generateToken(userDetails.getUsername());
		if(logUser.getRole().equals("admin"))
			return "admin";
		else
			return "home";
		
	}
	
	
}
