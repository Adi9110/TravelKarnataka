package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.travel.entity.ReviewEntity;
import com.travel.service.ReviewService;
import jakarta.servlet.http.HttpSession;

public class divya {
	
	@Autowired
	private ReviewService reviewservice;
	
	
//	if ((repo.findByUserEmail(email).getUserEmail().equals(email))
//			&& (repo.findByUserEmail(email).getUserPassword().equals(password))) {
//		System.out.println("login Succesfull...........");
//		session.setAttribute("umail", repo.findByUserEmail(email).getUserEmail());
//		session.setAttribute("uname", repo.findByUserEmail(email).getUserName());
//		session.setAttribute("uphone", repo.findByUserEmail(email).getUserPhone());
//	}
	@PostMapping("/review")
	public String userReview(@ModelAttribute ReviewEntity review, Model model,HttpSession session) {
		model.addAttribute("uname", session.getAttribute("uname"));
		model.addAttribute("umail", session.getAttribute("umail"));
		int rid=reviewservice.saveReview(review);
		if(rid>0) {
			model.addAttribute("msg","Thank you for your Feedback");
			return "review";
		}else {
			model.addAttribute("msg","Failed to recieve your Feedback");
			return "review";
		}
	}
}