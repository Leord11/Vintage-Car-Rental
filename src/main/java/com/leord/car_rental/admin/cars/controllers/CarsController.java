package com.leord.car_rental.admin.cars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.leord.car_rental.admin.cars.models.Cars;
import com.leord.car_rental.admin.cars.repositories.CarsRepository;
import com.leord.car_rental.admin.cars.services.CarServices;

@Controller
public class CarsController {

	@Autowired
	private CarServices carServices;
	
	@Autowired
	private CarsRepository carsRepository;
	
	@RequestMapping(value="/admin/cars", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<Cars> cars = carServices.findAll();
		model.addAttribute("cars", cars);
		return "admin/cars";
	}
	
	@GetMapping("/admin/addCar")
	public String addCar() {
		return "admin/addCar";
	}
	
	/*
	 * @PostMapping("/admin/cars") public String save(Cars car) {
	 * carsRepository.save(car); return "redirect:/admin/cars"; }
	 */
	
	
	@PostMapping("/admin/cars")
	public RedirectView save(@RequestParam MultipartFile file, @RequestParam String make, @RequestParam String model, @RequestParam String price, 
			@RequestParam String topspeed, @RequestParam String acceleration, @RequestParam String seats, @RequestParam String engine, @RequestParam String details, RedirectAttributes redir) {
		
		carServices.saveAll(file, make, model, price, topspeed, acceleration, seats, engine, details);
		
		RedirectView redirectView = new RedirectView("/admin/cars", true);
		redir.addFlashAttribute("message", "Successfully Added!");
		redir.addFlashAttribute("x", "x");
		
		return redirectView;
	}
	
	@PostMapping("/admin/update")
	public RedirectView update(@RequestParam MultipartFile file, Cars car,RedirectAttributes redir) {
		
		carServices.update(file, car);
		RedirectView redirectView = new RedirectView("/admin/cars", true);
		redir.addFlashAttribute("message", "Successfully Updated!");
		redir.addFlashAttribute("x", "x");
		
		return redirectView;
	}
	
	@GetMapping("/admin/car/edit/{id}")
	public String editCar(@PathVariable Integer id, Model model) {
		Cars car = carServices.findById(id);
		model.addAttribute("car",car);
		return "admin/editCar";
	}
	
	@RequestMapping(value="/admin/car/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE} )
	public String deleteCar(@PathVariable Integer id) {
		carServices.delete(id);
		return "redirect:/admin/cars";
	}
}
