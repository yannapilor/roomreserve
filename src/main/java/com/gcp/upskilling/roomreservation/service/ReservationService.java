package com.gcp.upskilling.roomreservation.service;

import java.util.List;

import com.gcp.upskilling.roomreservation.entity.Reservation;
import com.gcp.upskilling.roomreservation.entity.Room;

public interface ReservationService {
	
	public void save(Reservation theReserve);

	public List<Reservation> findAll();
	
	public List<Reservation> searchBy(String theName);
	
	public List<Reservation> searchByRoomNumAndUsername(String roomNum);
	
	public Reservation findById(int theId);
	
	public void deleteById(int theId);
}
