package com.leord.car_rental.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.leord.car_rental.admin.cars.models.Cars;
import com.leord.car_rental.admin.cars.services.CarServices;
import com.leord.car_rental.security.models.MyUserDetails;
import com.leord.car_rental.security.models.User;
import com.leord.car_rental.security.services.UserService;
import com.leord.car_rental.user.rent.services.RentalServices;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
	private CarServices carServices;
    
    @Autowired
    private RentalServices rentService;

    @PostMapping("/addNew")
    public RedirectView addNew(User user, RedirectAttributes redir) {
        userService.save(user);

        RedirectView redirectView = new RedirectView("/login", true);
        redir.addFlashAttribute("message", "You are successfully registered!");
        return  redirectView;
    }

    @RequestMapping(value="/user/index", method= RequestMethod.GET)
    public String userHome(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
				
    	List<Cars> cars = carServices.findAll();
        String username = userDetails.getUsername();
        
        User user = userService.username(username);
        System.out.println(user.getUsername());
        model.addAttribute("cars", cars);
        model.addAttribute("user", user);
        return "user/index";
    }
	/*
	 * @GetMapping("/user/index") public String userHome(Model model){ List<Cars>
	 * cars = carServices.findAll();
	 * 
	 * model.addAttribute("cars", cars); return "user/index"; }
	 */
    
    @GetMapping("/admin/index")
    public String adminHome(Model model) {
    	
    	model.addAttribute("countPending", rentService.count("pending"));
		model.addAttribute("countApproved", rentService.count("approved")); 
		model.addAttribute("countCancelled", rentService.count("cancelled")); 
        return "admin/index";
    }
}
