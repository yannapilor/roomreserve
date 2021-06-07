package com.gcp.upskilling.roomreservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gcp.upskilling.roomreservation.entity.Reservation;
import com.gcp.upskilling.roomreservation.entity.Room;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	
	public List<Reservation> findByUsernameAllIgnoreCase(String type);
	
	public List<Reservation> findByRoomNum(int roomNum);

	@Query(value = "SELECT * FROM reservation \r\n"
			+ "WHERE room_number=?1 AND username=?2",
			nativeQuery = true)
	public List<Reservation> findByUserAndRoom(int num, String user);
}
