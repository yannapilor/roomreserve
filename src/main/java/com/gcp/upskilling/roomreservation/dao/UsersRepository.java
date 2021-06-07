package com.gcp.upskilling.roomreservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gcp.upskilling.roomreservation.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

	
	@Query(value = "SELECT * FROM users \r\n"
			+ "WHERE username=?1",
			nativeQuery = true)
	public Users findByUsername(String user);
	
}
