package com.travel.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.entity.PackageEntity;
import com.travel.service.PackageService;

@RestController
@RequestMapping("package")
public class subhas {
	
	private PackageService pservice;

	@GetMapping("/packages")
	public String packages() {
		return "packages";
	}
	
	@GetMapping("/packageForm")
	public String packageForm() {
		return "packageForm";
	}
	
	@PostMapping("/addPackage")
	public String addPackage(@ModelAttribute PackageEntity p ,Model model) {
		
		if(pservice.isExist(p.getPname())) {
			model.addAttribute("msg", "package with the name :"+ p.getPname() + " already exist");
			return "packageForm";
		}
		
		int packageId=pservice.addPackage(p);
		
		if(packageId>0) {
			model.addAttribute("msg", "package added successfully");
			return "packageForm";
		}
		
		model.addAttribute("msg", "failed to add the package");
		return "packageForm";
		
		
	}
}
