package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.entity.Contact;
import com.travel.service.ContactService;

@Controller
@RequestMapping("/adi")
public class adi {

	@Autowired
	private ContactService cservice;
	@GetMapping("/contact")
	public String getcontact()
	{
		return "contact";
	}
	
	@PostMapping("/submit")
	public String contactUs(Contact con,Model model)
	{
		int a=cservice.add(con);
		if(a>0)
		{
			model.addAttribute("msg", "You will be Informed");
			
		}
		else
			model.addAttribute("msg", "Contact failed");
		return "contact";
	}
//	@
//	public String addsubmit(Contact con, Model model) {
//		
//		return "";
//	}
}
