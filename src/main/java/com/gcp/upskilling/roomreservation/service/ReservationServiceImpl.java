package com.gcp.upskilling.roomreservation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gcp.upskilling.roomreservation.controller.ReservationController;
import com.gcp.upskilling.roomreservation.dao.ReservationRepository;
import com.gcp.upskilling.roomreservation.dao.RoomRepository;
import com.gcp.upskilling.roomreservation.entity.Reservation;
import com.gcp.upskilling.roomreservation.entity.Room;


@Service
public class ReservationServiceImpl implements ReservationService {

	
	private ReservationRepository reservationRepository;
	
	@Autowired
	public ReservationServiceImpl(ReservationRepository theReservationRepository) {
		reservationRepository = theReservationRepository;
	}
	
	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}
	
	@Override
	public void save(Reservation theReserve) {
		reservationRepository.save(theReserve);
	}
	
	@Override
	public List<Reservation> searchBy(String theType) {
		List<Reservation> results = null;
		
		if (theType != null && (theType.trim().length() > 0)) {
			results = reservationRepository.findByUsernameAllIgnoreCase(theType);
			for(Reservation x: results) {
				System.out.println(x.getRoom_num());
			}
		}
		else {
			results = findAll();
		}
		
		return results;
	}
	
	@Override
	public List<Reservation> searchByRoomNumAndUsername(String roomNum){
		List<Reservation> results = null;
		
		int room;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		 try {
		       room = Integer.parseInt(roomNum);
		       results = reservationRepository.findByUserAndRoom(room,username);
		    } catch (NumberFormatException nfe) {
		    	
		    	results = reservationRepository.findByUsernameAllIgnoreCase(username);
		    }
		
		return results;
			
	}
	
	@Override
	public Reservation findById(int theId) {
		Optional<Reservation> result = reservationRepository.findById(theId);
		
		Reservation theRoom = null;
		
		if (result.isPresent()) {
			theRoom = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find room id - " + theId);
		}
		
		return theRoom;
	}
	
	@Override
	public void deleteById(int theId) {
		reservationRepository.deleteById(theId);
	}

}
