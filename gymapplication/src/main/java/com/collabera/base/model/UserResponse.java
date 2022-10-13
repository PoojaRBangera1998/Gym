package com.collabera.base.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
   private String message;
   private boolean error;
   private String jwt;
   private Collection<? extends GrantedAuthority> userRole;
   private String name;
   private Double bodyWeight;
}
