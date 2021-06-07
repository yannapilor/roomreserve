package com.gcp.upskilling.roomreservation.service;

import java.util.List;

import com.gcp.upskilling.roomreservation.entity.Room;

public interface RoomService {
	
	public List<Room> findAll();
	
	public Room findById(int theId);
	
	public void save(Room theEmployee);
	
	public void deleteById(int theId);

	public List<Room> searchBy(String theName);
	
	
	
}
