package com.collabera.base.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class GymUserDetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer userId;
     private String name;
     private Double bodyWeight;
     private String username;
     private String password;
     private String userRole;
	
     
}
