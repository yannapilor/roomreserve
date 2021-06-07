package com.gcp.upskilling.roomreservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcp.upskilling.roomreservation.entity.Authority;
import com.gcp.upskilling.roomreservation.entity.Reservation;
import com.gcp.upskilling.roomreservation.entity.Room;
import com.gcp.upskilling.roomreservation.entity.UserAuth;
import com.gcp.upskilling.roomreservation.entity.Users;
import com.gcp.upskilling.roomreservation.service.AuthorityService;
import com.gcp.upskilling.roomreservation.service.ReservationService;
import com.gcp.upskilling.roomreservation.service.UsersService;

@Controller
public class AccountsController {

	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private UsersService usersService;
	
	public AccountsController(AuthorityService theAuthorityService,UsersService theUsersService) {
		authorityService = theAuthorityService;
		usersService = theUsersService;
	}	
	
	@GetMapping("accounts")
	public String accountList(Model theModel) {
		List<Authority> theAuth = authorityService.findAll();
		
		// add to the spring model
		theModel.addAttribute("autho", theAuth);
		theModel.addAttribute("theDate", new java.util.Date());
		
		return "/rooms/accounts";
	}
	
	
	@GetMapping("/showFormForAccount")
	public String showFormForUpdate(@RequestParam("username") String username,
									Model theModel) {
		Authority theAuth = authorityService.findByUsername(username);
		Users user = usersService.findByUsername(username);
		System.out.print(user.getPassword());
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("autho", theAuth);
		theModel.addAttribute("user", user);
		// send over to our form
		return "/rooms/account-form";			
	}
	
	@PostMapping("/accounts/save")
	public String saveEmployee(@ModelAttribute("user") UserAuth account) {
		
		try {
			
			Users user = new Users();
			user.setUsername(account.getUsername());
			String pass = "{bcrypt}"+account.getPassword();
			
			
			System.out.println("PASSWORDTO" +pass + account.getUsername() + account.getAuthority());
			
			user.setPassword(pass);
			user.setEnabled(1);
			
			Authority auth = new Authority();
			auth.setUsername(account.getUsername());
			auth.setAuthority(account.getAuthority());
			
			usersService.save(user);
			authorityService.save(auth);
			
		}catch(Exception e){
			
		}	
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/accounts";
	}
	
	@GetMapping("/accounts/delete")
	public String delete(@RequestParam("username") String theId) {
		
		authorityService.deleteById(theId);
		usersService.delete(theId);
		
		// redirect to /employees/list
		return "redirect:/accounts";
		
	}
	
	@GetMapping("/accounts/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Users user = new Users();
		Authority auth = new Authority();
		
		
		theModel.addAttribute("user", user);
		theModel.addAttribute("autho", auth);
		
		return "/rooms/account-form";
	}
	
	@GetMapping("/accounts/search")
	public String search(@RequestParam("username") String username,
						 Model theModel) {
		
		if(username=="") {
			return "redirect:/accounts";
		}
		
		// delete the employee
		List<Authority> auth = authorityService.findByUser(username);
		
		// add to the spring model
		theModel.addAttribute("autho", auth);
		theModel.addAttribute("theDate", new java.util.Date());
		
		// send to /employees/list
		return "/rooms/accounts";
		
	}
	
	
	
}
