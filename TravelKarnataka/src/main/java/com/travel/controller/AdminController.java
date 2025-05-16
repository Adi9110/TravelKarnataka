package com.travel.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travel.entity.BookingEntity;
import com.travel.entity.PackageEntity;
import com.travel.entity.RegisterEntity;
import com.travel.service.BookingService;
import com.travel.service.PackageService;
import com.travel.service.RegisterService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private PackageService pservice;
	
	@Autowired
	private BookingService bservice;
	
	@Autowired
	private RegisterService registerService;
	
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
	
	@GetMapping("/updatePackage/{id}")
		public String updatePackage(@PathVariable Integer id, Model model) {
			
			PackageEntity packageDetails = pservice.findPackageById(id);
			
			if(packageDetails!=null) {
				model.addAttribute("packageDetails", packageDetails);
				return "updatePackage";
			}
			else {
				model.addAttribute("failedMsg","Something Went Wrong! Please Try Again");
				return "redirect:/admin/packages";
			}
			
		}
	
	@PostMapping("/updatePackage")
	public String updatePackage(@ModelAttribute PackageEntity p, Model model,RedirectAttributes redirectAttributes) {
		
		pservice.updatePackage(p);
		
		redirectAttributes.addFlashAttribute("successMessage", "Package Updated Successfully!");
		
		System.out.println("Updated package: " + p);
		System.out.println("Redirecting to: /admin/updatePackage/" + p.getPid());
		
	    return "redirect:/admin/updatePackage/" + p.getPid(); 
	}
	
	
	@GetMapping("/deletePackage/{id}")
	public String deletePackage(@PathVariable Integer id, Model model,RedirectAttributes redirectAttributes) {
		
		pservice.deletePackage(id);
		redirectAttributes.addFlashAttribute("successMessage", "Package Deleted Successfully!");
		return "redirect:/admin/viewPackages";
	}
	
	
	@GetMapping("/acceptBooking/{id}")
	public String acceptBooking(@PathVariable Integer id, Model model,RedirectAttributes redirectAttributes) {
		
		Optional<BookingEntity> booking = bservice.getBooking(id);
		
		if(booking.isPresent()) {
			BookingEntity acceptBooking = booking.get();
			acceptBooking.setStatus("accepted");
			bservice.updateBooking(acceptBooking);	
	    }
		return "redirect:/booking/getAllBookings";
	}
	
	@GetMapping("/rejectBooking/{id}")
	public String rejectBooking(@PathVariable Integer id, Model model,RedirectAttributes redirectAttributes) {
		
		Optional<BookingEntity> booking = bservice.getBooking(id);
		
		if(booking.isPresent()) {
			BookingEntity acceptBooking = booking.get();
			acceptBooking.setStatus("rejected");
			bservice.updateBooking(acceptBooking);	
	    }
		return "redirect:/booking/getAllBookings";
	}
	
   @GetMapping("/deleteUser/{id}")
   public String deleteUser(@PathVariable Integer id, Model model) {
	   
	   RegisterEntity registerEntity = registerService.getUserById(id);
	   registerService.deleteUserById(id);
	   return "redirect:/user/getAllCustomers";
	   
   }
}
	
	
	 
	 
	
	

