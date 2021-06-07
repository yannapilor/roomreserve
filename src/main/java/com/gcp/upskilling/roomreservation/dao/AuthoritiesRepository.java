package com.gcp.upskilling.roomreservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gcp.upskilling.roomreservation.entity.Authority;
import com.gcp.upskilling.roomreservation.entity.Users;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authority, String> {

	
	@Query(value = "SELECT * FROM authorities \r\n"
			+ "WHERE username=?1",
			nativeQuery = true)
	public Authority findByUsername(String user);
	
	

	@Query(value = "SELECT * FROM authorities \r\n"
			+ "WHERE username=?1",
			nativeQuery = true)
	public List<Authority> findUsers(String user);
	
	
}
