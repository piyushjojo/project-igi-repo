package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name = "patient_profile")
@ToString
public class Patient {
	@Id
	@Column(name = "email")
	private String email;
	
	private String name;
	private long phone;
	
	private String address ; 
	
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	//photopath
	
	//status
	
	private String password;
	
	public Patient() {
		
	}
	
	

}
