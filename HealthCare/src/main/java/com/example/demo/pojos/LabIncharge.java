package com.example.demo.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lab_incharge_profile")
public class LabIncharge {
	
	@Id
	private String email ; 
	
	private String name ; 
	private long phone_no ; 
	private String address ; 
	private LocalDate dob ; 
	private Gender gender ; 
	//photopath 
	
	private String password ; 

}
