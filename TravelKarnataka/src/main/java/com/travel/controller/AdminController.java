package com.travel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travel.entity.PackageEntity;
import com.travel.service.PackageService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private PackageService pservice;
	
	@GetMapping("/home")
	public String getAdminpage() {
		return "adminHome";
	}
	
//	@GetMapping("/packages")
//	public String getPackagespage() {
//		return "packages";
//	}
	
	@GetMapping("/addPackages")
	public String getAddPackagesPage() {
		return "addPackages";
	}
	
	@PostMapping("/addPackage")
	public String addPackage(@ModelAttribute PackageEntity p ,Model model) {
		
		if(pservice.isExist(p.getPname())) {
			model.addAttribute("msg", "package with the name :"+ p.getPname() + " already exist");
			return "addPackages";
		}
		
		int packageId=pservice.addPackage(p);
		
		if(packageId>0) {
			model.addAttribute("successMessage", "package added successfully");
			return "addPackages";
		}
		
		model.addAttribute("errorMessage", "failed to add the package");
		return "addPackages";
			
	}
	
	@GetMapping("/viewPackages")
	public String viewPackages(Model model) {
	    List<PackageEntity> packages = pservice.getAllPackages();
	    model.addAttribute("packages", packages);
	    return "packages"; 
	}
	
//	@GetMapping("updatePackage/{id}")
//		public String updatePackage(@RequestParam Integer id, Model model) {
//			
//			PackageEntity packageDetails = pservice.findPackageById(id);
//			
//			if(packageDetails!=null) {
//				model.addAttribute("packageDeatails", packageDetails);
//				return "updatePackage";
//			}
//			
//		}
	}
	
	
	 
	 
	
	

