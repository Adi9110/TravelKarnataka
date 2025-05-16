package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.entity.RegisterEntity;
import com.travel.service.PackageService;
import com.travel.service.RegisterService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/travel")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	

	@Autowired
	private  PackageService packageService;

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
	public String userLogin(@ModelAttribute RegisterEntity user , Model model ,HttpSession session) {
		
		 RegisterEntity logUser = service.getUser(user.getUserEmail(), user.getUserPassword(), session);
		
		if(logUser!=null)
			if(logUser.getUserPassword().equals(user.getUserPassword()) ) {
				if(logUser.getRole().equals("admin")) {
					return "adminHome";
				}
				else {
					model.addAttribute("packages", packageService.getAllPackages());
					return "userHome";
				}
			}
			else 
			{
				model.addAttribute("msg", "invalid credentials");
			}
		return "login";
        //  System.out.println(authRequest.getEmail()+ "between " + authRequest.getPassword());

			
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // destroy session
	    return "redirect:/travel/login?logout"; // redirect to login with message
	}

	
	
	
	
}