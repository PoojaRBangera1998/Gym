package com.collabera.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collabera.base.dto.GymUserDetails;
import com.collabera.base.model.UserRequest;
import com.collabera.base.model.UserResponse;
import com.collabera.base.service.GymService;
import com.collabera.base.util.JwtUtil;


@RestController
@RequestMapping("/mrbeast")
@CrossOrigin(origins = "*")
public class GymController {
   
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private GymService gymService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> getAuthenticated(@RequestBody UserRequest userRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
			UserDetails details = userDetailsService.loadUserByUsername(userRequest.getUsername());
			String jwt = jwtUtil.generateToken(details);
           System.out.println(jwt);
			return ResponseEntity.ok(new UserResponse("Logged in Successfully", false, jwt,details.getAuthorities(),details.getUsername(),null));
		} catch (Exception e) {
			UserDetails details = userDetailsService.loadUserByUsername(userRequest.getUsername());
			String jwt = jwtUtil.generateToken(details);
			return ResponseEntity.ok(new UserResponse("Wrong Credentials Try Agian!", true, jwt,null,"user name not found",null));
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserResponse> signupData(@RequestBody GymUserDetails gymUserDetails) {
		GymUserDetails userDetails=gymService.signUp(gymUserDetails);
		if(userDetails != null) {
			return ResponseEntity.created(null).body(new UserResponse("User Created Successfully", false, null, null, null,null));
		}else {
			return ResponseEntity.badRequest().body(new UserResponse("user already exixt", true, null, null, null,null));
		}
		
	}
	
	
	
	@GetMapping("/getbodyweight/{username}")
	public ResponseEntity<?> getWeight(@PathVariable String username ) {
		UserResponse details=gymService.getWeight(username);
		return ResponseEntity.ok(new UserResponse(null, false, null, null, null, details.getBodyWeight()));
	}
	
	
	
	
}
