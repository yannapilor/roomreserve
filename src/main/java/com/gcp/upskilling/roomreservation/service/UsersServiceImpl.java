package com.gcp.upskilling.roomreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.upskilling.roomreservation.dao.UsersRepository;
import com.gcp.upskilling.roomreservation.entity.Users;


@Service
public class UsersServiceImpl implements UsersService {

	private UsersRepository usersRepository;
	
	@Autowired
	public  UsersServiceImpl(UsersRepository theUsersRepository) {
		usersRepository = theUsersRepository;
	}
	
	@Override
	public Users findByUsername(String username) {

		return usersRepository.findByUsername(username);
	}

	@Override
	public void save(Users theUser) {
		usersRepository.save(theUser);
	}
	
	@Override
	public void delete(String theUser) {
		usersRepository.deleteById(theUser);
	}
}
