package com.gcp.upskilling.roomreservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcp.upskilling.roomreservation.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name
	public List<Room> findAllByOrderByPriceAsc();
	
	// search by name
	public List<Room> findByTypeAllIgnoreCase(String type);

}

