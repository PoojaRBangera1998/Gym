package com.collabera.base.service;

import java.lang.module.FindException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.collabera.base.dao.UserRepository;
import com.collabera.base.dto.GymUserDetails;
import com.collabera.base.dto.MyUserDetails;
import com.collabera.base.model.UserResponse;

@Service
public class GymServiceImpli implements UserDetailsService,GymService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		GymUserDetails details = repository.findByUsername(username);
		if(details != null) {
		  return new MyUserDetails(details);	
		}
		throw new UsernameNotFoundException("user not found !");
		
	}

	@Override
	public GymUserDetails signUp(GymUserDetails details) {
	    GymUserDetails details2=repository.findByUsername(details.getUsername());
	    if(details2 == null) {
	    	return repository.save(details);
	    }else {
	    	return null;
	    }
	}

	@Override
	public UserResponse getWeight(String username) {
		 GymUserDetails findByUsername = repository.findByUsername(username);
		 UserResponse response = new UserResponse();
		 response.setBodyWeight(findByUsername.getBodyWeight());
		 return response;
	}

}
