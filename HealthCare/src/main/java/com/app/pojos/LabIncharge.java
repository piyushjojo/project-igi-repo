package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "lab_incharge")
@Getter
@Setter
@ToString
public class LabIncharge extends BaseEntity {
	
	private String email ; 
	
	private String name ; 
	private long phone_no ; 
	private String address ; 
	private LocalDate dob ; 
	private Gender gender ; 
	//photopath 
	
	private String password ; 

}
