package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travel.entity.Contact;
import com.travel.entity.ReviewEntity;
import com.travel.service.ContactService;
import com.travel.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/service")
public class serviceContoller {

    @Autowired
    private ReviewService reviewService;
    
    @GetMapping("/reviewpage")
    public String getReviewPage(Model model, HttpSession session) {
        String uname = (String) session.getAttribute("uname");
        String umail = (String) session.getAttribute("umail");
        model.addAttribute("uname", uname);
        model.addAttribute("umail", umail);
        return "review";
    }

    @PostMapping("/review")
    public String userReview(@ModelAttribute ReviewEntity review, Model model, HttpSession session) {
        String uname = (String) session.getAttribute("uname");
        String umail = (String) session.getAttribute("umail");
        review.setUname(uname);
        review.setUmail(umail);
        int rid = reviewService.saveReview(review);
        if (rid > 0) {
            model.addAttribute("msg", "Thank you for your feedback!");
        } else {
            model.addAttribute("msg", "Failed to submit your feedback.");
        }

        return "review";
    }
    
    @Autowired
	private ContactService cservice;
	@GetMapping("/contact")
	public String getcontact(Model model, HttpSession session)
	{
		model.addAttribute("name", session.getAttribute("uname"));
		model.addAttribute("email", session.getAttribute("umail"));
		model.addAttribute("phone", session.getAttribute("uphone"));
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
}
