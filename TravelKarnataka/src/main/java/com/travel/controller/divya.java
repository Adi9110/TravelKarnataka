package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.travel.entity.RegisterEntity;
import com.travel.entity.ReviewEntity;
import com.travel.service.ReviewService;
import jakarta.servlet.http.HttpSession;

public class divya {
	
	@Autowired
	private ReviewService reviewservice;
	
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