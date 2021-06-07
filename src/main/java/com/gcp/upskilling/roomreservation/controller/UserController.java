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

import com.gcp.upskilling.roomreservation.entity.Reservation;
import com.gcp.upskilling.roomreservation.entity.Room;
import com.gcp.upskilling.roomreservation.service.ReservationService;
import com.gcp.upskilling.roomreservation.service.RoomService;



@Controller
public class UserController {
	
	private String username;
	
	private RoomService roomService;
	
	public UserController(RoomService theRoomService) {
		roomService = theRoomService;
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		// get employees from db
		List<Room> theRoom = roomService.findAll();
		
		// add to the spring model
		theModel.addAttribute("rooms", theRoom);
		theModel.addAttribute("theDate", new java.util.Date());
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		System.out.println(username);
		
		return "/rooms/list-room";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("type") String theType,
						 Model theModel) {
		
		// delete the employee
		List<Room> theRoom= roomService.searchBy(theType);
		
		// add to the spring model
		theModel.addAttribute("rooms", theRoom);
		
		// send to /employees/list
		return "/rooms/list-room";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Room theRoom = new Room();
		
		theModel.addAttribute("room", theRoom);
		
		return "/rooms/room-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Room theRoom) {
		
		// save the employee
		roomService.save(theRoom);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("roomId") int theId,
									Model theModel) {
		
		// get the employee from the service
		Room theRoom = roomService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("room", theRoom);
		
		// send over to our form
		return "/rooms/room-form";			
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("roomId") int theId) {
		
		// delete the employee
		roomService.deleteById(theId);
		
		// redirect to /employees/list
		return "redirect:/list";
		
	}
	
	
	Reservation reserve = new Reservation();
	
	@GetMapping("/reserve")
	public String reserve(@RequestParam("roomId") int theId,Model theModel) {
		
		Room theRoom = roomService.findById(theId);
		
		if(theRoom.getAvailability()<=0) {
			
			System.out.println("UNABLE TO RESERVE DUE TO NO AVAILABILITY");
			
			return "redirect:/list";
		}	
		
		reserve.setRoomId(theRoom.getId());
		reserve.setUsername(username);	
		
		theModel.addAttribute("res" , reserve);
		
		theRoom.setAvailability(theRoom.getAvailability()-1);
		
		roomService.save(theRoom);

		return "/rooms/reserve";
	}
	
}
