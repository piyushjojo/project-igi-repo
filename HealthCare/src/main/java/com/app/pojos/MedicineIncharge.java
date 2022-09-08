package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicine_incharge_profile")
public class MedicineIncharge {
	
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
