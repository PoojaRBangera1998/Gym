package com.collabera.base.service;

import com.collabera.base.dto.GymUserDetails;
import com.collabera.base.model.UserResponse;

public interface GymService {
 public GymUserDetails signUp(GymUserDetails details);

public UserResponse getWeight(String username); 
 
 
	
}
