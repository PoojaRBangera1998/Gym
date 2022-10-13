package com.collabera.base.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class MyUserDetails implements UserDetails {
	
	@Autowired
	private GymUserDetails gymUserDetails;

	public MyUserDetails(GymUserDetails gymUserDetails) {
		this.gymUserDetails= gymUserDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(gymUserDetails.getUserRole());
		List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
		list.add(authority);
		return list;
		
	}

	@Override
	public String getPassword() {
		
		return gymUserDetails.getPassword();
	}

	@Override
	public String getUsername() {
		
		return gymUserDetails.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
