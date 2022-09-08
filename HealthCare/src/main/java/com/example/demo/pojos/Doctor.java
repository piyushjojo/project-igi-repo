package com.example.demo.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {
	
	@Id
	private String doc_email ; 
	
	//dept no 
	
	private String name ; 
	private String password ; 
	
	//status
	//private boolean status ; 
	
	//photopath 
	//private String photopath ; 
	
	@Enumerated
	private Gender gender ; 
	
	private LocalDate dob;
	
	private String address ; 
	
	private long phone_no ; 
	
	private int fee ; 
	
	private int experience ; //year
	
	private boolean is_approved ; 
	
	
}
