package com.collabera.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collabera.base.dto.GymUserDetails;


@Repository
public interface UserRepository extends JpaRepository<GymUserDetails, Integer> {
	
	public GymUserDetails findByUserId(Integer userId);
	
	public GymUserDetails findByUsername(String username);

}
