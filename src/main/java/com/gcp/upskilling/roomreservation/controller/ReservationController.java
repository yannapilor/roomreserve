package com.gcp.upskilling.roomreservation.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcp.upskilling.roomreservation.entity.Reservation;
import com.gcp.upskilling.roomreservation.entity.Room;
import com.gcp.upskilling.roomreservation.service.ReservationService;

@Controller
@RequestMapping("/reserves")
public class ReservationController {

	private ReservationService reservationService;
	
	private String username;
	
	public ReservationController(ReservationService theReservationService) {
		reservationService = theReservationService;
	}
	
	@GetMapping("/list")
	public String reserveList(Model theModel) {
		
		String username;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		List<Reservation> theRes= reservationService.searchBy(username);
		
		theModel.addAttribute("res", theRes);
		theModel.addAttribute("theDate", new java.util.Date());
		
		return "/rooms/reserve-room";
	}
	
	
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("reservation") Reservation theReservation) {
		
		// save the employee
		reservationService.save(theReservation);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/reserves/list";
	}
	
	
	@GetMapping("/search")
	public String search(@RequestParam("roomNum") String roomNum,
						 Model theModel) {
		
		// delete the employee
		List<Reservation> theRes= reservationService.searchByRoomNumAndUsername(roomNum);
		
		if(theRes==null) {
			return "redirect:/reserves/list";
		}
		
		// add to the spring model
		theModel.addAttribute("res", theRes);
		
		// send to /employees/list
		return "/rooms/reserve-room";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("roomId") int theId,
									Model theModel) {
		String rId = String.valueOf(theId);
		// get the employee from the service
		Reservation theRoom = reservationService.findById(theId);
		System.out.println("RESERVE:" + theRoom.getRoomId() + theRoom.getUsername() + theRoom.getRoomNum());
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("res", theRoom);
		
		// send over to our form
		return "/rooms/reserve-form";			
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("roomId") int theId) {
		
		// delete the employee
		reservationService.deleteById(theId);
		
		// redirect to /employees/list
		return "redirect:/reserves/list";
		
	}
	
}
