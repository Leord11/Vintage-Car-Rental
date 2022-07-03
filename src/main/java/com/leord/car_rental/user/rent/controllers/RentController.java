package com.leord.car_rental.user.rent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.leord.car_rental.admin.cars.services.CarServices;
import com.leord.car_rental.security.models.MyUserDetails;
import com.leord.car_rental.security.models.User;
import com.leord.car_rental.security.services.UserService;
import com.leord.car_rental.user.rent.models.Rentals;
import com.leord.car_rental.user.rent.services.RentalServices;

@Controller
public class RentController {

	@Autowired
	private RentalServices rentalService;
	@Autowired
	private CarServices carService;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/rentals")
	public String findAll(Model model) {
		
		model.addAttribute("rentals", rentalService.findAll());
		return "";
	}
	
	@GetMapping("/rentals/show/pending")
	public String findPending(Model model) {
		
		model.addAttribute("rentals", rentalService.findByStatus("pending"));
		
		return "admin/rentals";
	}
	
	@GetMapping("/rentals/{id}")
	public String findById(Model model, @PathVariable int id) {
		
		model.addAttribute("rent", rentalService.findById(id));
		
		return "admin/confirmRent";
	}
	
	@GetMapping("/rentals/add/{id}")
	public String addNew(@AuthenticationPrincipal MyUserDetails userDetails, Model model, @PathVariable Integer id) {
		
		String username = userDetails.getUsername();
		User user = userService.username(username);
		
		
		model.addAttribute("user", user);
		model.addAttribute("car",carService.findById(id));
		
		return "user/rent";
	}
	
	
	@GetMapping("/users/reservations")
	public String showReservations(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
		
		String username = userDetails.getUsername();
		User user = userService.username(username);
		int userId = user.getId();
		System.out.println(userId);
		int count = rentalService.countReservations(userId);
		model.addAttribute("reservations", rentalService.findByUserid(userId));
		
		if(count == 0 ) {
			return "user/noReservation";
		}
		return "user/reservations";
	}
	
	
	
	@PostMapping("/rentals")
	public RedirectView save(Rentals rent, RedirectAttributes redir) {
		
		rentalService.save(rent);
		RedirectView redirectView = new RedirectView("/user/index", true);
		redir.addFlashAttribute("message", "Rental Request Sent!");
		redir.addFlashAttribute("x", "x");
		return redirectView;
	}
	
	@PostMapping("/rentals/confirm")
	public RedirectView confirm(Rentals rent, RedirectAttributes redir) {
		
		rentalService.confirm(rent);
		
		RedirectView redirectView = new RedirectView("/rentals/show/pending", true);
		redir.addFlashAttribute("message", "Approved Successfully!");
		redir.addFlashAttribute("x", "x");
		
		return redirectView;
	}
	
	
	
}
