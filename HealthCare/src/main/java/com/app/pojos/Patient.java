package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patient_profile")
@Getter
@Setter
@ToString
public class Patient extends BaseEntity {
	
	
	private String email;
	
	private String name;
	
	private long phone;
	
	private String address ; 
	
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	//photopath
	
	private boolean status = true; //active or inactive
	
	private String password;
	
	private double wallet;
	
	public Patient() {
		
	}
	
	

}
