package com.gcp.upskilling.roomreservation.service;

import java.util.List;

import com.gcp.upskilling.roomreservation.entity.Authority;

public interface AuthorityService {

	
	 	public List<Authority> findAll();
		
		public void save(Authority theAuthority);
		
		public void deleteById(String theId);

		public Authority findByUsername(String theName);
		
		public List<Authority> findByUser(String theName);
}
