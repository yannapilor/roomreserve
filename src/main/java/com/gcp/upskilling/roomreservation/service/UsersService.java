package com.gcp.upskilling.roomreservation.service;

import java.util.List;

import com.gcp.upskilling.roomreservation.entity.Authority;
import com.gcp.upskilling.roomreservation.entity.Users;

public interface UsersService {

	public Users findByUsername(String username);
	
	public void save(Users theUser);

	public void delete(String Username);
	
}
