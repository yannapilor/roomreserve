package com.gcp.upskilling.roomreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.upskilling.roomreservation.dao.AuthoritiesRepository;
import com.gcp.upskilling.roomreservation.dao.ReservationRepository;
import com.gcp.upskilling.roomreservation.entity.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	
	private AuthoritiesRepository authorityRepository;
	
	@Autowired
	public AuthorityServiceImpl(AuthoritiesRepository theAuthorityRepository) {
		authorityRepository = theAuthorityRepository;
	}
	
	
	@Override
	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}

	@Override
	public void save(Authority theAuthority) {
		authorityRepository.save(theAuthority);
	}

	@Override
	public Authority findByUsername(String theName) {
		return authorityRepository.findByUsername(theName);
	}
	
	@Override
	public List<Authority> findByUser(String theName) {
		return authorityRepository.findUsers(theName);
	}


	@Override
	public void deleteById(String theId) {
			authorityRepository.deleteById(theId);
		
	}

}
